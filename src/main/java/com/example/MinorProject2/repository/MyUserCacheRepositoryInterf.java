package com.example.MinorProject2.repository;

import com.example.MinorProject2.model.MyUser;

public interface MyUserCacheRepositoryInterf {

	public void set(MyUser myUser);
	public MyUser get(String username);
	
}
