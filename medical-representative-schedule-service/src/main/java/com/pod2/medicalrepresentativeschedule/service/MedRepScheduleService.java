package com.pod2.medicalrepresentativeschedule.service;

import java.time.LocalDate;
import java.util.List;

import com.pod2.medicalrepresentativeschedule.exception.TokenValidationFailedException;
import com.pod2.medicalrepresentativeschedule.model.RepSchedule;

public interface MedRepScheduleService {
	public List<RepSchedule> getRepSchedule(String token, LocalDate scheduleStartDate)
			throws TokenValidationFailedException;

}
