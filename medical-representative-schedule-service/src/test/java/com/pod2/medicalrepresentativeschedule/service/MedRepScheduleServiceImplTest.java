package com.pod2.medicalrepresentativeschedule.service;



import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.pod2.medicalrepresentativeschedule.model.Doctor;
import com.pod2.medicalrepresentativeschedule.model.RepSchedule;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
class MedRepScheduleServiceImplTest {
	@Autowired
	private MedRepScheduleServiceImpl medicalRepresentativeScheduleService;

	@Mock
	private RepSchedule repSchedule;

	List<Date> scheduleDates;

	List<Doctor> doctorDetailsList;

	@Before
	public void setup() {
		String[] medicines = { "Crocin", "Percocet" };
		repSchedule = new RepSchedule();
		repSchedule.setId(1);
		repSchedule.setRepName("R1");
		repSchedule.setDoctorName("D1");
		repSchedule.setTreatingAilment("Orthopaedics");
		repSchedule.setMedicines(medicines);
		repSchedule.setMeetingSlot("1 AM to 2 PM");
		repSchedule.setMeetingDate(LocalDate.of(2020, 02, 03));
		repSchedule.setDoctorContactNumber("784521487");
	}

	@Test
	public void testGetRepSchedule() throws FeignException {
		log.info("Start");

		FeignException thrown = assertThrows(FeignException.class,
				() -> medicalRepresentativeScheduleService.getRepSchedule("token", LocalDate.of(2020, 2, 2)));

		assertFalse(thrown.getMessage().contains("Invalid Token"));

		log.info("End");
	}

}
