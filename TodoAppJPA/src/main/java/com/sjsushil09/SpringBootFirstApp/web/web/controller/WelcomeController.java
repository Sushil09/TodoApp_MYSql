package com.sjsushil09.SpringBootFirstApp.web.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;


@Controller
//@SessionAttributes("name") //to store this info across multiple pages(We are getting this via Spring Security) 
public class WelcomeController {
	
	@RequestMapping(value="/",method=RequestMethod.GET)//to map to particular url routing & only applicable for get method
	//@ResponseBody//When we don't have view to render
	public String showWelcomePage(ModelMap map) {
		map.put("name",getLoggedinUserName());
		return "Welcome";
	}
	
	//To get userName via Spring Security
	private String getLoggedinUserName() {
		Object principal=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(principal instanceof UserDetails) {
			return ((UserDetails)principal).getUsername();
		}
		return principal.toString();
	}
	
	//Removed below method to replace it with security method
	/*
	 * @RequestMapping(value="/login",method=RequestMethod.POST)//to map to
	 * particular url routing & only applicable for post method
	 * //@ResponseBody//When we don't have view to render public String
	 * showWelcomPage(ModelMap map, @RequestParam String name, @RequestParam String
	 * password) { if(!loginservice.isValid(name, password)) return "login";
	 * 
	 * map.put("name",name); map.put("password",password); return "Welcome"; }
	 */
}
