package com.pod2.medicalrepresentativeschedule.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pod2.medicalrepresentativeschedule.exception.TokenValidationFailedException;
import com.pod2.medicalrepresentativeschedule.feignclient.AuthenticationFeignClient;
import com.pod2.medicalrepresentativeschedule.model.JwtResponse;
import com.pod2.medicalrepresentativeschedule.model.MedicalRepresentative;
import com.pod2.medicalrepresentativeschedule.repository.MedRepRepository;

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
