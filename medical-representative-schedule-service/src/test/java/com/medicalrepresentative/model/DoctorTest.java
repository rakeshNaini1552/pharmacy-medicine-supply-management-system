package com.medicalrepresentative.model;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.medicalrepresentativeschedule.model.Doctor;

@SpringBootTest
public class DoctorTest {

	@Mock
	public Doctor doctor;

	@Before
	public void setup() {
		doctor = new Doctor();
		doctor.setName("D1");
		doctor.setContactNumber("8877779292");
		doctor.setTreatingAilment("General");

	}

	@Test
	public void testMedicineStockDetails() throws Exception {
		assertEquals("D1", doctor.getName());
		assertEquals("8877779292", doctor.getContactNumber());
		assertEquals("General", doctor.getTreatingAilment());
	}

	@Test
	public void testAllArgsConstructor() {

		Doctor doctor = new Doctor(1, "D1", "8877779292", "General");
		assertEquals("D1", doctor.getName());
	}

	@Test
	public void testToStringMethod() {
		assertEquals(doctor.toString(), doctor.toString());
	}

	@Test
	public void testSetters() {
		doctor.setName("D1");
		;
		assertEquals("D1", doctor.getName());
	}

	@Test
	public void testEqualsMethod() {
		boolean equals = doctor.equals(doctor);
		assertTrue(equals);
	}

	@Test
	public void testHashCodeMethod() {
		int hashCode = doctor.hashCode();
		assertEquals(hashCode, doctor.hashCode());
	}

}