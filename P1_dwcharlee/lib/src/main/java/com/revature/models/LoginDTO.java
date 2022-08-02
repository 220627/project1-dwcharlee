package com.revature.models;

public class LoginDTO {

	//the loginDTO models ONLY the username/password sent in by the user
	private String username;
	private String password;
	
	//we just need one all-args constructor - the user will never just send a username or password or neither
	public LoginDTO(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}	
}
