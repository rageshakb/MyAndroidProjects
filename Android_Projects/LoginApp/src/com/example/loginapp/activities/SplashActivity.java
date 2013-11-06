package com.example.loginapp.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Window;
import android.widget.ProgressBar;

import com.example.loginapp.R;

public class SplashActivity extends Activity {
	private static final String TAG = SplashActivity.class.getSimpleName();
	private int mSplashTime = 1000;	
	private ProgressBar mSplashProgressBar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.splash);
		
		init();
		
		Thread splashThread = new Thread(mSplashRunnable);
		splashThread.start();	
		
	}

	private void init() {
		mSplashProgressBar = (ProgressBar) findViewById(R.id.splashProgressBar);
		mSplashProgressBar.setVisibility(ProgressBar.VISIBLE);
		
	}
	
	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);			
			redirectToLogin();
		}
	};
	
	private Runnable mSplashRunnable = new Runnable() {		
		@Override
		public void run() {
			Log.d(TAG, "Handler : " + mHandler);
			Message msg = new Message();
			mHandler.sendMessageDelayed(msg, mSplashTime);			
		}
	};
	
	private void redirectToLogin() {
		mSplashProgressBar.setVisibility(ProgressBar.GONE);
		Intent loginIntent = new Intent(this, LoginActivity.class);
		startActivity(loginIntent);
		finish();
	}
	
	
	

}
