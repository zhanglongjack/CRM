package com.base.crm.orders.constants;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.base.common.util.ExcelMappingsAbstract;
import com.base.crm.common.constants.OrderStatus;
import com.base.crm.common.constants.PaymentMethodStatus;
import com.base.crm.users.constants.UserContainer;

@Component
public class OrderExcelMappings extends ExcelMappingsAbstract {
	private static final Logger logger = LoggerFactory.getLogger(OrderExcelMappings.class);
	private static String sheetName ="订单信息";
	
	private static List<String> headerList = Arrays.asList(
			"订单编号","快递公司","快递单号","客服名称","联系人","电话","地址","产品列表","付款方式",
			"定金","到付金额","总金额", "折后金额","实付金额",
			"下单日期","订单状态");
	
	public static List<String> columnsMappings = Arrays.asList(
			"orderNo","expressCompany","expressNo","userId","contact","oPhone","address","productList","paymentMethod",
			"deposits","cashOnDeliveryAmt","totalAmt","afterDiscountAmt","payAmount",
			"orderDate","orderStatus");
	
	public void setOrderExcelMappings(JSONArray jsonObj) {
		this.data = jsonObj;
		this.setSheetName(sheetName);
	}

	@Override
	public List<String> getHeaderList() {
		return headerList;
	}

	@Override
	public List<String> getColumnsMappings() {
		return columnsMappings;
	}

}