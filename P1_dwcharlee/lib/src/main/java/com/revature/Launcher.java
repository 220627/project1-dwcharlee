package com.revature;


import java.sql.Connection;
import java.sql.SQLException;

import com.revature.controllers.AuthController;
import com.revature.controllers.EmployeeController;
import com.revature.controllers.ReimbursementController;
import com.revature.controllers.RoleController;
import com.revature.controllers.Users_RolesController;
import com.revature.daos.AuthDAO;
import com.revature.daos.EmployeeDAO;
import com.revature.daos.RoleDAO;
import com.revature.models.Employee;
import com.revature.models.Role;
import com.revature.utils.ConnectionUtil;

import io.javalin.Javalin;

public class Launcher {
	
	
	public static void main(String[] args) {
		
		System.out.println("========Welcome to the Krusty Krab Employee Management System========");
		
		
		try(Connection conn = ConnectionUtil.getConnection()){
			System.out.println("CONNECTION SUCCESSFUL :)");
		} catch (SQLException e) {
			//if the connection fails to open, catch the resulting SQLException and print the stack trace (error log)
			System.out.println("connection failed...");
			e.printStackTrace();
		} //end of the connection test
			
		
		//Typical Javalin syntax to CREATE a javalin object 
		Javalin app = Javalin.create(
				
				//the config lambda lets us specify certain configurations for our Javalin app.
				config -> {
					config.enableCorsForAllOrigins(); //this lets us process HTTP requests from any origin
				}
				).start(3000); //we need .start() to start our Java server on port 3000.
				//This port is important, because this is where we need to send requests to.
		
		//We need to make some endpoint handlers, which will take in requests and send them where they need to go
		//Javalin endpoint handlers are like a traffic cop to your Java server. They take HTTP traffic and direct it.
		
		//Instantiate an EmployeeController so that we can use its handlers
		ReimbursementController rc = new ReimbursementController();
		
		//Instantiate a RoleController so that we can use its handler
		Users_RolesController urc = new Users_RolesController();
		
		//Instantiate an AuthController... you know why hopefully :)
		AuthController ac = new AuthController();
		
		//endpoint handlers below--------------------------
		
		//app.get() is the javalin method that takes in GET requests. It will return all employees from the DB.
		//this handler takes in GET requests ending in /employees, and sends them to the getEmployeesHandler
		//the request in postman would look something like: localhost:3000/employees
		app.get("/reimbursement", rc.getReimbursementHandler);
		//what does /employees relate to? it's something we define. we want requests ending in /employees to get all employees
		
		//app.post() is the javalin method that takes in POST requests. It will insert employee data into the DB.
		//how come we can have two endpoints of "/employees"? that's because one is for a GET, while the other is a POST
		app.post("/reimbursement", rc.insertReimbHandler);
		
		//app.put() is a javalin method that takes PUT requests. 
		//It will take in two pieces of data: the role title (in the path parameter) and the salary (in the Request body)
		//:title? This is a PATH PARAMETER. Whatever the user inserts here in the request will be used in the controller
		app.put("/ers_user_roles/:status", urc.updateUser_RolesStatus);
		
		app.delete("/delete/:id", rc.deleteReimbHandler);
	
		app.post("/login", ac.loginHandler);
		
	} //end of main method
	
}
