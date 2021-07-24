package com.portal.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.portal.exception.MedicineNotFoundException;
import com.portal.service.SupplyFeignService;
import com.portal.vo.MedicineDemandVO;
import com.portal.vo.MedicineSupplyVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/user")
@Controller
public class PharmacySupplyController {

	@Autowired
	private SupplyFeignService feignService;

	List<MedicineSupplyVO> medicineSupplyList = new ArrayList<>();

	@SuppressWarnings("unchecked")
	@RequestMapping("/PharmacySupply")
	public ModelAndView getMedicineSupply(@ModelAttribute("medicineDemand") MedicineDemandVO medicineDemand,
			HttpSession session) throws Exception {
		log.info("Start");

		ModelAndView modelAndView = new ModelAndView();
		String token = (String) session.getAttribute("token");
		List<MedicineDemandVO> medicineDemandList = new ArrayList<>();
		medicineDemandList.add(medicineDemand);
		log.debug("medicineDemand{} :", medicineDemand);

		ResponseEntity<?> response = feignService.getPharmacySupply(token, medicineDemandList);

		HttpStatus statusCode = response.getStatusCode();

		if (statusCode == HttpStatus.NOT_FOUND) {
			modelAndView.addObject("error", true);
		}

		if (response.getBody() instanceof String) {
			throw new MedicineNotFoundException("Medicine not found");
		}

		log.debug("response []:", response);

		medicineSupplyList = (List<MedicineSupplyVO>) response.getBody();

		log.debug("medicineSupplyList []:", medicineSupplyList);

		modelAndView.setViewName("medicineSupplyList");
		modelAndView.addObject("medicineSupplyList", medicineSupplyList);

		return modelAndView;
	}

	@RequestMapping("/showMedicineSupply")
	public ModelAndView showMedicineSupply(HttpSession session) {
		String token = (String) session.getAttribute("token");
		@SuppressWarnings("unchecked")
		List<MedicineSupplyVO> medicineSupply = (List<MedicineSupplyVO>) feignService.getMedicineSupply(token).getBody();
		ModelAndView mv = new ModelAndView("medicineSupplyList");
		mv.addObject("medicineSupplyList", medicineSupply);
		return mv;
	}

	@RequestMapping("/medicineDemand")
	public String getMedicineDemandPage() {
		
		return "medicineDemand";
	}


}
