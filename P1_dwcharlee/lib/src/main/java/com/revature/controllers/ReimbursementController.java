package com.revature.controllers;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.revature.daos.ReimbursementDAO;
import com.revature.daos.UsersDAO;
import com.revature.models.Reimbursement;
import com.revature.models.Users;

import io.javalin.http.Handler;

public class ReimbursementController {

	//We need an EmployeeDAO object to use its methods
			ReimbursementDAO rDAO = new ReimbursementDAO();
			
			//This Handler will get the HTTP GET Request for all employees, then collect the data and send it back in an HTTP Response 
			public Handler getReimbursementHandler = (ctx) -> {
			
				//what is ctx? it's the Context object! 
				//This object contains a bunch of method that we can use to take in HTTP Requests and send HTTP Responses
				
				if(AuthController.ses != null) { //if the user is logged in, they can access this functionality
				
				//We need an ArrayList of Employees, courtesy of our EmployeeDAO
				ArrayList<Reimbursement> reimb = rDAO.getReimbursement();
				
				//create a GSON object, which has methods to convert our Java object into JSON
				Gson gson = new Gson();
				
				//use the GSON .toJson() method to turn our Java into JSON String (JSON is always in String format on the Java side)
				String JSONreimb = gson.toJson(reimb); //employees is the ArrayList of our employee data
				
				//use ctx to provide an HTTP response containing our JSON string of employees (which is what was requested)
				
				ctx.result(JSONreimb); //ctx.result() sends a response back (this is where our data goes)
				
				ctx.status(200); //ctx.status() sets the HTTP status code. 200 stands for "OK", the generic success code.
				
				} else { //if the user is NOT logged in (aka AuthController.ses wil be null)
					ctx.result("YOU ARE NOT LOGGED IN!! *SMACK*");
					ctx.status(401); //"forbidden" access code
				}
				
			}; //semicolon after curly brace? That's lambdas for you.
			
			
			//This Handler will get the HTTP POST Request for inserting employees, then send the employee data to the DB.
			public Handler insertReimbHandler = (ctx) -> {
				
				//With POST requests, we have some data coming in, which we access with ctx.body();
				//body?? it refers to the BODY of the HTTP Request (which is where the incoming data is found)
				String body = ctx.body(); //store the data in a String 
				
				//create a new GSON object to make JSON <-> Java conversions
				Gson gson = new Gson();
				
				Reimbursement newReimb = gson.fromJson(body,  Reimbursement.class);
						
				if(rDAO.insertReimbursement(newReimb)) {
					ctx.status(202);
					
				} else {
					ctx.status(406);
				}
				
			};
			
			public Handler deleteReimbHandler = (ctx) -> {
				
				int youreFired = Integer.valueOf(ctx.pathParam("id"));
				
				rDAO.deleteReimbursement(youreFired);
				
				ctx.result("Reimb " + youreFired + " Voided");
				ctx.status(200);
			};
}
