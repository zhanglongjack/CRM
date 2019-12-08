package com.base.crm.orders.entity;

import com.base.common.util.PageTools;

public class OrderSalesRateReport {
	private Long groupId;
	private String groupName;
	private String addMonth;
	private Integer fansNum;
	private Integer orderedNum;
	private Integer reorderedNum;
	
	private Double orderedRate;
	private Double reorderedRate;

	
	private PageTools pageTools;
	
	public PageTools getPageTools() {
		return pageTools;
	}

	public void setPageTools(PageTools pageTools) {
		this.pageTools = pageTools;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getAddMonth() {
		return addMonth;
	}

	public void setAddMonth(String addMonth) {
		this.addMonth = addMonth;
	}

	public Integer getFansNum() {
		return fansNum;
	}

	public void setFansNum(Integer fansNum) {
		this.fansNum = fansNum;
	}

	public Integer getOrderedNum() {
		return orderedNum;
	}

	public void setOrderedNum(Integer orderedNum) {
		this.orderedNum = orderedNum;
	}

	public Integer getReorderedNum() {
		return reorderedNum;
	}

	public void setReorderedNum(Integer reorderedNum) {
		this.reorderedNum = reorderedNum;
	}

	public Double getOrderedRate() {
		orderedRate = fansNum==0?0D:((double)orderedNum/fansNum);
		return orderedRate;
	}

	public void setOrderedRate(Double orderedRate) {
		this.orderedRate = orderedRate;
	}

	public Double getReorderedRate() {
		reorderedRate = orderedNum==0?0D:((double)reorderedNum/orderedNum);
		return  reorderedRate;
	}

	public void setReorderedRate(Double reorderedRate) {
		this.reorderedRate = reorderedRate;
	}

}