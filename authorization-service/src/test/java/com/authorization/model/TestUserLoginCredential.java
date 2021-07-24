package com.authorization.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.authorization.jwt.JwtRequest;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= {JwtRequest.class})
public class TestUserLoginCredential {

	@Mock
	public JwtRequest userLogin;
	
	@Before
	public void setUp() {
		userLogin=new JwtRequest("admin","admin");
	}
	
	@Test
	public void testAllArgumentConstructor() {
		JwtRequest userLog =new JwtRequest("admin","admin");
		assertEquals("admin",userLog.getUserid());
	}
	
	@Test
	public void testEquals() {
		boolean res=userLogin.equals(userLogin);
		assertTrue(res);
	}
	
	@Test
	public void testNoArgConstructor() {
		JwtRequest userLoginCredentials=new JwtRequest();
		assertEquals(userLoginCredentials,userLoginCredentials);
	}

}
