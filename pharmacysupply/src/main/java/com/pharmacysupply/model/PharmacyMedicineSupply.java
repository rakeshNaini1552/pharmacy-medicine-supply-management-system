package com.pharmacysupply.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "medicine_supply")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PharmacyMedicineSupply {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String pharmacyName;

	private String medicineName;

	private int supplyCount;

}
