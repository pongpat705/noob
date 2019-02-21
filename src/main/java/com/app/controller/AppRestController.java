package com.app.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.AppBean;

@RestController
public class AppRestController {

	
	@PostMapping("/service1")
	public AppBean service1(@RequestBody AppBean appBean) {
		
		System.out.println(appBean.toString());
		
		return appBean;
	}
	
	
}
