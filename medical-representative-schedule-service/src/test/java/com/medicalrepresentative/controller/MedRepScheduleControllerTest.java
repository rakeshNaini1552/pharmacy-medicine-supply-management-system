package com.medicalrepresentative.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.medicalrepresentativeschedule.controller.MedRepScheduleController;
import com.medicalrepresentativeschedule.exception.InvalidDateException;
import com.medicalrepresentativeschedule.exception.TokenValidationFailedException;
import com.medicalrepresentativeschedule.feignclient.AuthenticationFeignClient;
import com.medicalrepresentativeschedule.model.JwtResponse;
import com.medicalrepresentativeschedule.model.RepSchedule;
import com.medicalrepresentativeschedule.service.MedRepScheduleServiceImpl;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = { MedRepScheduleControllerTest.class })
public class MedRepScheduleControllerTest {

	@Mock
	private MedRepScheduleServiceImpl medicalRepresentativeScheduleService;

	@MockBean
	private AuthenticationFeignClient authenticationFeignClient;

	@InjectMocks
	private MedRepScheduleController medicalRepresenativeScheduleController;

	@Mock
	private RepSchedule repSchedule;

	@Mock
	private List<RepSchedule> medicineStockList;

	@MockBean
	private MedRepScheduleServiceImpl scheduleService;

	public List<RepSchedule> getMockRepSchedule() {
		List<RepSchedule> repSchedules = new ArrayList<>();
		String[] medicines = { "Crocin", "Percocet" };

		RepSchedule mockRepSchedule = new RepSchedule();
		mockRepSchedule.setId(1);
		mockRepSchedule.setDoctorName("D1");
		mockRepSchedule.setMeetingSlot("1 PM to 2 PM");
		mockRepSchedule.setMeetingDate(LocalDate.of(2022, 04, 20));
		mockRepSchedule.setDoctorContactNumber("9449569825");
		mockRepSchedule.setMedicines(medicines);
		repSchedules.add(mockRepSchedule);

		return repSchedules;
	}


	@Test(expected = NullPointerException.class)
	public void testGetRepScheduleFails() throws Exception {
		when(authenticationFeignClient.verifyToken("token")).thenReturn(new JwtResponse("admin", "admin", false));
		ResponseEntity<?> allProducts = medicalRepresenativeScheduleController.getRepSchedule("token", "2021-07-21");
		assertNull(allProducts);
	}

}