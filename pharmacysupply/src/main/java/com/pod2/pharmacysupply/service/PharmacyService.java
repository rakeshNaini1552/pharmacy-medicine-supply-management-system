package com.pod2.pharmacysupply.service;

import java.util.List;

import com.pod2.pharmacysupply.exception.MedicineNotFoundException;
import com.pod2.pharmacysupply.model.MedicineDemand;
import com.pod2.pharmacysupply.model.PharmacyMedicineSupply;

public interface PharmacyService {
	public Boolean validateToken(String token) ;
	public List<PharmacyMedicineSupply> getMedicineSupply();
	public List<MedicineDemand> getMedicineDemand();
	public List<PharmacyMedicineSupply> getPharmacySupplyCount(String token, List<MedicineDemand> medicineDemandList) throws MedicineNotFoundException ;
}
