package com.example.demo.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@Configuration
//@PropertySource(value = "classpath:config/login.yml")
@ConfigurationProperties(prefix = "login.user")
public class PropertiesConfig {

//	@Value("${login.user.loginName}")
	private String loginName;
	
//	@Value("${login.user.password}")
	private String password;

//	@Value("${login.user.loginInfo}")
	private String loginInfo;
	
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLoginInfo() {
		return loginInfo;
	}

	public void setLoginInfo(String loginInfo) {
		this.loginInfo = loginInfo;
	}
	
}
