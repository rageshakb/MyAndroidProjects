package com.example.loginapp.activities;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

import com.example.loginapp.R;
import com.example.loginapp.R.id;
import com.example.loginapp.R.layout;
import com.example.loginapp.R.menu;
import com.example.loginapp.app.LoginApp;
import com.example.loginapp.models.UserLogin;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class LoginActivity extends Activity {
	private static final String TAG = LoginActivity.class.getSimpleName();
	private static final String PREFS_NAME = "com.example.loginapp.Login";
	private static final String PREF_USERNAME = "username";
	private static final String PREF_PASSWORD = "password";
	private static final String PREF_REMEMBER_ME = "rememberMe";
	private EditText mUserName;
	private EditText mPassword;
	private CheckBox mRememberMe;
	private SharedPreferences mSharedPreferences;
	private ProgressBar mLoginProgressBar;
	private ProgressDialog mLoginProgressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		
		initViews();		
		updateViewState();
		
	}
	
	private void initViews() {
		mUserName = (EditText) findViewById(R.id.username);
		mPassword = (EditText) findViewById(R.id.password);
		mRememberMe = (CheckBox) findViewById(R.id.rememberMe);
		mLoginProgressBar = (ProgressBar) findViewById(R.id.loginProgressBar);		
		mSharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
	}
	
	private void updateViewState() {
		String userName = mSharedPreferences.getString(PREF_USERNAME, null);
		String password = mSharedPreferences.getString(PREF_PASSWORD, null);
		boolean rememberMe = mSharedPreferences.getBoolean(PREF_REMEMBER_ME, false);
		Log.d(TAG, "Username :" + userName + ", Password : " + password);		
		
		if (userName != null && password != null) {
			mUserName.setText(userName);
			mPassword.setText(password);
		} else {
			mUserName.requestFocus();
		}
		mRememberMe.setChecked(rememberMe);
		
		
	}
	
	public void onLoginClick (View view) {
		Log.d(TAG, "Remember me status : " + mRememberMe.isChecked());
		if (mRememberMe.isChecked()) {
			saveLoginCredentialsToPreference();
			new LoginAsyncTask().execute();
		} else {
			removeLoginCredentialsFromPreference();
		}
		
	}

	private void removeLoginCredentialsFromPreference() {
		SharedPreferences.Editor editor = mSharedPreferences.edit();
		editor.clear();
		editor.commit();		
	}

	private void saveLoginCredentialsToPreference() {
		String userName = mUserName.getText().toString();
		String password = mPassword.getText().toString();
		Log.d(TAG, "Saving credentials: Username :" + userName + ", Password : " + password);
		if (userName != null && password != null) {
			SharedPreferences.Editor editor = mSharedPreferences.edit();
			editor.putString(PREF_USERNAME, userName);
			editor.putString(PREF_PASSWORD, password);
			editor.putBoolean(PREF_REMEMBER_ME, mRememberMe.isChecked());
			editor.commit();
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}
	
	private class LoginAsyncTask extends AsyncTask<Void, Void, Boolean> {
		
		@Override
		protected void onPreExecute() {
			/*mLoginProgressDialog = new ProgressDialog(LoginActivity.this);
			mLoginProgressDialog.setTitle("Login Status");
			mLoginProgressDialog.setMessage("Logging In...");
			mLoginProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			mLoginProgressDialog.show();*/
			//mLoginProgressBar.setVisibility(ProgressBar.VISIBLE);
			mLoginProgressDialog = ProgressDialog.show(LoginActivity.this, "", "Logging In...");
			mLoginProgressDialog.setCancelable(false);
		}

		@Override
		protected Boolean doInBackground(Void... params) {
			LoginApp app = (LoginApp)getApplication();
			UserLogin userLogin = new UserLogin();
			userLogin.setUsername(mUserName.getText().toString());
			userLogin.setPassword(mPassword.getText().toString());
			boolean result = false;
			try {
				result = app.getLoginApi().login(userLogin);
			} catch (ClientProtocolException e) {				
				e.printStackTrace();
			} catch (JSONException e) {				
				e.printStackTrace();
			} catch (IOException e) {				
				e.printStackTrace();
			}
			return result;
		}
		
		@Override
		protected void onPostExecute(Boolean result) {
			//mLoginProgressBar.setVisibility(ProgressBar.INVISIBLE);
			//mLoginProgressDialog.hide();
			mLoginProgressDialog.dismiss();
			if (result) {
				Intent intent = new Intent(LoginActivity.this, UserListActivity.class);
				startActivity(intent);
				finish();
			} else {
				Toast.makeText(LoginActivity.this, "Login failed.Try later!!!", Toast.LENGTH_LONG).show();
			}
		}
		
	}

}
