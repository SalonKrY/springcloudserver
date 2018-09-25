package com.example.demo.bean;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class User {
	private String id;
	private String userName;
	private String password;
	@JSONField(format="yyyy-MM-dd")
	private Date birthday;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
}
