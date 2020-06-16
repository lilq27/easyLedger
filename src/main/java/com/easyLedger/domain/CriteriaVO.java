package com.easyLedger.domain;

public class CriteriaVO {
	
	private int pageNum;
	private int amount;
	
	private String findType;
	private String keyWord;
	private String email;

	public CriteriaVO() {
		this(1,10);
		
	}

	public CriteriaVO(int pageNum, int amount) {
		super();
		this.pageNum = pageNum;
		this.amount = amount;
	}
	

	public String[] getTypeArr() {
		return findType == null? new String[] {}: findType.split("");
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getfindType() {
		return findType;
	}

	public void setfindType(String type) {
		this.findType = type;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


}
