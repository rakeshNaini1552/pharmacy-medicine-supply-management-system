package com.pharmacysupply.service;

import java.util.List;

import com.pharmacysupply.exception.MedicineNotFoundException;
import com.pharmacysupply.model.MedicineDemand;
import com.pharmacysupply.model.PharmacyMedicineSupply;

public interface PharmacyService {
	public Boolean validateToken(String token) ;
	public List<PharmacyMedicineSupply> getMedicineSupply();
	public List<MedicineDemand> getMedicineDemand();
	public List<PharmacyMedicineSupply> getPharmacySupplyCount(String token, List<MedicineDemand> medicineDemandList) throws MedicineNotFoundException ;
}
