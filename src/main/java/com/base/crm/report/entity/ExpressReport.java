package com.base.crm.report.entity;

import java.math.BigDecimal;

import com.base.common.util.PageTools;

public class ExpressReport {
	private String month;
	private String dates;
	private String depositoryId;

	private BigDecimal expressTotalFee = new BigDecimal("0.00");// 快递费用
	private BigDecimal expressCommissionTotalFee = new BigDecimal("0.00"); // 快递佣金

	// @JsonIgnore
	private PageTools pageTools;

	public PageTools getPageTools() {
		return pageTools;
	}

	public void setPageTools(PageTools pageTools) {
		this.pageTools = pageTools;
	}
	
	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDates() {
		return dates;
	}

	public void setDates(String dates) {
		this.dates = dates;
	}

	public String getDepositoryId() {
		return depositoryId;
	}

	public void setDepositoryId(String depositoryId) {
		this.depositoryId = depositoryId;
	}

	public BigDecimal getExpressTotalFee() {
		return expressTotalFee;
	}

	public void setExpressTotalFee(BigDecimal expressTotalFee) {
		this.expressTotalFee = expressTotalFee;
	}

	public BigDecimal getExpressCommissionTotalFee() {
		return expressCommissionTotalFee;
	}

	public void setExpressCommissionTotalFee(BigDecimal expressCommissionTotalFee) {
		this.expressCommissionTotalFee = expressCommissionTotalFee;
	}

}
