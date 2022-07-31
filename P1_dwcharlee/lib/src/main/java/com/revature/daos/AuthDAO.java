package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.models.User;
import com.revature.utils.ConnectionUtil;

public class AuthDAO {

	//I should've made an Interface but it's ok.
	
	//This DAO would likely have other methods like register user, or update user info
	//but we just need a login for P0 and P1.
	
	public User login(String username, String password) {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			
		String sql = "select * from users where username = ? and password = ?;";
			
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ps.setString(1, username);
		ps.setString(2, password);
		
		ResultSet rs = ps.executeQuery();	
		
		System.out.println(rs);
		//if anything gets returned at all, we know a user exists with that username/password pair. so we can return true
		if(rs.next()) {
		
			User u = new User(
						rs.getInt("user_id"),
						rs.getString("username"),
						rs.getString("password")
					);
			return u;
		}	
		} catch (SQLException e) {
			System.out.println("LOGIN FAILED");
			e.printStackTrace();
		}
		return null;		
	}
	
}
