package com.medicalrepresentativeschedule.service;

import java.util.List;

import com.medicalrepresentativeschedule.exception.TokenValidationFailedException;
import com.medicalrepresentativeschedule.model.MedicalRepresentative;

public interface MedRepService {
	
	public List<MedicalRepresentative> getMedicalRepresentatives(String token) throws TokenValidationFailedException;

}
