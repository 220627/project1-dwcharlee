package com.revature.controllers;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.revature.models.LoginDTO;
import com.revature.models.User;
import com.revature.services.AuthService;

import io.javalin.http.Handler;

public class AuthController {

	public static Logger log = LogManager.getLogger();
	
	//we need an AuthService, remember the Service layer sits between the Controllers and DAOs
	AuthService as = new AuthService();
	
	//empty HttpSession object that will be filled upon successful login
	public static HttpSession ses;
	
	//login will be a POST request, check insertEmployeeHandler if any of this syntax eludes you
	public Handler loginHandler = (ctx) -> {
		
		String body = ctx.body();
		
		Gson gson = new Gson();
		
		LoginDTO lDTO = gson.fromJson(body, LoginDTO.class);
		
		User user = as.login(lDTO.getUsername(), lDTO.getPassword()); //this will either be a String or Null.
		
		System.out.println(user);
		
		if(user != null) { //if login is successful...

			//log that the user logged in successfully
			log.info("User Logged In!");
			
			ses = ctx.req.getSession(); //this is how we create new sessions
			
			String userJSON = gson.toJson(user);
			
			ctx.result(userJSON);
			ctx.status(202); //202 stands for "accepted"
			
		} else {
			//log that user failed to log in
			log.warn("User Failed to Login!");
			ctx.status(401); //401 stands for "unauthorized"
		}
	}; //end of handler
}
