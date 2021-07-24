package com.medicalrepresentativeschedule.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medicalrepresentativeschedule.exception.TokenValidationFailedException;
import com.medicalrepresentativeschedule.feignclient.AuthenticationFeignClient;
import com.medicalrepresentativeschedule.feignclient.MedicineStockFeignClient;
import com.medicalrepresentativeschedule.model.Doctor;
import com.medicalrepresentativeschedule.model.JwtResponse;
import com.medicalrepresentativeschedule.model.MedicalRepresentative;
import com.medicalrepresentativeschedule.model.RepSchedule;
import com.medicalrepresentativeschedule.repository.MedRepRepository;
import com.medicalrepresentativeschedule.repository.RepScheduleRepository;
import com.medicalrepresentativeschedule.util.CsvParseUtil;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class MedRepScheduleServiceImpl implements MedRepScheduleService {

	@Autowired
	private MedicineStockFeignClient medicineStockClient;

	@Autowired
	private MedRepRepository medicalRepresentativeRepository;
	@Autowired
	private RepScheduleRepository repScheduleRepository;

	@Autowired
	AuthenticationFeignClient authFeignClient;

	@Override
	public List<RepSchedule> getRepSchedule(String token, LocalDate scheduleStartDate)
			throws TokenValidationFailedException {
		log.info("BEGIN");
		if (!isValidSession(token)) {
			log.info("END");
			return null;
		}

		List<RepSchedule> repSchedules = new ArrayList<>();

		List<Doctor> doctors = CsvParseUtil.parseDoctors();
		log.debug("DOCTORS :",doctors);
		List<MedicalRepresentative> medicalRepresentatives = medicalRepresentativeRepository.findAll();
		log.debug("MR :",medicalRepresentatives);
		LocalDate localDate = scheduleStartDate;

		LocalTime now = LocalTime.now();
		LocalTime one = LocalTime.of(13, 0);

		LocalDate today = LocalDate.now();
		if (scheduleStartDate.isBefore(today)) {
			log.info("END");
			return repSchedules;
		}

		if (scheduleStartDate.equals(today)) {
			
			if (now.isAfter(one)) {
				localDate = localDate.plusDays(1);
			}

		}

		for (int i = 0; i < doctors.size(); i++) {

			if (localDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
				localDate = localDate.plusDays(1);
			}

			Doctor doctor = doctors.get(i);
			MedicalRepresentative medicalRepresentative = medicalRepresentatives.get(i % medicalRepresentatives.size());

			RepSchedule repSchedule = new RepSchedule();
			repSchedule.setId(i + 1);
			repSchedule.setRepName(medicalRepresentative.getName());
			repSchedule.setDoctorName(doctor.getName());
			repSchedule.setDoctorContactNumber(doctor.getContactNumber());
			repSchedule.setMeetingDate(localDate);
			repSchedule.setMeetingSlot("1 PM to 2 PM");
			repSchedule.setTreatingAilment(doctor.getTreatingAilment());
			
			String[] medicinesByTreatingAilment = medicineStockClient.getMedicinesByTreatingAilment(token,
					doctor.getTreatingAilment());
			
			repSchedule.setMedicines(medicinesByTreatingAilment);
			log.debug("rep schedule :",repSchedule);
			repScheduleRepository.save(repSchedule);
			repSchedules.add(repSchedule);
			
			localDate = localDate.plusDays(1);
		}
		log.debug("rep schedule :",repSchedules);
		
		log.info("END");
		return repSchedules;
	}

	public Boolean isValidSession(String token) throws TokenValidationFailedException {
		log.info("BEGIN");
		JwtResponse response = authFeignClient.verifyToken(token);
		if (!response.isValid()) {
			log.info("END");
			throw new TokenValidationFailedException("Invalid Token");
		}
		log.info("END");
		return true;
	}

}
