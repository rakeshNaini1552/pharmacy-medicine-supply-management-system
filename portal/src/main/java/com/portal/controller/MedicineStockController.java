package com.portal.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.portal.service.StockFeignService;
import com.portal.vo.MedicineStockVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/user")
@Controller
public class MedicineStockController {

	@Autowired
	private StockFeignService feignService;

	@RequestMapping("/MedicineStockInformation")
	public ModelAndView getMedicineStockDetails(HttpSession session) throws Exception {
		log.info("Begin");
		String token = (String) session.getAttribute("token");
		log.info("Calling StockFeignClient");
		ResponseEntity<?> response = feignService.getMedicineStockInformation(token);
		log.debug("response[]:", response);
		@SuppressWarnings("unchecked")
		List<MedicineStockVO> medicineStockList = (List<MedicineStockVO>) response.getBody();
		log.debug("medicineStock[]:", medicineStockList);
		ModelAndView modelAndView = new ModelAndView("medicineStockList");
		modelAndView.addObject("medicineStockList", medicineStockList);
		return modelAndView;
	}

}
