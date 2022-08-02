package com.revature.daos;

import com.revature.models.Reimb_Type;

public interface Reimb_TypeDAOInterface {

	//a method that returns a Role object from the database given the Role's ID
	public Reimb_Type getReimb_TypeById(int id);
		
	//a method that updates the salary data for a given role
	public boolean updateReimb_Type(int id, String type);
		
	//ctrl + shift + o to import unimported Classes 
}
