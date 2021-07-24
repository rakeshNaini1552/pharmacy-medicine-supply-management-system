package com.pharmacysupply.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pharmacysupply.model.JwtResponse;


@FeignClient(name = "authorization-service", url ="http://localhost:8084")
public interface AuthenticationFeignClient {

	
	@RequestMapping(value = "/validate" ,method = RequestMethod.GET)
	public JwtResponse verifyToken(@RequestHeader(name = "Authorization", required = true) String token);
	
}
