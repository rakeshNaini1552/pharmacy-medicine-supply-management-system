package com.authorization.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.authorization.model.MyUser;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= {MyUser.class})
public class MyUserTest {

	@Mock
	private MyUser myUser;
	
	@Before
	public void setUp() {
		myUser=new MyUser("admin","admin","admin");
	}
	@Test
	public void testAllArgumentConstructor() {
		MyUser user=new MyUser("admin","admin","admin");
		assertEquals("admin",user.getUserid());
		assertEquals("admin",user.getPassword());
		assertEquals("admin",user.getUsername());
	}
	@Test
	public void testNoArgumentConstructor() {
		MyUser user=new MyUser();
		assertEquals(user,user);
	}
	
	@Test
	public void testSetter() {
		MyUser user=new MyUser();
		user.setUsername("abc");
		assertEquals("abc",user.getUsername());
	}
	
	@Test
	public void testEqualsMethod() {
		boolean equals = myUser.equals(myUser);
		assertTrue(equals);
	}
	
}
