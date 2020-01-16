package com.base.crm.customer.entity;

public class CustInfoReport extends CustInfo {
	private String startMonth;
	private String endMonth;
	private String addMonth;
	private Long fansNum;

	public String getStartMonth() {
		return startMonth;
	}

	public void setStartMonth(String startMonth) {
		this.startMonth = startMonth;
	}

	public String getEndMonth() {
		return endMonth;
	}

	public void setEndMonth(String endMonth) {
		this.endMonth = endMonth;
	}

	public String getAddMonth() {
		return addMonth;
	}

	public void setAddMonth(String addMonth) {
		this.addMonth = addMonth;
	}

	public Long getFansNum() {
		return fansNum;
	}

	public void setFansNum(Long fansNum) {
		this.fansNum = fansNum;
	}

}
