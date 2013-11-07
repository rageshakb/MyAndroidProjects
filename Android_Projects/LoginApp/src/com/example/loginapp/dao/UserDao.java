package com.example.loginapp.dao;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;

import com.example.loginapp.models.User;

public class UserDao extends BaseDao{	
	private static final String TAG = UserDao.class.getSimpleName();
	
	private static abstract class UserEntry implements BaseColumns {
		public static final String TABLE_NAME = "Users";
		public static final String COLUMN_NAME = "name";
		public static final String COLUMN_EMAIL = "email";
		public static final String COLUMN_PASSWORD = "password";
		public static final String COLUMN_ADDRESS = "address";
		public static final String COLUMN_CITY = "city";
		public static final String COLUMN_GENDER = "gender";
	}
	

	public UserDao(SQLiteDatabase db) {
		super(db);		
	}

	
	public static void createTable(SQLiteDatabase db) {		
		String userTable = "CREATE TABLE " + UserEntry.TABLE_NAME + "(" +
				UserEntry._ID + TYPE_INTEGER + " PRIMARY KEY AUTOINCREMENT" + COMMA_SEP + UserEntry.COLUMN_NAME + TYPE_TEXT + COMMA_SEP  +
				UserEntry.COLUMN_EMAIL + TYPE_TEXT + COMMA_SEP + UserEntry.COLUMN_PASSWORD + TYPE_TEXT + COMMA_SEP +
				UserEntry.COLUMN_ADDRESS + TYPE_TEXT + COMMA_SEP + UserEntry.COLUMN_CITY + TYPE_TEXT + COMMA_SEP +
				UserEntry.COLUMN_GENDER + TYPE_TEXT + ")";
		Log.d(TAG, "SQL:- User table :->" + userTable);
		db.execSQL(userTable);
	}

	@Override
	public void dropTable(SQLiteDatabase db) {
		db.execSQL("DROP TABLE IF EXITS " + UserEntry.TABLE_NAME);
		
	}
	
	public void insertUser(User user) {
		ContentValues values = new ContentValues();
		values.put(UserEntry.COLUMN_NAME, user.getName());
		values.put(UserEntry.COLUMN_EMAIL, user.getEmail());
		values.put(UserEntry.COLUMN_PASSWORD, user.getPassword());
		values.put(UserEntry.COLUMN_ADDRESS, user.getAddress());
		values.put(UserEntry.COLUMN_CITY, user.getCity());
		values.put(UserEntry.COLUMN_GENDER, user.getGender());
		mDatabase.insert(UserEntry.TABLE_NAME, null, values);
		
	}
	
	

}
