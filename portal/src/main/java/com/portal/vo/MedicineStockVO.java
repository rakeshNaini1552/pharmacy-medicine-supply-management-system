package com.portal.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MedicineStockVO {

	private int id;

	private String name;

	private String chemicalComposition;

	private String targetAilment;

	private String pharmacyName;

	private Date dateOfExpiry;

	private int numberOfTabletsInStock;

}
