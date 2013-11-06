package com.example.loginapp.parsers;

import org.json.JSONException;

import com.example.loginapp.models.Result;

public interface Parser<T extends Result> {
	T parse(String input) throws JSONException;
}
