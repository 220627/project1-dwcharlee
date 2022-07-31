package com.revature.controllers;

import com.revature.daos.User_RolesDAO;

import io.javalin.http.Handler;

public class Users_RolesController {
	
		//we need a RoleDAO to use its methods
		User_RolesDAO rDAO = new User_RolesDAO();
		
		//this Handler will get the HTTP PUT request to update a Role salary.
		public Handler updateUser_RolesStatus = (ctx) -> {
			
			//String to hold the role title (which comes in as a PATH PARAMETER)
			String status = ctx.pathParam("status"); //pathParam() gives us the value the user sends in as a path parameter
			
			//int to hold the new Role salary, which the user will include in the BODY of the HTTP Request
			int id = Integer.parseInt(ctx.body()); //we need to use parseInt here, because ctx.body() returns a String
			
			//if the update DAO method returns true (which means successful)..
			if(rDAO.updateUser_RolesStatus(id, status)) {
				ctx.status(202); //202 stands for "accepted"
			} else {
				ctx.status(406); //406 stands for "not acceptable"
			}
		};
}
