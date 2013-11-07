package com.example.loginapp.app;

import com.example.loginapp.db.DBManager;

import android.app.Application;

public class LoginApp extends Application {
	private LoginAPI mLoginApi;
	private DBManager mDbManager;
	
	@Override
	public void onCreate() {		
		super.onCreate();
		mLoginApi = new LoginAPI();
		mDbManager = new DBManager(getApplicationContext());
	}
	
	public LoginAPI getLoginApi() {
		return mLoginApi;
	}
	
	public DBManager getDBManager() {
		return mDbManager;
	}

}
