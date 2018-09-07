package com.base.crm.orders.constants;

import java.util.Arrays;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.base.common.util.ExcelMappingsAbstract;

public class OrderExcelMappings extends ExcelMappingsAbstract {
	private static String sheetName ="订单信息";
	
	private static List<String> headerList = Arrays.asList("订单号","订单状态","联系人","地址","电话","产品列表","定金","到付金额","总金额",
			"付款方式","快递公司","快递单号","签收情况","下单日期","下单时间");
	
	private static List<String> columnsMappings = Arrays.asList("orderNo","orderStatus","contact","address","oPhone","productList",
			"deposits","cashOnDeliveryAmt","totalAmt","paymentMethod","expressCompany",
			"expressNo","signing","orderDate","orderDatetime");
	
	public OrderExcelMappings(JSONArray jsonObj) {
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