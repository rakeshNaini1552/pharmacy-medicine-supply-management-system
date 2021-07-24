package com.portal.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import com.portal.vo.MedicineDemandVO;


@FeignClient(url = "http://localhost:8083", name = "pharmacy-medicine-supply")
public interface PharmacySupplyFeignClient {


	@RequestMapping("/pharmacy-supply")
	public ResponseEntity<?> getPharmacySupply(@RequestHeader(name = "Authorization") String token,
			@RequestBody List<MedicineDemandVO> medicineDemandList);


	@RequestMapping("/getMedicineSupply")
	public ResponseEntity<?> getMedicineSupply(@RequestHeader(name = "Authorization") String token);

	@RequestMapping("/getMedicineDemand")
	public ResponseEntity<?> getMedicineDemand(@RequestHeader(name = "Authorization") String token);

}
