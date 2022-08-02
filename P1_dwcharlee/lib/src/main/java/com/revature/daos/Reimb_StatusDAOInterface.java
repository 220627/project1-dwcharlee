package com.revature.daos;

import com.revature.models.Reimb_Status;

public interface Reimb_StatusDAOInterface {

		//a method that returns a Role object from the database given the Role's ID
		public Reimb_Status getReimb_StatusById(int id);
			
		//a method that updates the salary data for a given role
		public boolean updateReimb_Status(int id, String status);
			
		//ctrl + shift + o to import unimported Classes 
}
