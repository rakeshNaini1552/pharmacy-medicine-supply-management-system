package com.portal.vo;

import javax.persistence.Table;

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
@Table(name = "medicine_supply")
public class MedicineSupplyVO {
	private int id;
	private String pharmacyName;
	private String medicineName;
	private int supplyCount;
}