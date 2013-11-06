package com.example.loginapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.loginapp.R;
import com.example.loginapp.models.User;
import com.example.loginapp.models.UserList;

public class UserListAdapter extends ArrayAdapter<User> {
	private UserList mUserList;
	private Context mContext;
	private LayoutInflater mLayoutInflater;
	
	public UserListAdapter (Context context, UserList userList, int listRowResourceId ) {
		super(context, listRowResourceId, userList);
		this.mUserList = userList;
		this.mContext = context;
		mLayoutInflater = LayoutInflater.from(context);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = mLayoutInflater.inflate(R.layout.user_list_item, parent, false);
			viewHolder.name = (TextView) convertView.findViewById(R.id.name);
			viewHolder.department = (TextView) convertView.findViewById(R.id.department);
			viewHolder.id = (TextView) convertView.findViewById(R.id.userid);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		User user  = mUserList.get(position);
		viewHolder.name.setText(user.getName());
		viewHolder.department.setText(user.getDepartment());
		viewHolder.id.setText(user.getId() + "");
		
		return convertView;
	}
	
	private static class ViewHolder {
		private TextView name;
		private TextView department;
		private TextView id;
	}

	

}
