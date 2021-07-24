package com.portal.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.portal.feign.PharmacySupplyFeignClient;
import com.portal.vo.MedicineDemandVO;

import feign.FeignException;

@Service
public class SupplyFeignService {

	@Autowired
	private PharmacySupplyFeignClient supplyFeignClient;

	public ResponseEntity<?> getPharmacySupply(String token, List<MedicineDemandVO> medicineDemand) {

		ModelAndView modelAndView = new ModelAndView();
		ResponseEntity<?> response = null;
		try {
			response = supplyFeignClient.getPharmacySupply(getTokenWithHeader(token), medicineDemand);

		} catch (FeignException e) {
			modelAndView.setViewName("medicineNotFound");
			return new ResponseEntity<>("medicineNotFound", HttpStatus.NOT_FOUND);
		}
		return response;
	}

	public ResponseEntity<?> getMedicineSupply(String token) {

		ResponseEntity<?> response = null;
		response = supplyFeignClient.getMedicineSupply(getTokenWithHeader(token));

		return response;
	}

	public ResponseEntity<?> getMedicineDemand(String token) {

		ResponseEntity<?> response = null;
		response = supplyFeignClient.getMedicineDemand(getTokenWithHeader(token));

		return response;
	}

	public String getTokenWithHeader(String token) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.add("Authorization", "Bearer " + token);

		return headers.getFirst("Authorization");
	}
}
