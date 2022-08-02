package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.models.User_Roles;
import com.revature.utils.ConnectionUtil;

public class User_RolesDAO implements User_RolesDAOInterface{
	
	//This method takes in an int, and returns the Role with the role_id that matches the given int.
		@Override
		public User_Roles getUser_RolesById(int id) {
			
			//use a try-with-resources to open a DB connection object
			try(Connection conn = ConnectionUtil.getConnection()){
				
				//String that lays out the SQL query we want to run
				//This String has a parameter for role_id, which we'll fill with our PreparedStatement
				String sql = "select * from ers_user_roles where ers_users_role_id = ?;";
				
				//we need a PreparedStatment object to fill in the variable above with setInt().
				PreparedStatement ps = conn.prepareStatement(sql);
				
				//insert a value for the variable in out SQL statement
				ps.setInt(1, id); //1 == the first (and only) question mark, id == the parameter sent in to this method
				
				//The data returned from a SELECT statement is known as a ResultSet
				//We need a ResultSet OBJECT to hold our incoming data.
				ResultSet rs = ps.executeQuery(); //execute the query into our new ResultSet
				
				//the above code gets our data, and stores in a ResultSet object
				//we have to ITERATE through our ResultSet to create a new Role object on the Java side (Java can't read SQL)
				
				//while there are records in the ResultSet...
				while(rs.next()) {
					
					//we need to use the data in the ResultSet to fill a Role all-args constructor
					//note we're getting data by calling each column name of our Role table 
					User_Roles role = new User_Roles(
							rs.getInt("ers_user_role_id"),
							rs.getString("user_role")
						); 
					
					return role; //return the Role data to the user!
					
					//In this case, we know we're only going to get one role, so we can return from within the while loop
					//If we were getting multiple records (like in get all employees), we would do it a little different.	
				}
				
			} catch (SQLException e) {
				System.out.println("Get User_Roles failed"); //tell the console it failed
				e.printStackTrace(); //print an error log for debugs
			}
			
			return null;
			
		} //end of select by id method

		@Override
		public boolean updateUserRole(int id, String role) {
			
			try(Connection conn = ConnectionUtil.getConnection()){
				
				//SQL String for our UPDATE command
				String sql = "update ers_user_roles set user_role = ? where ers_user_role_id = ?;";
				
				//create our PreparedStatement to fill in the variables
				PreparedStatement ps = conn.prepareStatement(sql);
				
				//input the appropriate values into our PreparedStatement
				ps.setString(1, role);
				ps.setInt(2, id);
				
				//execute the update!
				ps.executeUpdate();
				
				//tell the console the update was successfully 
				System.out.println(id + " has been updated to " + role);
				
				//if it succeeds, return true
				return true;
			} catch (SQLException e) {
				System.out.println("FAILED TO UPDATE");
				e.printStackTrace();
			}
			return false; //if update fails, return false	
		}
}
