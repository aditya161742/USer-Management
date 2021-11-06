package com.usermanagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usermanagement.helpers.Helpers;

@RestController
@RequestMapping("users/")
public class UsersController {
	
	@Autowired
	Helpers helpers;
	
	//@Autowired
	//private UserDetailsService userDetailsService;
	
	@GetMapping(path = "/dashboard")
	public String dashboard() {
		
		return "dashboard called";
	}
	
	
}
