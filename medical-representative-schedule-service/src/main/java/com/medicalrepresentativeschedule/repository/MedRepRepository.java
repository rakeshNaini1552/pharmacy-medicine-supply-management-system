package com.medicalrepresentativeschedule.repository;

import org.springframework.stereotype.Repository;

import com.medicalrepresentativeschedule.model.MedicalRepresentative;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface MedRepRepository extends JpaRepository<MedicalRepresentative, Integer> {

}
