package com.app.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {
	
	@GetMapping("/jquery")
	public String index(Model model) {
		
		model.addAttribute("date", new Date().getTime());
		
		return "jquery";
	}
	

}
