package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.revature.models.User_Roles;
import com.revature.models.Users;
import com.revature.utils.ConnectionUtil;

public class UsersDAO implements UsersDAOInterface{

		//method that inserts data into the DB
		@Override
		public boolean insertUsers(Users user) {
			
			//at the top of EVERY DAO METHOD, we need to open a database connection.
			try(Connection conn = ConnectionUtil.getConnection()){
				
			//First, we need out SQL String that represents the INSERT statement we want to send to the DB
			//There are variables here, and we can fill them out thanks to a PreparedStatement object
			//The question marks are how we indicate that it's a value that we'll fill below
			String sql = "insert into ers_users (ers_username, ers_password, first_name, last_name, user_email, user_role_id) values (?, ?, ?, ?, ?, ?);";
				
			//Instantiate a PreparedStatement to fill in the variables of our SQL String (the ?s).
			//we use the prepareStatement() method from our Connection object to do this.
			PreparedStatement ps = conn.prepareStatement(sql);
				
			//fill in the values of our variables using ps.setXYZ()
			//these methods take two parameters - the variable we'll filling, and the value to fill it with
			ps.setString(1, user.getErs_username());
			ps.setString(2, user.getErs_password()); //by "1" here, we're referring to the first question mark in the SQL String.
			ps.setString(3, user.getUser_first_name());
			ps.setString(4, user.getUser_last_name());//PreparedStatement doesn't have a setRole() method, 
			ps.setString(5, user.getUser_email());//so we can just use the id here, because it takes an int on the database side
			ps.setInt(6, user.getUser_role_id());
			System.out.println(ps);
			
			//we've created the SQL String and filled it with data - now we need to EXECUTE THE STATEMENT!
			ps.executeUpdate(); //This is what actually sends our SQL off to the database.
			
			//Tell the user the insert was successful
			System.out.println("User " + user.getErs_username() + " was added!");
			
			return true; //if the update is successful, true will get returned
				
			} catch (SQLException e) { //if anything goes wrong, this SQLException will get thrown
				System.out.println("INSERT USER FAILED"); //tell the console we failed
				e.printStackTrace(); //print out the error log, which we'll need for debugging
			}
			
			return false; //if it fails, we'll get here (instead of the "return true" in the try block) 
			
		} //end of insertUser()

		
		//There is a TDD test written for this method (not necessary to do this, just wanted to demonstrate TDD0
		//This method gets all user from the DB
		@Override
		public ArrayList<Users> getUser() {
			
			try(Connection conn = ConnectionUtil.getConnection()){
				
				//A String that will represent our SQL statement
				String sql = "select * from ers_users;";
				
				//no variables so we don't need a PreparedStatement!
				//What we'll use instead is a Statement object to execute our query
				Statement s = conn.createStatement(); 
				//IMPORT STATEMENT FROM JAVA.SQL, NOT JAVA.BEANS!!!!!!!!!!!!!!!!!!!!!
				
				//remember we need to execute our query into a ResultSet object, which will hold the incoming data
				ResultSet rs = s.executeQuery(sql);
				
				//Instantiate an empty ArrayList to hold our User data (remember, java can't read SQL).
				ArrayList<Users> usersList = new ArrayList<>();
				
				//use rs.next() in a while loop to create Employee object and populate our ArrayList with them.
				while(rs.next()) { //remember, rs.next() returns "true" if there are more results that we haven't looked at yet
					
					//Create a new Users object from each record in the ResultSet
					//we're using the all-args constructor!!
					Users u = new Users(
								rs.getInt("ers_users_id"),
								rs.getString("ers_username"),
								rs.getString("ers_password"),
								rs.getString("user_first_name"),
								rs.getString("user_last_name"),
								rs.getString("user_email"),
								rs.getInt("user_role_id")
							);

					int roleFK = rs.getInt("user_role_id");
					
					//Instantiate a RoleDAO so we can use getRoleById
					User_RolesDAO rDAO = new User_RolesDAO();
					
					//get a Role object using the int that we populate with rs.getInt()!!!!
					User_Roles r = rDAO.getUser_RolesById(roleFK);
					
					//use the SETTER of our Users class to set the User_Roles object to the one we got from the DB
					u.setUser_role_id_fk(r);
					//thanks to this setter, we have a FULLY INITIALIZED Users object
					
					//add the new Employee to our ArrayList. For every employee returned, we put it in the ArrayList
					usersList.add(u); 
					
				} //end of while loop
				
				//once the while loop breaks (once rs.next() == false), return the ArrayList
				return usersList;
				
			} catch (SQLException e) {
				System.out.println("SOMETHING WENT WRONG GETTING EMPLOYEES"); //tell the console it failed
				e.printStackTrace(); //print the error log for debugging
			}
			
			return null; //so the compiler doesn't complain when our REAL return is in a try block
			
		} //end of get all 
		
		@Override
		public void deleteUser(int id) {
			
			try(Connection conn = ConnectionUtil.getConnection()){
				
				//SQL String that we want to send to the DB
				String sql = "delete from ers_users where ers_users_id = ?;";
				
				//instantiate our PreparedStatement to fill in the variable
				PreparedStatement ps = conn.prepareStatement(sql);
				
				ps.setInt(1, id);
				
				//ps.executeUpdate() to send our delete to the DB
				ps.executeUpdate();
				
				//let the user know that the dreams of their former employee have been crushed
				System.out.println("Get outta here, user #" + id);
				
			} catch (SQLException e) {
				System.out.println("YOU CAN'T GET RID OF ME!");
				e.printStackTrace();
			}	
		}
}
