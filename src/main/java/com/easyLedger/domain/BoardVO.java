package com.easyLedger.domain;

import java.io.Serializable;

import lombok.Data;

@Data
public class BoardVO implements Serializable {

	private Integer bno;
	private String member_email;
	private String name;
	private String grade;
	private String paid;
	private String depositDate;
	private String paymentOption;
	private String memo;
	
}
