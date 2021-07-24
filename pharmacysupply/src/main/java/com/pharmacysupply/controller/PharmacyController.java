package com.pharmacysupply.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.pharmacysupply.exception.MedicineNotFoundException;
import com.pharmacysupply.exception.TokenValidationFailedException;
import com.pharmacysupply.model.MedicineDemand;
import com.pharmacysupply.model.PharmacyMedicineSupply;
import com.pharmacysupply.service.PharmacyServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class PharmacyController {

	@Autowired
	private PharmacyServiceImpl pharmacyService;

	@PostMapping("/pharmacy-supply")
	public ResponseEntity<?> getPharmacySupply(@RequestHeader(name = "Authorization") String token,
			@Valid @RequestBody List<MedicineDemand> medicineDemandList) throws MedicineNotFoundException {
		log.info("BEGIN");
		log.debug("medicine demand list:", medicineDemandList);
		List<PharmacyMedicineSupply> pharmacySupply = null;
		if (pharmacyService.validateToken(token)) {
			pharmacySupply = pharmacyService.getPharmacySupplyCount(token, medicineDemandList);

			if (pharmacySupply == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}

			log.info("END");
			return new ResponseEntity<>(pharmacySupply, HttpStatus.OK);
		}
		log.info("END");
		throw new TokenValidationFailedException("Token is no longer valid");
	}

	@GetMapping("/getMedicineSupply")
	public ResponseEntity<?> getMedicineSupply(@RequestHeader("Authorization") String token) {
		List<PharmacyMedicineSupply> medicineSupply = null;
		if (pharmacyService.validateToken(token)) {
			medicineSupply = pharmacyService.getMedicineSupply();
			return new ResponseEntity<>(medicineSupply, HttpStatus.OK);
		}
		throw new TokenValidationFailedException("Token is no longer valid");
	}

	@GetMapping("/getMedicineDemand")
	public ResponseEntity<?> getMedicineDemand(@RequestHeader(name = "Authorization") String token) {
		List<MedicineDemand> medicineDemand = null;
		if (pharmacyService.validateToken(token)) {
			medicineDemand = pharmacyService.getMedicineDemand();
			return new ResponseEntity<>(medicineDemand, HttpStatus.OK);
		}
		throw new TokenValidationFailedException("Token is no longer valid");
	}

}
