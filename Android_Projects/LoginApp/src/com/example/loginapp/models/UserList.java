package com.example.loginapp.models;

import java.util.ArrayList;

public class UserList extends ArrayList<User> implements Result {
	
	private static final long serialVersionUID = -4559587993969466862L;

	public void addUser(User user) {
		add(user);
	}
	
	public User getUser(int index) {
		return get(index);
	}

}
