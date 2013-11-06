package com.example.loginapp.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.example.loginapp.app.AppConstants;
import com.example.loginapp.models.Result;
import com.example.loginapp.parsers.Parser;

public final class HttpApi {
	private static final String TAG = HttpApi.class.getSimpleName();

	public Result executeHttpGet(final String url, Parser<? extends Result> parser,
			NameValuePair... nameValuePairs) throws JSONException {

		return parser.parse(null);

	}

	public Result executeHttpPost(final String url, Parser<? extends Result> parser,
			NameValuePair... nameValuePairs) throws JSONException, ClientProtocolException, IOException {
		
		HttpClient client = getHttpClient();
		HttpPost postRequest = createPost(url, nameValuePairs);
		HttpResponse response = client.execute(postRequest);		
		int status = response.getStatusLine().getStatusCode();
		Log.d(TAG, "Response Status : " + status);
		switch(status) {
		case 200 :
			String result = EntityUtils.toString(response.getEntity());
			return parser.parse(result);
		}
		return null;
		
	}

	private HttpClient getHttpClient() {
		HttpClient client = new DefaultHttpClient();
		return client;
	}
	
	private HttpPost createPost(final String url, final NameValuePair... nameValuePairs) throws UnsupportedEncodingException, JSONException {
		HttpPost httpPost = new HttpPost(url);
		if (nameValuePairs != null && nameValuePairs.length > 0) {
			JSONObject params = getParamsAsJson(nameValuePairs);
			httpPost.setEntity(new StringEntity(params.toString()));
			httpPost.setHeader(AppConstants.HTTP_HEADER_CONTENT_TYPE, AppConstants.HTTP_MIME_TYPE_JSON);
		}		
		httpPost.setHeader(AppConstants.HTTP_HEADER_ACCEPT, AppConstants.HTTP_MIME_TYPE_JSON);        
		return httpPost;
	}
	
	private JSONObject getParamsAsJson (NameValuePair... nameValuePairs) throws JSONException {
		JSONObject params = new JSONObject();
		
		for (NameValuePair param : nameValuePairs) {
			if (param != null && param.getValue() != null) {
				params.put(param.getName(), param.getValue());
			}
		}
		return params;
	}

}
