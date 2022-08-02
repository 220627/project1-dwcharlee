package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.models.Reimbursement;
import com.revature.utils.ConnectionUtil;

public class ReimbursementDAO implements ReimbursementDAOInterface {

	@Override
	public boolean insertReimbursement(Reimbursement reimb) {
		//at the top of EVERY DAO METHOD, we need to open a database connection.
		try(Connection conn = ConnectionUtil.getConnection()){
			
		//First, we need out SQL String that represents the INSERT statement we want to send to the DB
		//There are variables here, and we can fill them out thanks to a PreparedStatement object
		//The question marks are how we indicate that it's a value that we'll fill below
		String sql = "insert into ers_reimbursement (reimb_id, reimb_amount, reimb_submitted, reimb_author, reimb_resolver, reimb_status_id, reimb_type_id) values (?, ?, ?, ?, ?, ?, ?);";
			
		//Instantiate a PreparedStatement to fill in the variables of our SQL String (the ?s).
		//we use the prepareStatement() method from our Connection object to do this.
		PreparedStatement ps = conn.prepareStatement(sql);
			
		//fill in the values of our variables using ps.setXYZ()
		//these methods take two parameters - the variable we'll filling, and the value to fill it with
		ps.setInt(1, reimb.getReimb_amount());
		ps.setInt(2, reimb.getReimb_submitted()); //by "1" here, we're referring to the first question mark in the SQL String.
		ps.setInt(3, reimb.getReimb_author());
		ps.setInt(4, reimb.getReimb_resolver());//PreparedStatement doesn't have a setRole() method, 
		ps.setInt(5, reimb.getReimb_status_id());//so we can just use the id here, because it takes an int on the database side
		ps.setInt(6, reimb.getReimb_type_id());
		System.out.println(ps);
		
		//we've created the SQL String and filled it with data - now we need to EXECUTE THE STATEMENT!
		ps.executeUpdate(); //This is what actually sends our SQL off to the database.
		
		//Tell the user the insert was successful
		System.out.println("User " + reimb.getReimb_id() + " was added!");
		
		return true; //if the update is successful, true will get returned
			
		} catch (SQLException e) { //if anything goes wrong, this SQLException will get thrown
			System.out.println("INSERT REIMBURSEMENT FAILED"); //tell the console we failed
			e.printStackTrace(); //print out the error log, which we'll need for debugging
		}
		
		return false; //if it fails, we'll get here (instead of the "return true" in the try block) 
		
	}

	@Override
	public ArrayList<Reimbursement> getReimbursement() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteReimbursement(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateReimbursement(int id, int amount) {
		// TODO Auto-generated method stub
		
	}

}
