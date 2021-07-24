package com.authorization.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.authorization.exception.UserNotFoundException;
import com.authorization.model.MyUser;
import com.authorization.repository.MyUserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CustomerDetailsService implements UserDetailsService {

	@Autowired
	private MyUserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String uid) throws UsernameNotFoundException {
		log.debug("userid ",uid);
		MyUser user = userRepo.findById(uid).orElseThrow(() -> new UserNotFoundException(
				"YOU ARE NOT AN AUTHENTICATED USER. PLEASE TRY TO LOGIN WITH THE VALID CREDENTIALS"));
		log.debug("user is loaded:",user);
		return new User(user.getUserid(), user.getPassword(), new ArrayList<>());

	}

}
