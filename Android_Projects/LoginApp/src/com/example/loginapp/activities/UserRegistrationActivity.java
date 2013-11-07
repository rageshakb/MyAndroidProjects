package com.example.loginapp.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.loginapp.R;
import com.example.loginapp.app.LoginApp;
import com.example.loginapp.db.DBManager;
import com.example.loginapp.models.User;

public class UserRegistrationActivity extends Activity {
	private EditText mName;
	private EditText mEmail;
	private EditText mPassword;
	private EditText mAddress;
	private EditText mCity;
	private RadioGroup mGender;
	private ProgressDialog mUserSaveProgress;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_registration);
		
		initViews();
	}
	
	private void initViews() {
		mName = (EditText) findViewById(R.id.name);
		mEmail = (EditText) findViewById(R.id.email);
		mPassword = (EditText) findViewById(R.id.pass);
		mAddress = (EditText) findViewById(R.id.address); 
		mCity = (EditText) findViewById(R.id.city);
		mGender = (RadioGroup) findViewById(R.id.gender);		
	}

	public void onRegister(View view) {
		String name = mName.getText().toString();
		String email = mEmail.getText().toString();
		String password = mPassword.getText().toString();
		String address = mAddress.getText().toString();
		String city = mCity.getText().toString();
		int selectedGenderId = mGender.getCheckedRadioButtonId();
		RadioButton genderBtn = (RadioButton) findViewById(selectedGenderId);
		String gender = genderBtn.getText().toString();
		User user = new User();
		user.setName(name);
		user.setEmail(email);
		user.setPassword(password);
		user.setAddress(address);
		user.setCity(city);
		user.setGender(gender);
		new UserDbSaveTask().execute(user);
	}
	
	private class UserDbSaveTask extends AsyncTask<User, Void, Void> {
		
		@Override
		protected void onPreExecute() {
			mUserSaveProgress = ProgressDialog.show(UserRegistrationActivity.this, "", "Saving user to database...");
			mUserSaveProgress.setCancelable(false);
			mUserSaveProgress.show();
		}

		@Override
		protected Void doInBackground(User... params) {
			LoginApp app = (LoginApp) getApplicationContext();
			DBManager manager = app.getDBManager();
			manager.getUserDao().insertUser(params[0]);
			return null;			
		}
		
		@Override
		protected void onPostExecute(Void result) {
			mUserSaveProgress.dismiss();
			Intent intent = new Intent(UserRegistrationActivity.this, LoginActivity.class);
			startActivity(intent);
			finish();
		}

		
		
	}

}
