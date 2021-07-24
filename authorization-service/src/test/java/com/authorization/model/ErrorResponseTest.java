package com.authorization.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import com.authorization.exception.ErrorResponse;


@RunWith(SpringRunner.class)
@SpringBootTest(classes= {ErrorResponse.class})
public class ErrorResponseTest {
	@Mock
	private ErrorResponse errorResponse;

	@Before
	public void setup() {
		
		errorResponse = new ErrorResponse();
		errorResponse.setStatus(HttpStatus.OK);
		errorResponse.setReason("Bad request");
		errorResponse.setMessage("Please provide valid value");
		
	}


	@Test
	public void testMedicineStockDetails() throws Exception {
	
		assertEquals(HttpStatus.OK, errorResponse.getStatus());
		assertEquals("Bad request", errorResponse.getReason());
		assertEquals("Please provide valid value", errorResponse.getMessage());
		

	}


}
