package com.portal.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.portal.service.AuthFeignService;
import com.portal.vo.UserLoginCredentialVO;
import com.portal.vo.UserTokenVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/user")
public class LoginController {

	@Autowired
	private AuthFeignService authfeignService;

	@GetMapping("/login")
	public ModelAndView userLogin() {

		ModelAndView modelAndView = new ModelAndView("login");
		modelAndView.addObject("loginMessage", "Login");
		return modelAndView;
	}

	
	@GetMapping("/home")
	public String home() {

		return "homepage";
	}

	@PostMapping("/homepage")
	public ModelAndView userLogin(@ModelAttribute("usercredentials") UserLoginCredentialVO usercredentials,
			HttpSession session) {
		log.debug("username{}: ", usercredentials.getUserid());
		ResponseEntity<?> response = null;

		try {
			response = authfeignService.getToken(usercredentials);
		} catch (Exception e) {
			log.error("Invalid credentials");
			ModelAndView modelAndView = new ModelAndView("login");
			modelAndView.addObject("loginMessage", "Invalid credentials");
			return modelAndView;
		}
		log.debug("Response[]: ", response);
		log.info("Getting body from response entity");
		UserTokenVO userToken = (UserTokenVO) response.getBody();
		log.debug("token[]:", userToken.getAuthToken());
		log.debug("userToken[]: ", userToken);
		session.setAttribute("token", userToken.getAuthToken());
		log.debug("session{}:", session.toString());
		ModelAndView modelAndView = new ModelAndView("homepage");
		return modelAndView;

	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.setAttribute("token", null);
		return "logout";
	}

}
