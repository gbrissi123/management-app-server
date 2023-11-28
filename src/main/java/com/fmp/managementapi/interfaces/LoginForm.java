package com.fmp.managementapi.interfaces;

public class LoginForm {
	final String password;
	final String username;
	
	public LoginForm(String password, String username) {
		super();
		this.password = password;
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}
	
	
}
