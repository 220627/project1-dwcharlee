package com.revature.daos;

import com.revature.models.User_Roles;

public interface User_RolesDAOInterface {

		//a method that returns a Role object from the database given the Role's ID
		public User_Roles getUser_RolesById(int id);
		
		//a method that updates the salary data for a given role
		public boolean updateUserRole(int id, String role);
		
		//ctrl + shift + o to import unimported Classes 
	
}
