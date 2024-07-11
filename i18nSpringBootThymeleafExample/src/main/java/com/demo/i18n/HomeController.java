package com.demo.i18n;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/i18n")
public class HomeController {
	
	@GetMapping("/home")
	public String home() {
		return "home";
	}

}
