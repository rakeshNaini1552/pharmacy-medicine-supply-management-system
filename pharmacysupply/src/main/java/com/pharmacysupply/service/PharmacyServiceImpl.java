package com.pharmacysupply.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pharmacysupply.exception.MedicineNotFoundException;
import com.pharmacysupply.exception.TokenValidationFailedException;
import com.pharmacysupply.feignclient.AuthenticationFeignClient;
import com.pharmacysupply.feignclient.MedicineStockFeignClient;
import com.pharmacysupply.model.JwtResponse;
import com.pharmacysupply.model.MedicineDemand;
import com.pharmacysupply.model.MedicineStock;
import com.pharmacysupply.model.PharmacyMedicineSupply;
import com.pharmacysupply.repository.MedicineDemandRepository;
import com.pharmacysupply.repository.PharmacyMedicineSupplyRepository;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PharmacyServiceImpl implements PharmacyService {
	@Autowired
	private MedicineDemandRepository medicineDemandRepo;

	@Autowired
	private MedicineStockFeignClient stockFeignClient;

	@Autowired
	private PharmacyMedicineSupplyRepository pharmacyMedicineSupplyRepository;

	@Autowired
	private AuthenticationFeignClient authFeign;

	@Autowired
	private MedicineDemandRepository medicineDemandRepository;

	@Override
	public List<PharmacyMedicineSupply> getPharmacySupplyCount(String token, List<MedicineDemand> medicineDemandList)
			throws MedicineNotFoundException {
		log.info("BEGIN");
		log.info("Medicine demand list", medicineDemandList);
		List<PharmacyMedicineSupply> list = new ArrayList<>();

		for (MedicineDemand medicineDemand : medicineDemandList) {
			PharmacyMedicineSupply pharmacyMedicineSupply = new PharmacyMedicineSupply();
			MedicineStock medicineStock = getNumberOfTablets(token, medicineDemand);
			log.info("[]", medicineStock);
			log.info("Medicine [] , Tablets []", medicineDemand.getMedicineName(),
					medicineStock.getNumberOfTabletsInStock());
			if (medicineStock.getNumberOfTabletsInStock() < medicineDemand.getDemandCount()) {
				return null;
			}

			setSupply(token, pharmacyMedicineSupply, medicineDemand, medicineStock);
			list.add(pharmacyMedicineSupply);
		}
		pharmacyMedicineSupplyRepository.saveAll(list);
		medicineDemandRepository.saveAll(medicineDemandList);
		log.info("END");
		return list;
	}

	public void setSupply(String token, PharmacyMedicineSupply medicineSupply, MedicineDemand medicineDemand,
			MedicineStock medicineStock) throws MedicineNotFoundException {
		log.info("BEGIN");
		int val = 0;
		
		log.info("no of tablets in stock",medicineStock.getNumberOfTabletsInStock());
		log.info("no of tablets in demand",medicineDemand.getDemandCount());
		if (medicineStock.getNumberOfTabletsInStock() < medicineDemand.getDemandCount()) {
			medicineSupply.setSupplyCount(medicineStock.getNumberOfTabletsInStock());
		} else {

			medicineSupply.setSupplyCount(medicineDemand.getDemandCount());
			val = medicineStock.getNumberOfTabletsInStock() - medicineDemand.getDemandCount();

		}
		log.info("VALUE =", val);
		try {
			stockFeignClient.updateNumberOfTabletsInStockByName(token, medicineDemand.getMedicineName(), val);
		} catch (FeignException ex) {
			throw new MedicineNotFoundException("Medicine not found");
		}

		medicineSupply.setMedicineName(medicineDemand.getMedicineName());
		log.info("Medicine demand, medicine supply", medicineDemand, medicineSupply);
		medicineSupply.setPharmacyName(medicineStock.getPharmacyName());
		log.info("medicine supply", medicineSupply);
		log.info("END");
	}

	public MedicineStock getNumberOfTablets(String token, MedicineDemand medicineDemand)
			throws MedicineNotFoundException {
		log.info("BEGIN");
		MedicineStock medicineStock = null;
		log.info("MEdicine :", medicineDemand);
		try {
			medicineStock = stockFeignClient.getNumberOfTabletsInStockByName(token, medicineDemand.getMedicineName());
		} catch (FeignException ex) {
			throw new MedicineNotFoundException("Medicine not found");
		}

		if (medicineStock == null) {
			throw new MedicineNotFoundException("Medicine not found");
		}
		
		log.info("END");
		return medicineStock;
	}

	@Override
	public List<MedicineDemand> getMedicineDemand() {
		
		log.info("BEGIN");
		return medicineDemandRepo.findAll();
	}

	@Override
	public List<PharmacyMedicineSupply> getMedicineSupply() {
		log.info("BEGIN");
		return pharmacyMedicineSupplyRepository.findAll();
	}

	@Override
	public Boolean validateToken(String token) {
		log.info("BEGIN");
		JwtResponse jwtResponse = authFeign.verifyToken(token);
		log.info("END");
		if (jwtResponse.isValid())
			return true;
		throw new TokenValidationFailedException("Token is no longer valid");

	}
}
