package com.authorization.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.authorization.jwt.JwtRequest;
import com.authorization.jwt.JwtResponse;
import com.authorization.jwt.JwtUtil;
import com.authorization.model.UserToken;
import com.authorization.repository.MyUserRepository;
import com.authorization.service.CustomerDetailsService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
public class JwtAuthenticationController {

	@Autowired
	private JwtUtil jwtutil;

	@Autowired
	private CustomerDetailsService custdetailservice;

	@Autowired
	private MyUserRepository userservicerepo;

	@Autowired
	private AuthenticationManager authenticationManager;


	private void authenticate(String userid, String password) throws Exception {
		log.info("begin");
		log.debug("userid and password", userid, password);
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userid, password));
		} catch (DisabledException e) {
			
			log.error("exception: INVALID USER");
			throw new Exception("Invalid User", e);
		} catch (BadCredentialsException e) {
			
			log.error("exception: INVALID USER");
			throw new Exception("INVALID_CREDENTIALS", e);
		}
		log.info("end");
	}

	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<UserToken> login(@RequestBody JwtRequest userlogincredentials) throws Exception {
		log.info("begin");
		authenticate(userlogincredentials.getUserid(), userlogincredentials.getPassword());
		final UserDetails userdetails = custdetailservice.loadUserByUsername(userlogincredentials.getUserid());
		log.debug("USER DEATILS: ",userdetails);
		log.info("end");
		return new ResponseEntity<>(new UserToken(userlogincredentials.getUserid(), jwtutil.generateToken(userdetails)),
				HttpStatus.OK);

	}


	@RequestMapping(value = "/validate", method = RequestMethod.GET)
	public ResponseEntity<?> getValidity(@RequestHeader("Authorization") final String token) {
		log.info("begin");
		log.debug("token :", token);
		String newToken = token.substring(7);
		log.debug("token after removing bearer: ", newToken);
		JwtResponse jwtResponse = new JwtResponse();
		if (jwtutil.validateToken(newToken)) {
			log.info("Token is valid");
			jwtResponse.setUserid(jwtutil.extractUsername(newToken));
			jwtResponse.setValid(true);
			jwtResponse
					.setUsername((userservicerepo.findById(jwtutil.extractUsername(newToken)).orElse(null).getUsername()));
		log.debug("jwt response : ",jwtResponse);
		} else {
			log.error("token is invalid");
			jwtResponse.setValid(false);
		}
		log.info("end");
		
		return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
	}

}
