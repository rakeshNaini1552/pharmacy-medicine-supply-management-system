package com.pod2.medicinestock.service;

import java.util.List;

import com.pod2.medicinestock.model.MedicineStock;

public interface MedicineStockService {

	public List<MedicineStock> getMedicineStockInformation();
	public List<MedicineStock> getMedicineByTargetAilment(String treatingAilment);
	public MedicineStock getNumberOfTabletsInStockByName(String medicine);
	public Boolean updateNumberOfTabletsInStockByName(String medicine, int count);
}
