package com.example.demo.dao;

import com.example.demo.bean.User;

public interface IUserDao {

	public int insertUser(User user);
	
	public User selectByUseId(String id);
}
