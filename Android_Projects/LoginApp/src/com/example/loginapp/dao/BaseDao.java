package com.example.loginapp.dao;

import android.database.sqlite.SQLiteDatabase;

public abstract class BaseDao {
	protected static final String TYPE_TEXT = " TEXT";
	protected static final String TYPE_INTEGER = " INTEGER";
	protected static final String COMMA_SEP = ",";
	protected SQLiteDatabase mDatabase;
	
	public BaseDao(SQLiteDatabase db) {
		this.mDatabase = db;
	}
	
	public abstract void dropTable(SQLiteDatabase db);

}
