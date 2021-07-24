package com.medicalrepresentativeschedule.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medicalrepresentativeschedule.exception.TokenValidationFailedException;
import com.medicalrepresentativeschedule.feignclient.AuthenticationFeignClient;
import com.medicalrepresentativeschedule.model.JwtResponse;
import com.medicalrepresentativeschedule.model.MedicalRepresentative;
import com.medicalrepresentativeschedule.repository.MedRepRepository;

@Service
public class MedRepServiceImpl implements MedRepService {

	@Autowired
	AuthenticationFeignClient authFeignClient;

	@Autowired
	private MedRepRepository medicalRepresentativesRepository;

	@Override
	public List<MedicalRepresentative> getMedicalRepresentatives(String token) throws TokenValidationFailedException {

		if (!isValidSession(token)) {
	
			return null;
		}

	
		return medicalRepresentativesRepository.findAll();
	}

	public Boolean isValidSession(String token) throws TokenValidationFailedException {

	

		JwtResponse response = authFeignClient.verifyToken(token);

	
		if (!response.isValid()) {
		

			throw new TokenValidationFailedException("Invalid Token");
		}

		
		return true;
	}

}
