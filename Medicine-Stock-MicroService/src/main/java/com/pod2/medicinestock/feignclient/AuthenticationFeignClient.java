package com.pod2.medicinestock.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.pod2.medicinestock.model.JwtResponse;

@FeignClient(name = "authorization-service", url ="http://localhost:8084")
public interface AuthenticationFeignClient {


	@GetMapping(path = "/validate")
	public JwtResponse verifyToken(@RequestHeader(name = "Authorization", required = true) String token);

}