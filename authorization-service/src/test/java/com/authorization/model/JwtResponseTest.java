package com.authorization.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.authorization.jwt.JwtResponse;

import org.junit.Before;
import org.junit.Test;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= {JwtResponse.class})
public class JwtResponseTest {

	@Mock
	public JwtResponse response;
	
	@Before
	public void setUp() throws Exception{
		response=new JwtResponse();
		response.setUserid("admin");
		response.setUsername("admin");
		response.setValid(false);
	}
		
	@Test
	public void AllArgConstTest() {
		JwtResponse auth=new JwtResponse("admin","admin",false);
		assertEquals(response.getUsername(), auth.getUsername());
		assertEquals("admin", auth.getUserid());
		}
	
	
	@Test
	public void testNoArgsConstructor() {
		
		JwtResponse response = new JwtResponse();
		assertEquals(response, response);
	}
	@Test
	public void testSetters() {
		response.setUsername("admin");
		response.setUserid("admin");
		response.setValid(true);
		assertEquals("admin", response.getUsername());
	}
	
	@Test
	public void testEqualsMethod() {
		boolean equals = response.equals(response);
		assertTrue(equals);
	}

}
