package com.example.loginapp.parsers;

import org.json.JSONException;

import com.example.loginapp.models.Result;

public abstract class AbstractResultParser<T extends Result> implements Parser<T> {
	
	@Override
	public T parse(String input) throws JSONException {		
		return null;
	}

}
