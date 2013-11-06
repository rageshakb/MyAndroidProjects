package com.example.loginapp.parsers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.example.loginapp.models.LoginResult;

public class LoginResultParser extends AbstractResultParser<LoginResult> {
	private static final String TAG = LoginResultParser.class.getSimpleName();
	@Override
	public LoginResult parse(String input) throws JSONException {
		Log.d(TAG, "Response string to parse:" + input);
		JSONArray jsonResp = new JSONArray(input);
		JSONObject loginResponse = (JSONObject) jsonResp.get(0);
		LoginResult result = new LoginResult();
		result.setStatus((loginResponse.getString("status")));
		result.setSuccess((result.getStatus().equals("ok") ? true : false));
		return result;		
	}

}
