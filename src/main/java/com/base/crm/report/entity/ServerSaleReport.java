package com.base.crm.report.entity;

import java.math.BigDecimal;

public class ServerSaleReport {
	private Long userId;
	private String month;
	private String name;
	private String wechatNumber;
	private BigDecimal custAddSumNum = new BigDecimal(0);
	private BigDecimal orderNumber = new BigDecimal(0);
	private Double orderRate = 0D;
	private BigDecimal reorderNumber = new BigDecimal(0);
	private Double reorderRate = 0D;
	private Double addCustPrice = 0D;
	private Double orderAdPrice = 0D;
	private BigDecimal adConsumeSum = new BigDecimal(0);
	private BigDecimal salePerforman = new BigDecimal(0);
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWechatNumber() {
		return wechatNumber;
	}

	public void setWechatNumber(String wechatNumber) {
		this.wechatNumber = wechatNumber;
	}

	public BigDecimal getCustAddSumNum() {
		return custAddSumNum;
	}

	public void setCustAddSumNum(BigDecimal custAddSumNum) {
		this.custAddSumNum = custAddSumNum;
	}

	public BigDecimal getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(BigDecimal orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Double getOrderRate() {
		return orderRate;
	}

	public void setOrderRate(Double orderRate) {
		this.orderRate = orderRate;
	}

	public BigDecimal getReorderNumber() {
		return reorderNumber;
	}

	public void setReorderNumber(BigDecimal reorderNumber) {
		this.reorderNumber = reorderNumber;
	}

	public Double getReorderRate() {
		return reorderRate;
	}

	public void setReorderRate(Double reorderRate) {
		this.reorderRate = reorderRate;
	}

	public Double getAddCustPrice() {
		return addCustPrice;
	}

	public void setAddCustPrice(Double addCustPrice) {
		this.addCustPrice = addCustPrice;
	}

	public Double getOrderAdPrice() {
		return orderAdPrice;
	}

	public void setOrderAdPrice(Double orderAdPrice) {
		this.orderAdPrice = orderAdPrice;
	}

	public BigDecimal getAdConsumeSum() {
		return adConsumeSum;
	}

	public void setAdConsumeSum(BigDecimal adConsumeSum) {
		this.adConsumeSum = adConsumeSum;
	}

	public BigDecimal getSalePerforman() {
		return salePerforman;
	}

	public void setSalePerforman(BigDecimal salePerforman) {
		this.salePerforman = salePerforman;
	}

	@Override
	public String toString() {
		return String.format(
				"ServerSaleReport [userId=%s, month=%s, name=%s, wechatNumber=%s, custAddSumNum=%s, orderNumber=%s, orderRate=%s, reorderNumber=%s, reorderRate=%s, addCustPrice=%s, orderAdPrice=%s, adConsumeSum=%s, salePerforman=%s]",
				userId, month, name, wechatNumber, custAddSumNum, orderNumber, orderRate, reorderNumber, reorderRate,
				addCustPrice, orderAdPrice, adConsumeSum, salePerforman);
	}


}
