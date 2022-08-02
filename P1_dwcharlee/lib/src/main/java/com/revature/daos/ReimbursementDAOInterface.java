package com.revature.daos;

import java.util.ArrayList;

import com.revature.models.Reimbursement;

public interface ReimbursementDAOInterface {

	//A method to insert a new reimbursement
	public boolean insertReimbursement(Reimbursement reimb);
			
	//A method to get all reimbursement
	public ArrayList<Reimbursement> getReimbursement();
			
	//A method to delete reimbursement
	public void deleteReimbursement(int id);
	
	//A method to update reimbursement
	public void updateReimbursement(int id, int amount);
			
}
