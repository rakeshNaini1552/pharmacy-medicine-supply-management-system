package com.portal.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

@RestControllerAdvice
public class GlobalErrorHandler {

	@ExceptionHandler(Exception.class)
	public ModelAndView handleAllErrors(Exception ex) {
		ModelAndView modelAndView = new ModelAndView("tokenExpired");
		return modelAndView;
	}

	@ExceptionHandler(MedicineNotFoundException.class)
	public ModelAndView handleAllMedicineStockErrors(MedicineNotFoundException ex) {
		ModelAndView modelAndView = new ModelAndView("medicineNotFound");
		return modelAndView;
	}

}
