package com.pod2.medicalrepresentativeschedule.service;

import java.util.List;

import com.pod2.medicalrepresentativeschedule.exception.TokenValidationFailedException;
import com.pod2.medicalrepresentativeschedule.model.MedicalRepresentative;

public interface MedRepService {
	
	public List<MedicalRepresentative> getMedicalRepresentatives(String token) throws TokenValidationFailedException;

}
