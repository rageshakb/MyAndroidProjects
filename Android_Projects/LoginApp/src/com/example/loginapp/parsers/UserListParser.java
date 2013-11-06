package com.example.loginapp.parsers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.example.loginapp.models.User;
import com.example.loginapp.models.UserList;

public final class UserListParser extends AbstractResultParser<UserList> {
	private static final String TAG = UserListParser.class.getSimpleName();
	
	@Override
	public UserList parse(String input) throws JSONException {
		Log.d(TAG, "User list string :" + input);
		UserList userList = new UserList();
		JSONArray users = new JSONArray(input);
		for (int i = 0; i < users.length(); i++) {
			JSONObject userJson = (JSONObject) users.get(i);
			User user = new User(userJson.getLong("id"), userJson.getString("department"), userJson.getString("name"));
			userList.addUser(user);
		}		
		return userList;
	}

}
