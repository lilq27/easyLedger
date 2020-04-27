package com.easyLedger.domain;

import lombok.Data;

@Data
public class memberVO {
	
	private String email;
	private String pwd;
	private String name;
	
	
	public memberVO(String email, String pwd, String name) {
		super();
		this.email = email;
		this.pwd = pwd;
		this.name = name;
	}

}
