package com.example.loginapp.activities;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.ListView;

import com.example.loginapp.R;
import com.example.loginapp.adapters.UserListAdapter;
import com.example.loginapp.app.LoginApp;
import com.example.loginapp.models.UserList;

public class UserListActivity extends ListActivity {
	private static final String TAG = UserListActivity.class.getSimpleName();
	private ProgressDialog mUserListProgressDialog;
	private ListView mUserList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.user_list);
		
		mUserList = (ListView) findViewById(android.R.id.list);
		new UserListFetchAsyncTask().execute();
		
	}
	
	private class UserListFetchAsyncTask extends AsyncTask<Void, Void, UserList> {
		
		@Override
		protected void onPreExecute() {
			mUserListProgressDialog = ProgressDialog.show(UserListActivity.this, "", "Fetching user list...");
			mUserListProgressDialog.setCancelable(false);
			mUserListProgressDialog.show();
		}

		@Override
		protected UserList doInBackground(Void... params) {
			LoginApp app = (LoginApp) getApplication();
			UserList userList = null;
			try {
				userList = app.getLoginApi().getUserList();
			} catch (ClientProtocolException e) {				
				e.printStackTrace();
			} catch (JSONException e) {				
				e.printStackTrace();
			} catch (IOException e) {				
				e.printStackTrace();
			}
			return userList;
		}
		
		@Override
		protected void onPostExecute(UserList result) {
			Log.d(TAG, "UserList :" + result);
			mUserListProgressDialog.dismiss();
			UserListAdapter adapter = new UserListAdapter(UserListActivity.this, result, R.layout.user_list_item);			
			mUserList.setAdapter(adapter);
		}
		
	}

}
