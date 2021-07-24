package com.medicalrepresentativeschedule.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateUtil {

	public static LocalDate getDate(String scheduleStartDate) {

		LocalDate localDate = null;
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
			localDate = LocalDate.parse(scheduleStartDate, formatter);
		} catch (Exception e) {
			
		}	
		return localDate;
	}

}
