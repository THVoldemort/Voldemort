package com.voldemort.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
	
	@GetMapping("/")
	public String welcome(HttpServletRequest req) {
		String url ="http://"+ req.getServerName() + ":" + req.getServerPort() + "/swagger-ui.html";
		return "<center style='margin-top:100px'><a href = '"+ url +"'/>Welcome to Voldemort Study ^___^</a><center>";
	}
	
}
