package com.demo.mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;


@Controller
@SessionAttributes("name")
public class LoginController {
	
//
//	
//	@Autowired
//	LoginService loginservice;
//	
//	@RequestMapping(value="/login" , method = RequestMethod.GET)
//	public String loginPage() {
//		return "login";
//	}
//	
	@RequestMapping(value="/" , method = RequestMethod.GET)
	public String indexPage() {
		return "index";
	}
//	
	@RequestMapping(value="/index" , method = RequestMethod.GET)
	public String indexNavBarPage() {
	return "index";
	}
//
//
//
//	@RequestMapping(value="/login" , method = RequestMethod.POST)	 
//	public String getLoginInfo(
//			@RequestParam String name,
//			@RequestParam String password,
//			ModelMap model) {
//		//System.out.println(name);
//	
//	if(!loginservice.checkValidateUser(name, password)) {
//			model.put("errorMsg", "Check your name and password");
//			return "login";
//		}
//		
//		model.put("name", name);
//		model.put("password", password);
//		return "home";
//	}
//	
//	
//	
	@RequestMapping(value="/home") 
	public String homePage() {
		return "home";
	}

}
