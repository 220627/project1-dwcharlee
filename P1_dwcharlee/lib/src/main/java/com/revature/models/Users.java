package com.revature.models;

public class Users {

	private int ers_users_id;
	private String ers_username;
	private String ers_password;
	private String user_first_name;
	private String user_last_name;
	private String user_email;
	private int user_role_id;
	private User_Roles user_role_id_fk;


	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Users(int ers_users_id, String ers_username, String ers_password, String user_first_name,
			String user_last_name, String user_email, int user_role_id) {
		super();
		this.ers_users_id = ers_users_id;
		this.ers_username = ers_username;
		this.ers_password = ers_password;
		this.user_first_name = user_first_name;
		this.user_last_name = user_last_name;
		this.user_email = user_email;
		this.user_role_id = user_role_id;
	}

	public Users(String ers_username, String ers_password, String user_first_name, String user_last_name,
			String user_email, User_Roles user_role_id_fk) {
		super();
		this.ers_username = ers_username;
		this.ers_password = ers_password;
		this.user_first_name = user_first_name;
		this.user_last_name = user_last_name;
		this.user_email = user_email;
		this.user_role_id_fk = user_role_id_fk;
	}

	public Users(String ers_username, String ers_password, String user_first_name, String user_last_name,
			String user_email, int user_role_id) {
		super();
		this.ers_username = ers_username;
		this.ers_password = ers_password;
		this.user_first_name = user_first_name;
		this.user_last_name = user_last_name;
		this.user_email = user_email;
		this.user_role_id = user_role_id;
	}
	
	@Override
	public String toString() {
		return "Users [ers_users_id=" + ers_users_id + ", ers_username=" + ers_username + ", ers_password="
				+ ers_password + ", user_first_name=" + user_first_name + ", user_last_name=" + user_last_name
				+ ", user_email=" + user_email + ", user_role_id=" + user_role_id + "]";
	}
	

	public int getErs_users_id() {
		return ers_users_id;
	}


	public void setErs_users_id(int ers_users_id) {
		this.ers_users_id = ers_users_id;
	}


	public String getErs_username() {
		return ers_username;
	}


	public void setErs_username(String ers_username) {
		this.ers_username = ers_username;
	}


	public String getErs_password() {
		return ers_password;
	}


	public void setErs_password(String ers_password) {
		this.ers_password = ers_password;
	}


	public String getUser_first_name() {
		return user_first_name;
	}


	public void setUser_first_name(String user_first_name) {
		this.user_first_name = user_first_name;
	}


	public String getUser_last_name() {
		return user_last_name;
	}


	public void setUser_last_name(String user_last_name) {
		this.user_last_name = user_last_name;
	}


	public String getUser_email() {
		return user_email;
	}


	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}



	public int getUser_role_id() {
		return user_role_id;
	}



	public void setUser_role_id(int user_role_id) {
		this.user_role_id = user_role_id;
	}



	public User_Roles getUser_role_id_fk() {
		return user_role_id_fk;
	}

	
	public void setUser_role_id_fk(User_Roles user_role_id_fk) {
		this.user_role_id_fk = user_role_id_fk;
	}
	
}
