package com.base.crm.orders.entity;

import java.math.BigDecimal;
import java.util.Date;

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

	private BigDecimal deposits;

	private BigDecimal cashOnDeliveryAmt;

	private BigDecimal totalAmt;

	private BigDecimal afterDiscountAmt;

	private BigDecimal payAmount;

	private Integer paymentMethod;

	private String expressCompany;

	private String expressNo;

	private String orderDate;

	private Date orderDatetime;

	private PageTools pageTools;
	private UserInfo user;
	private CustInfo custInfo;

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

	public BigDecimal getDeposits() {
		return deposits;
	}

	public void setDeposits(BigDecimal deposits) {
		this.deposits = deposits;
	}

	public BigDecimal getCashOnDeliveryAmt() {
		return cashOnDeliveryAmt;
	}

	public void setCashOnDeliveryAmt(BigDecimal cashOnDeliveryAmt) {
		this.cashOnDeliveryAmt = cashOnDeliveryAmt;
	}

	public BigDecimal getTotalAmt() {
		return totalAmt;
	}

	public void setTotalAmt(BigDecimal totalAmt) {
		this.totalAmt = totalAmt;
	}

	public BigDecimal getAfterDiscountAmt() {
		return afterDiscountAmt;
	}

	public void setAfterDiscountAmt(BigDecimal afterDiscountAmt) {
		this.afterDiscountAmt = afterDiscountAmt;
	}

	public BigDecimal getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(BigDecimal payAmount) {
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

	@Override
	public String toString() {
		return String.format(
				"CustOrder [orderNo=%s, userId=%s, oWechatNo=%s, orderStatus=%s, contact=%s, oPhone=%s, productList=%s, deposits=%s, afterDiscountAmt=%s, payAmount=%s, cashOnDeliveryAmt=%s, totalAmt=%s, paymentMethod=%s, expressNo=%s, orderDate=%s, pageTools=%s, user=%s, custInfo=%s]",
				orderNo, userId, oWechatNo, orderStatus, contact, oPhone, productList, deposits, afterDiscountAmt,
				payAmount, cashOnDeliveryAmt, totalAmt, paymentMethod, expressNo, orderDate, pageTools, user, custInfo);
	}

}