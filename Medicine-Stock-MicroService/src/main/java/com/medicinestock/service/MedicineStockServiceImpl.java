package com.medicinestock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medicinestock.model.MedicineStock;
import com.medicinestock.repository.MedicineStockRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j

@Service
public class MedicineStockServiceImpl implements MedicineStockService {

	@Autowired
	private MedicineStockRepository repository;

	@Override
	public List<MedicineStock> getMedicineStockInformation() {
		log.info("BEGIN");
		log.info("END");
		return repository.findAll();
	}

	@Override
	public List<MedicineStock> getMedicineByTargetAilment(String treatingAilment) {
		log.info("BEGIN");
		log.info("END");
		return repository.getMedicineByTargetAilment(treatingAilment);
	}

	@Override
	public MedicineStock getNumberOfTabletsInStockByName(String medicine) {
		log.info("BEGIN");
		MedicineStock numberOfTabletsInStockByName = repository.getNumberOfTabletsInStockByName(medicine);
		log.debug("No of tablets by name in stock:",numberOfTabletsInStockByName);
		log.info("END");
		return numberOfTabletsInStockByName;
	}

	@Override
	public Boolean updateNumberOfTabletsInStockByName(String medicine, int count) {
		log.info("BEGIN");
		repository.updateNumberOfTabletsInStockByName(medicine, count);
		log.info(medicine + "******"+count);
		log.info("END");
		return true;
	}

}
