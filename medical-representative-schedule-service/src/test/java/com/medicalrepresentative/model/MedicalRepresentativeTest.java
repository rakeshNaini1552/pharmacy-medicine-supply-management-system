package com.medicalrepresentative.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import com.medicalrepresentativeschedule.model.MedicalRepresentative;

@RunWith(SpringRunner.class)
public class MedicalRepresentativeTest {

	@Mock
	private MedicalRepresentative medicalRepresentative;

	@Before
	public void setup() {
		medicalRepresentative = new MedicalRepresentative();
		medicalRepresentative.setId(1);
		medicalRepresentative.setName("R1");
		medicalRepresentative.setPhoneNumber("8877779292");
	}

	@Test
	public void testMedicineStockDetails() throws Exception {
		assertEquals(1, medicalRepresentative.getId());
		assertEquals("R1", medicalRepresentative.getName());
		assertEquals("8877779292", medicalRepresentative.getPhoneNumber());

	}

	@Test
	public void testAllArgsConstructor() {

		MedicalRepresentative medicalRepresentatives = new MedicalRepresentative(1, "R1", "8877779292");
		assertEquals("R1", medicalRepresentatives.getName());
	}

	@Test
	public void testToStringMethod() {
		assertEquals(medicalRepresentative.toString(), medicalRepresentative.toString());
	}

	@Test
	public void testSetters() {
		medicalRepresentative.setId(1);
		assertEquals(1, medicalRepresentative.getId());
	}

	@Test
	public void testEqualsMethod() {
		boolean equals = medicalRepresentative.equals(medicalRepresentative);
		assertTrue(equals);
	}

}