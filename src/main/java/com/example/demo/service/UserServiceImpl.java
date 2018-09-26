package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bean.User;
import com.example.demo.dao.IUserDao;

@Service
public class UserServiceImpl implements IUserService{

	@Autowired
	private IUserDao userDao;
	
	@Override
	public User queryUser(String id) {
		return userDao.selectByUseId(id);
	}

	
}
