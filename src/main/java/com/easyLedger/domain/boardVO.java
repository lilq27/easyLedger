package com.easyLedger.domain;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class boardVO implements Serializable {

	private Integer name_id;
	private String name;
	private String grade;
	private String paid;
	private String depositDate;
	private String paymentOption;
	private String memo;
	
}
