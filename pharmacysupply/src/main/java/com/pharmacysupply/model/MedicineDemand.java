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

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "medicine_demand")
public class MedicineDemand {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String medicineName;

	private int demandCount;

}
