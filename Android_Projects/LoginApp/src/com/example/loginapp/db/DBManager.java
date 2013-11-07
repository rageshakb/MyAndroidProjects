package com.example.loginapp.db;

import com.example.loginapp.dao.UserDao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public final class DBManager {
	private static final String TAG = DBManager.class.getSimpleName();
	private static final String DATABASE_NAME = "loginapp.db";
	private static final int DATABASE_VERSION = 1;
	private Context mContext;
	private SQLiteDatabase mDatabase;
	private UserDao mUserDao;
	
	public DBManager(Context context) {
		this.mContext = context;
		OpenHelper helper = new OpenHelper(context);
		this.mDatabase = helper.getWritableDatabase();
		this.mUserDao = new UserDao(mDatabase);	
		
	}
	
	private class OpenHelper extends SQLiteOpenHelper {

		public OpenHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);			
		}
		
		@Override
		public void onCreate(SQLiteDatabase db) {
			UserDao.createTable(db);
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			mUserDao.dropTable(db);
			onCreate(db);
			
		}
		
		
		
	}
	
	public UserDao getUserDao() {
		return mUserDao;
	}
	
	

}
