package com.base.crm.report.entity;

import java.math.BigDecimal;

public class SummaryReport {
	private String month;
	private BigDecimal saleTotalAmount = new BigDecimal("0.00");
	private BigDecimal procurementCosts = new BigDecimal("0.00");
	private BigDecimal consumeAD = new BigDecimal("0.00");
	private BigDecimal realSalary = new BigDecimal("0.00");
	private BigDecimal realProfit = new BigDecimal("0.00");

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public BigDecimal getSaleTotalAmount() {
		return saleTotalAmount;
	}

	public void setSaleTotalAmount(BigDecimal saleTotalAmount) {
		this.saleTotalAmount = saleTotalAmount;
	}

	public BigDecimal getProcurementCosts() {
		return procurementCosts;
	}

	public void setProcurementCosts(BigDecimal procurementCosts) {
		this.procurementCosts = procurementCosts;
	}

	public BigDecimal getConsumeAD() {
		return consumeAD;
	}

	public void setConsumeAD(BigDecimal consumeAD) {
		this.consumeAD = consumeAD;
	}

	public BigDecimal getRealSalary() {
		return realSalary;
	}

	public void setRealSalary(BigDecimal realSalary) {
		this.realSalary = realSalary;
	}

	public BigDecimal getRealProfit() {
		return realProfit;
	}

	public void setRealProfit(BigDecimal realProfit) {
		this.realProfit = realProfit;
	}

	@Override
	public String toString() {
		return String.format(
				"SummaryReport [month=%s, saleTotalAmount=%s, procurementCosts=%s, consumeAD=%s, realSalary=%s, realProfit=%s]",
				month, saleTotalAmount, procurementCosts, consumeAD, realSalary, realProfit);
	}

}
