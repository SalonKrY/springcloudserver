package com.example.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.bean.User;


@Repository
public class UserDaoImpl implements IUserDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public int insertUser(User user) {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public User selectByUseId(String id) {
		List<User> list = jdbcTemplate.query("select * from tb_user where id = ?", 
				new Object[]{id}, new BeanPropertyRowMapper(User.class));
		return list.get(0);
	}

}
