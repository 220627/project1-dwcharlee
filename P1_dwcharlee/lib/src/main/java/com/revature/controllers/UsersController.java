package com.revature.controllers;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.revature.daos.UsersDAO;
import com.revature.models.Users;

import io.javalin.http.Handler;

public class UsersController {

		//We need an EmployeeDAO object to use its methods
		UsersDAO eDAO = new UsersDAO();
		
		//This Handler will get the HTTP GET Request for all employees, then collect the data and send it back in an HTTP Response 
		public Handler getUsersHandler = (ctx) -> {
		
			//what is ctx? it's the Context object! 
			//This object contains a bunch of method that we can use to take in HTTP Requests and send HTTP Responses
			
			if(AuthController.ses != null) { //if the user is logged in, they can access this functionality
			
			//We need an ArrayList of Employees, courtesy of our EmployeeDAO
			ArrayList<Users> users = eDAO.getUser();
			
			//create a GSON object, which has methods to convert our Java object into JSON
			Gson gson = new Gson();
			
			//use the GSON .toJson() method to turn our Java into JSON String (JSON is always in String format on the Java side)
			String JSONusers = gson.toJson(users); //employees is the ArrayList of our employee data
			
			//use ctx to provide an HTTP response containing our JSON string of employees (which is what was requested)
			
			ctx.result(JSONusers); //ctx.result() sends a response back (this is where our data goes)
			
			ctx.status(200); //ctx.status() sets the HTTP status code. 200 stands for "OK", the generic success code.
			
			} else { //if the user is NOT logged in (aka AuthController.ses wil be null)
				ctx.result("YOU ARE NOT LOGGED IN!! *SMACK*");
				ctx.status(401); //"forbidden" access code
			}
			
		}; //semicolon after curly brace? That's lambdas for you.
		
		
		//This Handler will get the HTTP POST Request for inserting employees, then send the employee data to the DB.
		public Handler insertUsersHandler = (ctx) -> {
			
			//With POST requests, we have some data coming in, which we access with ctx.body();
			//body?? it refers to the BODY of the HTTP Request (which is where the incoming data is found)
			String body = ctx.body(); //store the data in a String 
			
			//create a new GSON object to make JSON <-> Java conversions
			Gson gson = new Gson();
			
			Users newUsers = gson.fromJson(body,  Users.class);
					
			if(eDAO.insertUsers(newUsers)) {
				ctx.status(202);
				
			} else {
				ctx.status(406);
			}
			
		};
		
		public Handler deleteUsersHandler = (ctx) -> {
			
			int youreFired = Integer.valueOf(ctx.pathParam("id"));
			
			eDAO.deleteUser(youreFired);
			
			ctx.result("User " + youreFired + " terminated");
			ctx.status(200);
		};
}
