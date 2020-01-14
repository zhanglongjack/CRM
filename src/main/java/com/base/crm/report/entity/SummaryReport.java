package com.base.crm.report.entity;

import java.math.BigDecimal;

public class SummaryReport {
	private String month;
	private String dates;
	private BigDecimal saleTotalAmount = new BigDecimal("0.00");
	private BigDecimal realncomeTotalAmount = new BigDecimal("0.00");
	private BigDecimal procurementCosts = new BigDecimal("0.00");
	private BigDecimal consumeAD = new BigDecimal("0.00");
	private BigDecimal realConsumeAD = new BigDecimal("0.00");
	private BigDecimal realSalary = new BigDecimal("0.00");

	private BigDecimal realProfit = new BigDecimal("0.00");
	private BigDecimal profit = new BigDecimal("0.00");

	private BigDecimal expressTotalFee = new BigDecimal("0.00");// 快递费用

	private BigDecimal handselAmount = new BigDecimal("0.00"); // 定金
	private BigDecimal expectAmount = new BigDecimal("0.00"); // 预计收款
	private BigDecimal realAmount = new BigDecimal("0.00"); // 实际收款
	private BigDecimal refuseAmount = new BigDecimal("0.00"); // 拒签定金
	private BigDecimal refusePaymentAmount = new BigDecimal("0.00"); // 未收金额
	private BigDecimal expressCommissionTotalFee = new BigDecimal("0.00"); // 快递佣金
	private BigDecimal serviceKPI = new BigDecimal("0.00"); // 销售业绩
	private Integer sumOrders = 0;
	private Integer fansNum = 0;

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

	public BigDecimal getSaleTotalAmount() {
		return saleTotalAmount;
	}

	public void setSaleTotalAmount(BigDecimal saleTotalAmount) {
		this.saleTotalAmount = saleTotalAmount;
	}

	public BigDecimal getRealncomeTotalAmount() {
		return realncomeTotalAmount;
	}

	public void setRealncomeTotalAmount(BigDecimal realncomeTotalAmount) {
		this.realncomeTotalAmount = realncomeTotalAmount;
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

	public BigDecimal getRealConsumeAD() {
		return realConsumeAD;
	}

	public void setRealConsumeAD(BigDecimal realConsumeAD) {
		this.realConsumeAD = realConsumeAD;
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

	public BigDecimal getExpressTotalFee() {
		return expressTotalFee;
	}

	public void setExpressTotalFee(BigDecimal expressTotalFee) {
		this.expressTotalFee = expressTotalFee;
	}

	public BigDecimal getProfit() {
		return profit;
	}

	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}

	public BigDecimal getHandselAmount() {
		return handselAmount;
	}

	public void setHandselAmount(BigDecimal handselAmount) {
		this.handselAmount = handselAmount;
	}

	public BigDecimal getExpectAmount() {
		return expectAmount;
	}

	public void setExpectAmount(BigDecimal expectAmount) {
		this.expectAmount = expectAmount;
	}

	public BigDecimal getRealAmount() {
		return realAmount;
	}

	public void setRealAmount(BigDecimal realAmount) {
		this.realAmount = realAmount;
	}

	public BigDecimal getRefuseAmount() {
		return refuseAmount;
	}

	public void setRefuseAmount(BigDecimal refuseAmount) {
		this.refuseAmount = refuseAmount;
	}

	public BigDecimal getRefusePaymentAmount() {
		return refusePaymentAmount;
	}

	public void setRefusePaymentAmount(BigDecimal refusePaymentAmount) {
		this.refusePaymentAmount = refusePaymentAmount;
	}

	public BigDecimal getExpressCommissionTotalFee() {
		return expressCommissionTotalFee;
	}

	public void setExpressCommissionTotalFee(BigDecimal expressCommissionTotalFee) {
		this.expressCommissionTotalFee = expressCommissionTotalFee;
	}

	public BigDecimal getServiceKPI() {
		return serviceKPI;
	}

	public void setServiceKPI(BigDecimal serviceKPI) {
		this.serviceKPI = serviceKPI;
	}

	public Integer getSumOrders() {
		return sumOrders;
	}

	public void setSumOrders(Integer sumOrders) {
		this.sumOrders = sumOrders;
	}

	public Integer getFansNum() {
		return fansNum;
	}

	public void setFansNum(Integer fansNum) {
		this.fansNum = fansNum;
	}

	public void sum(SummaryReport report) {
		this.saleTotalAmount          = this.saleTotalAmount.add(report.saleTotalAmount);
		this.realncomeTotalAmount     = this.realncomeTotalAmount.add(report.realncomeTotalAmount);
		this.procurementCosts         = this.procurementCosts.add(report.procurementCosts);
		this.consumeAD                = this.consumeAD.add(report.consumeAD);
		this.realConsumeAD            = this.realConsumeAD.add(report.realConsumeAD);
		this.realSalary               = this.realSalary.add(report.realSalary);
		this.realProfit               = this.realProfit.add(report.realProfit);
		this.profit                   = this.profit.add(report.profit);
		this.expressTotalFee          = this.expressTotalFee.add(report.expressTotalFee);
		this.handselAmount            = this.handselAmount.add(report.handselAmount);
		this.expectAmount             = this.expectAmount.add(report.expectAmount);
		this.realAmount               = this.realAmount.add(report.realAmount);
		this.refuseAmount             = this.refuseAmount.add(report.refuseAmount);
		this.refusePaymentAmount      = this.refusePaymentAmount.add(report.refusePaymentAmount);
		this.expressCommissionTotalFee= this.expressCommissionTotalFee.add(report.expressCommissionTotalFee);
		this.serviceKPI				  = this.serviceKPI.add(report.serviceKPI);
		this.sumOrders 				  = this.sumOrders + report.sumOrders;
	}
	
	@Override
	public String toString() {
		return String.format(
				"SummaryReport [month=%s, saleTotalAmount=%s, procurementCosts=%s, consumeAD=%s, realSalary=%s, realProfit=%s]",
				month, saleTotalAmount, procurementCosts, consumeAD, realSalary, realProfit);
	}

}
