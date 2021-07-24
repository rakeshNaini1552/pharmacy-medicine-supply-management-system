package com.pharmacysupply.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.pharmacysupply.model.MedicineStock;



@FeignClient(url = "http://localhost:8081", name = "medicine-stock-service")
public interface MedicineStockFeignClient {
	

	@PostMapping("/get-stock-count/{medicine}")
	public MedicineStock getNumberOfTabletsInStockByName(@RequestHeader(name = "Authorization") String token,@PathVariable("medicine") String medicine);
	

	@PostMapping("/update-stock/{medicine}/{count}")
	public Boolean updateNumberOfTabletsInStockByName(@RequestHeader(name = "Authorization") String token,@PathVariable("medicine") String medicine, @PathVariable("count") int count);	
}
