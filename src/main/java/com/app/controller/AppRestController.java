package com.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.AppBean;
import com.app.ResponseBean;

@RestController
public class AppRestController {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@PostMapping("/service1")
	public ResponseBean<AppBean> service1(@RequestBody AppBean appBean) {
		
		ResponseBean<AppBean> result = new ResponseBean<>();
		if("1350100204282".equals(appBean.getNatId())) {
			result.setStatus("0000");
		} else {
			result.setStatus("9999");
		}
		
		result.setData(appBean);
		
		log.info(result.toString());
		
		return result;
	}
	
	
}
