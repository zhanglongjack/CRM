package com.base.crm.report.entity;

import java.math.BigDecimal;

import com.base.common.util.PageTools;

public class ConsumeAcctGroupReport {
	private Long id;
	private String groupName;
	private BigDecimal consumeAmount = new BigDecimal(0);
	private BigDecimal realAmount = new BigDecimal(0);
	private Double rebate = 0D;
	private String consumeDate;
	private String consumeMonth;
	private int fansNum;
	private Double price;

	// query
	private String status;
	private String startDate ;
	private String endDate ;
	
	
	// @JsonIgnore
	private PageTools pageTools;

	public PageTools getPageTools() {
		return pageTools;
	}

	public void setPageTools(PageTools pageTools) {
		this.pageTools = pageTools;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public BigDecimal getConsumeAmount() {
		return consumeAmount;
	}

	public void setConsumeAmount(BigDecimal consumeAmount) {
		this.consumeAmount = consumeAmount;
	}

	public BigDecimal getRealAmount() {
		return realAmount;
	}

	public void setRealAmount(BigDecimal realAmount) {
		this.realAmount = realAmount;
	}

	public Double getRebate() {
		return rebate;
	}

	public void setRebate(Double rebate) {
		this.rebate = rebate;
	}

	public String getConsumeDate() {
		return consumeDate;
	}

	public void setConsumeDate(String consumeDate) {
		this.consumeDate = consumeDate;
	}

	public String getConsumeMonth() {
		return consumeMonth;
	}

	public void setConsumeMonth(String consumeMonth) {
		this.consumeMonth = consumeMonth;
	}

	public int getFansNum() {
		return fansNum;
	}

	public void setFansNum(int fansNum) {
		this.fansNum = fansNum;
	}

	public Double getPrice() {
		price = fansNum==0?realAmount.doubleValue():(realAmount.doubleValue()/fansNum);
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return String.format(
				"ConsumeAcctGroupReport [id=%s, groupName=%s, consumeAmount=%s, realAmount=%s, rebate=%s, consumeDate=%s, fansNum=%s, price=%s, status=%s]",
				id, groupName, consumeAmount, realAmount, rebate, consumeDate, fansNum, price, status);
	}

}
