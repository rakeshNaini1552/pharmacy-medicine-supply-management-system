package com.portal.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.portal.feign.RepresentativeFeignClient;

@Service
public class MedicalRepFeignService {

	@Autowired
	private RepresentativeFeignClient repFeignClient;

	public ResponseEntity<?> getRepSchedule(String token, String scheduleStartDate) {
		ResponseEntity<?> response = repFeignClient.getRepSchedule(getTokenWithHeader(token), scheduleStartDate);
		return response;
	}

	public String getTokenWithHeader(String token) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.add("Authorization", "Bearer " + token);

		String first = headers.getFirst("Authorization");

		return first;
	}

}
