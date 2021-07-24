package com.medicalrepresentativeschedule.service;

import java.time.LocalDate;
import java.util.List;

import com.medicalrepresentativeschedule.exception.TokenValidationFailedException;
import com.medicalrepresentativeschedule.model.RepSchedule;

public interface MedRepScheduleService {
	public List<RepSchedule> getRepSchedule(String token, LocalDate scheduleStartDate)
			throws TokenValidationFailedException;

}
