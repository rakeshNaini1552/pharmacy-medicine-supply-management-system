package com.authorization;

import org.junit.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;

import com.authorization.AuthorizationServiceApplication;

@SpringBootTest
public class AuthorizationServiceApplicationTests {



	@Test
	public void main() {
		
		SpringApplication.run(AuthorizationServiceApplication.class, new String[] {});
	}

}
