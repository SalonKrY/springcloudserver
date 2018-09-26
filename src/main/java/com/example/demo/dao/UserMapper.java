package com.example.demo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.demo.bean.User;

@Mapper
public interface UserMapper {
	public User selectUserById(String id);
}
