package com.portal.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.springframework.stereotype.Service;

@Service
public class DateUtil {

	public static LocalDate convertToDate(String date) {

		LocalDate localDate = null;

		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
			localDate = LocalDate.parse(date, formatter);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return localDate;
	}
}