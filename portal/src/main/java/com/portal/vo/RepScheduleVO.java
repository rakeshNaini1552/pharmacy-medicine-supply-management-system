package com.portal.vo;

import java.util.Date;

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
@Table(name = "rep_schedule")
public class RepScheduleVO {

	private int id;

	private String repName;


	private String doctorName;

	private String treatingAilment;

	private String[] medicines;

	private String meetingSlot;

	private Date dateOfMeeting;

	private long doctorContactNo;

	
}
