package com.example.loginapp.app;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;

import com.example.loginapp.http.HttpApi;
import com.example.loginapp.models.LoginResult;
import com.example.loginapp.models.UserList;
import com.example.loginapp.models.UserLogin;
import com.example.loginapp.parsers.LoginResultParser;
import com.example.loginapp.parsers.UserListParser;

public final class LoginAPI {
	private String mApiDomain;
	private HttpApi mHttpApi;
	
	public LoginAPI() {
		//this.mApiDomain = domain;
		mHttpApi = new HttpApi();
	}
	
	public boolean login(UserLogin userLogin) throws ClientProtocolException, JSONException, IOException {
		String url = getFullUrl(AppConstants.URL_LOGIN);
		LoginResult result = (LoginResult) mHttpApi.executeHttpPost(url, new LoginResultParser(), 
				new BasicNameValuePair("username", userLogin.getUsername()),
				new BasicNameValuePair("password", userLogin.getPassword()),
				new BasicNameValuePair("origusername", userLogin.getUsername()),
				new BasicNameValuePair("deviceToken", ""));
		
		return result.getSuccess();
		
	}
	
	public UserList getUserList() throws ClientProtocolException, JSONException, IOException {
		String url = getFullUrl(AppConstants.URL_USER_LIST);
		UserList userList = (UserList) mHttpApi.executeHttpPost(url, new UserListParser(), null);
		return userList;
	}
	
	private String getFullUrl(String url) {
		return (AppConstants.URL_BASE + url);
	}

}
