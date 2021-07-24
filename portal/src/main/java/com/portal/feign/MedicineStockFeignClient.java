package com.portal.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;


@FeignClient(url = "http://localhost:8081", name = "medicine-stock-service")
public interface MedicineStockFeignClient {

	@PostMapping("/byTreatingAilment/{treatingAilment}")
	public String[] getMedicineByTreatingAilment(@RequestHeader("Authorization") String token,
			@PathVariable("treatingAilment") String treatingAilment);

	@GetMapping("/medicine-stock-information")
	public ResponseEntity<?> getMedicineStockInformation(@RequestHeader(name = "Authorization") String token);
}
