package com.medicalrepresentative.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import com.medicalrepresentativeschedule.model.JwtResponse;

@RunWith(SpringRunner.class)
public class JwtResponseTest {

	@Mock
	JwtResponse jwtResponse;

	@Before
	public void setUp() throws Exception {
		jwtResponse = new JwtResponse("admin", "adminpass", true);
	}

	@Test
	public void testAuthResponseDetails() throws Exception {
		assertEquals("admin", jwtResponse.getId());
		assertEquals("adminpass", jwtResponse.getName());
		assertEquals(true, jwtResponse.isValid());
	}

	@Test
	public void testToStringMethod() {
		System.out.println(jwtResponse.toString());
		assertEquals("JwtResponse(id=" + jwtResponse.getId() + ", name=" + jwtResponse.getName() + ", valid=" + jwtResponse.isValid() + ")", jwtResponse.toString());
	}

	@Test
	public void testNoArgsConstructor() {
		JwtResponse authResponse = new JwtResponse();
		assertEquals(authResponse, authResponse);
	}

	@Test
	public void testSetters() {
		jwtResponse.setName("abc");
		jwtResponse.setId("admin");
		jwtResponse.setValid(true);
		assertEquals("abc", jwtResponse.getName());
	}

	@Test
	public void testEqualsMethod() {
		boolean equals = jwtResponse.equals(jwtResponse);
		assertTrue(equals);
	}

	@Test
	public void testHashCodeMethod() {
		int hashCode = jwtResponse.hashCode();
		assertEquals(hashCode, jwtResponse.hashCode());
	}

}