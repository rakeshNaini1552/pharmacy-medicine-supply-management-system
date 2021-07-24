package com.pod2.medicalrepresentativeschedule.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.pod2.medicalrepresentativeschedule.exception.InvalidDateException;
import com.pod2.medicalrepresentativeschedule.exception.TokenValidationFailedException;
import com.pod2.medicalrepresentativeschedule.feignclient.AuthenticationFeignClient;
import com.pod2.medicalrepresentativeschedule.feignclient.MedicineStockFeignClient;
import com.pod2.medicalrepresentativeschedule.model.Doctor;
import com.pod2.medicalrepresentativeschedule.model.JwtResponse;
import com.pod2.medicalrepresentativeschedule.model.MedicalRepresentative;
import com.pod2.medicalrepresentativeschedule.model.RepSchedule;
import com.pod2.medicalrepresentativeschedule.service.MedRepScheduleService;
import com.pod2.medicalrepresentativeschedule.service.MedRepService;
import com.pod2.medicalrepresentativeschedule.util.CsvParseUtil;
import com.pod2.medicalrepresentativeschedule.util.DateUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class MedRepScheduleController {

	@Autowired
	private MedRepScheduleService scheduleService;

	@Autowired
	private MedRepService medicalRepresentativeService;

	@Autowired
	private AuthenticationFeignClient authFeignClient;

	@Autowired
	private MedicineStockFeignClient medicineStockClient;

	@GetMapping("/RepSchedule/{scheduleStartDate}")
	public ResponseEntity<List<RepSchedule>> getRepSchedule(@RequestHeader(name = "Authorization") final String token,
			@PathVariable("scheduleStartDate") final String scheduleStartDate)
			throws InvalidDateException, TokenValidationFailedException {
		log.info("Start");

		log.debug("scheduleStartDate : {}", scheduleStartDate);
		List<RepSchedule> repSchedule = null;

		LocalDate localDate = DateUtil.getDate(scheduleStartDate);
		log.debug("localDate : {}", localDate);
		if (!isValidSession(token)) {
			log.info("End");
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}

		if (localDate == null) {
			log.info("End");
			throw new InvalidDateException("Invalid date");
		}

		repSchedule = scheduleService.getRepSchedule(token, localDate);
		log.debug("repSchedule : {}", repSchedule);
		if (repSchedule.isEmpty()) {
			log.info("End");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		log.info("End");
		return ResponseEntity.of(Optional.of(repSchedule));

	}

	public Boolean isValidSession(String token) throws TokenValidationFailedException {
		log.info("Start");
		final JwtResponse response = authFeignClient.verifyToken(token);

		log.debug("response : {}", response);
		if (!response.isValid()) {
			log.info("End");
			throw new TokenValidationFailedException("Invalid Token");
		}
		log.info("End");
		return true;
	}

	@GetMapping
	public ResponseEntity<String[]> getMedicinesByTreatingAilment(@RequestHeader(name = "Authorization") String token) {
		log.info("Start");
		final ResponseEntity<String[]> responseEntity = ResponseEntity
				.of(Optional.of(medicineStockClient.getMedicinesByTreatingAilment(token, "medicine")));
		log.info("end");
		return responseEntity;
	}

	@GetMapping("/medicalRepresentatives")
	public List<MedicalRepresentative> getMedicalRepresentatives(
			@RequestHeader(name = "Authorization") final String token) throws TokenValidationFailedException {
		log.info("Start");
		List<MedicalRepresentative> medicalRepresentatives = medicalRepresentativeService
				.getMedicalRepresentatives(token);
		log.info("Start");
		return medicalRepresentatives;
	}

	@GetMapping("/doctors")
	public List<Doctor> getDoctors() {
		log.info("Start");
		List<Doctor> doctors = CsvParseUtil.parseDoctors();
		log.info("End");
		return doctors;
	}
}
