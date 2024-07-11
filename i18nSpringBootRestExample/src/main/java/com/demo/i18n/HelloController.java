package com.demo.i18n;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@Autowired
	MessageSource messageSource;

	 
	  @GetMapping("/api/greet/{username}")
	  public String greet(@PathVariable String username, 
	    @RequestHeader(name = "Accept-Language", required = false) Locale locale) {

	    String greetingMessage = messageSource.getMessage("greeting.message1", 
	    		new Object[]{username}, locale);
	    return String.format("Locale: %s, Message: %s", locale, greetingMessage);
	  }

}
