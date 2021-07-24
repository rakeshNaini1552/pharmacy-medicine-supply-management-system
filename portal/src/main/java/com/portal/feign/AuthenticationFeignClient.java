package com.portal.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.portal.vo.UserLoginCredentialVO;
import com.portal.vo.UserTokenVO;


@FeignClient(url = "http://localhost:8084", name = "authorization-service")
public interface AuthenticationFeignClient {

	@PostMapping(path = "/login")
	public ResponseEntity<UserTokenVO> login(@RequestBody UserLoginCredentialVO usercredentials);

	@GetMapping(path = "/validate")
	public boolean verifyToken(@RequestHeader(name = "Authorization", required = true) String token);

}
