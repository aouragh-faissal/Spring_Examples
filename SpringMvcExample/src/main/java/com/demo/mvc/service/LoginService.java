package com.demo.mvc.service;

import org.springframework.stereotype.Service;

@Service
public class LoginService {
	
	public boolean checkValidateUser(String name,String password) {
		if(name.equals("Muhammed") && password.equals("1234")) {
			return true;
		}else {
			return false;
		}
	}

	public LoginService() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
