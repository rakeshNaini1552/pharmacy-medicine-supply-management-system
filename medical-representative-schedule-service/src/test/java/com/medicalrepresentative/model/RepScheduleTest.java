package com.medicalrepresentative.model;
import java.time.LocalDate;

import org.junit.Before;
import org.springframework.boot.test.context.SpringBootTest;

import com.medicalrepresentativeschedule.model.RepSchedule;

@SpringBootTest
public class RepScheduleTest {

	private RepSchedule repSchedule;
	
	@Before
	public void setup() {
		
		repSchedule = new RepSchedule();
		repSchedule.setId(1);
		repSchedule.setDoctorName("avc");
		repSchedule.setDoctorContactNumber("2134555678");
		repSchedule.setMeetingDate(LocalDate.now());
		repSchedule.setMeetingSlot("1 PM to 2 PM");
	}
}