package com.app;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.AbstractRequestLoggingFilter;

@Component
@Order(1)
public class AdtorpFilter extends AbstractRequestLoggingFilter{

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	protected void beforeRequest(HttpServletRequest request, String message) {
		// TODO Auto-generated method stub
		log.info("remote address {}",request.getRemoteHost());
		log.info("request message {}",message);
	}

	@Override
	protected void afterRequest(HttpServletRequest request, String message) {
		// TODO Auto-generated method stub
		log.info("remote address {}",request.getRemoteHost());
		log.info("request message {}",message);
	}
	
	

}
