package com.pod2.pharmacysupply.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pod2.pharmacysupply.model.MedicineDemand;

@Repository
public interface MedicineDemandRepository extends JpaRepository<MedicineDemand, Integer>{

}
