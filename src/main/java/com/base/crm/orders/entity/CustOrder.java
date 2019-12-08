package com.base.crm.orders.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Locale;

import org.thymeleaf.util.DateUtils;

import com.base.common.util.DateFormateType;
import com.base.common.util.PageTools;
import com.base.crm.customer.entity.CustInfo;
import com.base.crm.users.entity.UserInfo;

public class CustOrder {
	private Long orderNo;

	private Long userId;

	private String oWechatNo;

	private Integer orderStatus;

	private String contact;

	private String address;

	private Long oPhone;

	private String productList;

	private Double deposits;

	private Double cashOnDeliveryAmt;

	private Double totalAmt;

	private Double afterDiscountAmt;

	private Double payAmount;

	private Integer paymentMethod;

	private String expressCompany;

	private String expressNo;

	private String orderDate;

	private Date orderDatetime;

	private String remark;
	private String buyStatus;
	private Double expressAmount;
	
	private PageTools pageTools;
	private UserInfo user;
	private CustInfo custInfo;
	
	private Integer oldOrderStatus;
	
	// query
	private String startDate;
	private String endDate ;
	
//	private String startDate = DateUtils.format(new Date(),"yyyyMM01", Locale.getDefault());
//	private String endDate = DateUtils.format(new Date(), DateFormateType.TIGHT_SHORT_FORMAT, Locale.getDefault());
	
	public Double getCommission(){
		return new BigDecimal(cashOnDeliveryAmt*0.03).setScale(2,BigDecimal.ROUND_HALF_DOWN).doubleValue();
	}
	
	public Long getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getoWechatNo() {
		return oWechatNo;
	}

	public void setoWechatNo(String oWechatNo) {
		this.oWechatNo = oWechatNo;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getoPhone() {
		return oPhone;
	}

	public void setoPhone(Long oPhone) {
		this.oPhone = oPhone;
	}

	public String getProductList() {
		return productList;
	}

	public void setProductList(String productList) {
		this.productList = productList;
	}

	public Double getDeposits() {
		return deposits;
	}

	public void setDeposits(Double deposits) {
		this.deposits = deposits;
	}

	public Double getCashOnDeliveryAmt() {
		return cashOnDeliveryAmt;
	}

	public void setCashOnDeliveryAmt(Double cashOnDeliveryAmt) {
		this.cashOnDeliveryAmt = cashOnDeliveryAmt;
	}

	public Double getTotalAmt() {
		return totalAmt;
	}

	public void setTotalAmt(Double totalAmt) {
		this.totalAmt = totalAmt;
	}

	public Double getAfterDiscountAmt() {
		return afterDiscountAmt;
	}

	public void setAfterDiscountAmt(Double afterDiscountAmt) {
		this.afterDiscountAmt = afterDiscountAmt;
	}

	public Double getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(Double payAmount) {
		this.payAmount = payAmount;
	}

	public Integer getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(Integer paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getExpressCompany() {
		return expressCompany;
	}

	public void setExpressCompany(String expressCompany) {
		this.expressCompany = expressCompany;
	}

	public String getExpressNo() {
		return expressNo;
	}

	public void setExpressNo(String expressNo) {
		this.expressNo = expressNo;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public Date getOrderDatetime() {
		return orderDatetime;
	}

	public void setOrderDatetime(Date orderDatetime) {
		this.orderDatetime = orderDatetime;
	}

	public PageTools getPageTools() {
		return pageTools;
	}

	public void setPageTools(PageTools pageTools) {
		this.pageTools = pageTools;
	}

	public UserInfo getUser() {
		return user;
	}

	public void setUser(UserInfo user) {
		this.user = user;
	}

	public CustInfo getCustInfo() {
		return custInfo;
	}

	public void setCustInfo(CustInfo custInfo) {
		this.custInfo = custInfo;
	}

	public Integer getOldOrderStatus() {
		return oldOrderStatus;
	}

	public void setOldOrderStatus(Integer oldOrderStatus) {
		this.oldOrderStatus = oldOrderStatus;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public String getBuyStatus() {
		return buyStatus;
	}

	public void setBuyStatus(String buyStatus) {
		this.buyStatus = buyStatus;
	}

	public Double getExpressAmount() {
		return expressAmount;
	}

	public void setExpressAmount(Double expressAmount) {
		this.expressAmount = expressAmount;
	}

	@Override
	public String toString() {
		return String.format(
				"CustOrder [orderNo=%s, userId=%s, oWechatNo=%s, orderStatus=%s, contact=%s, oPhone=%s, deposits=%s, cashOnDeliveryAmt=%s, totalAmt=%s, afterDiscountAmt=%s, payAmount=%s, paymentMethod=%s, expressNo=%s, orderDate=%s, orderDatetime=%s, remark=%s, oldOrderStatus=%s, startDate=%s, endDate=%s]",
				orderNo, userId, oWechatNo, orderStatus, contact, oPhone, deposits, cashOnDeliveryAmt, totalAmt,
				afterDiscountAmt, payAmount, paymentMethod, expressNo, orderDate, orderDatetime, remark, oldOrderStatus,
				startDate, endDate);
	}




}