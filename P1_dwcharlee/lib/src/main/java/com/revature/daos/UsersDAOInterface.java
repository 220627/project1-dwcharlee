package com.revature.daos;

import java.util.ArrayList;

import com.revature.models.Users;

public interface UsersDAOInterface {
	
		//A method to insert a new user
		boolean insertUsers(Users user);
		
		//A method to get all user
		ArrayList<Users> getUser();
		
		
		//A method to delete user
		public void deleteUser(int id);
		
		//a method that returns a Role object from the database given the Role's ID
		public Users getUsersById(int id);
		
}
