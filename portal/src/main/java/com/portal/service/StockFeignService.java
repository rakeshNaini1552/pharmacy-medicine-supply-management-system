package com.portal.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.portal.feign.MedicineStockFeignClient;

@Service
public class StockFeignService {

	@Autowired
	private MedicineStockFeignClient stockFeignClient;

	public ResponseEntity<?> getMedicineStockInformation(String token) {

		ResponseEntity<?> response = stockFeignClient.getMedicineStockInformation(getTokenWithHeader(token));
		return response;
	}

	public String getTokenWithHeader(String token) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.add("Authorization", "Bearer " + token);

		return headers.getFirst("Authorization");
	}

}
