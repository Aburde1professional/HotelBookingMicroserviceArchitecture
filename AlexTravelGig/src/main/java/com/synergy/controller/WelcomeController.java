package com.synergy.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

@Controller
public class WelcomeController {
	
	@RequestMapping(value = "/welcome")
	public String welcome(ModelMap model) {
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
		  String userName = ((UserDetails)principal).getUsername();
		  model.addAttribute("userName", userName);
		} else {
		  String userName = principal.toString();
		  model.addAttribute("userName", userName);
		}
		return "hotel";
	}
	
	@RequestMapping(value = "/displayGuestBookings", method = RequestMethod.GET)
	public String displayGuestBookings(ModelMap model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
		  String userName = ((UserDetails)principal).getUsername();
		  model.addAttribute("userName", userName);
		} else {
		  String userName = principal.toString();
		  model.addAttribute("userName", userName);
		}
		return "userBookingDetails";
	}
}