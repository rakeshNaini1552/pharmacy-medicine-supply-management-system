package com.pod2.medicalrepresentativeschedule.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pod2.medicalrepresentativeschedule.model.RepSchedule;

@Repository
public interface RepScheduleRepository extends JpaRepository<RepSchedule, Integer> {

}
