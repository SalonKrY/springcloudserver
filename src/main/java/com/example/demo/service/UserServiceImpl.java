package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bean.User;
import com.example.demo.dao.IUserDao;
import com.example.demo.dao.UserMapper;

@Service
public class UserServiceImpl implements IUserService{

	@Autowired
	private IUserDao userDao;
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public User queryUser(String id) {
//		return userDao.selectByUseId(id);
		return userMapper.selectUserById(id);
	}

	
}
