package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.models.Reimb_Status;
import com.revature.utils.ConnectionUtil;

public class Reimb_StatusDAO implements Reimb_StatusDAOInterface {

	@Override
	public Reimb_Status getReimb_StatusById(int id) {
		//use a try-with-resources to open a DB connection object
		try(Connection conn = ConnectionUtil.getConnection()){
			
			//String that lays out the SQL query we want to run
			//This String has a parameter for role_id, which we'll fill with our PreparedStatement
			String sql = "select * from ers_reimbursement_status where reimb_status_id = ?;";
			
			//we need a PreparedStatment object to fill in the variable above with setInt().
			PreparedStatement ps = conn.prepareStatement(sql);
			
			//insert a value for the variable in out SQL statement
			ps.setInt(1, id); //1 == the first (and only) question mark, id == the parameter sent in to this method
			
			//The data returned from a SELECT statement is known as a ResultSet
			//We need a ResultSet OBJECT to hold our incoming data.
			ResultSet rs = ps.executeQuery(); //execute the query into our new ResultSet
			
			//while there are records in the ResultSet...
			while(rs.next()) {
				
				//we need to use the data in the ResultSet to fill a Role all-args constructor
				//note we're getting data by calling each column name of our Role table 
				Reimb_Status status = new Reimb_Status(
						rs.getInt("reimb_status_id"),
						rs.getString("reimb_status")
					); 
				
				return status; //return the Role data to the user!	
			}
			
		} catch (SQLException e) {
			System.out.println("Get Reimb_Status failed"); //tell the console it failed
			e.printStackTrace(); //print an error log for debugs
		}
		return null;
	}

	@Override
	public boolean updateReimb_Status(int id, String status) {
		try(Connection conn = ConnectionUtil.getConnection()){
			
			//SQL String for our UPDATE command
			String sql = "update ers_reimbursement_status set reimbursement_status = ? where reimb_status_id = ?;";
			
			//create our PreparedStatement to fill in the variables
			PreparedStatement ps = conn.prepareStatement(sql);
			
			//input the appropriate values into our PreparedStatement
			ps.setString(1, status);
			ps.setInt(2, id);
			
			//execute the update!
			ps.executeUpdate();
			
			//tell the console the update was successfully 
			System.out.println(id + " has been updated to " + status);
			
			//if it succeeds, return true
			return true;
		} catch (SQLException e) {
			System.out.println("FAILED TO UPDATE");
			e.printStackTrace();
		}
		return false; //if update fails, return false
	}
}
