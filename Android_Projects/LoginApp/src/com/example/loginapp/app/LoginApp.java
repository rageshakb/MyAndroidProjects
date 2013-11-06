package com.example.loginapp.app;

import android.app.Application;

public class LoginApp extends Application {
	private LoginAPI mLoginApi;
	
	@Override
	public void onCreate() {		
		super.onCreate();
		mLoginApi = new LoginAPI();
	}
	
	public LoginAPI getLoginApi() {
		return mLoginApi;
	}

}
