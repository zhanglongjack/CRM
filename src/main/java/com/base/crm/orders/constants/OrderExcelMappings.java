package com.base.crm.orders.constants;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;
import com.base.common.util.ExcelMappingsAbstract;
import com.base.crm.common.constants.PaymentMethodStatus;

public class OrderExcelMappings extends ExcelMappingsAbstract {
	private static final Logger logger = LoggerFactory.getLogger(OrderExcelMappings.class);
	
	private static String sheetName ="订单信息";
	
	private static List<String> headerList = Arrays.asList(
			"订单编号","快递公司","快递单号","联系人","电话","产品列表",
			"定金","到付金额","总金额", "折后金额","实付金额",
			"付款方式","下单日期","地址");
	
	public static List<String> columnsMappings = Arrays.asList(
			"orderNo","expressCompany","expressNo","contact","oPhone","productList",
			"deposits","cashOnDeliveryAmt","totalAmt","afterDiscountAmt","payAmount",
			"paymentMethod","orderDate","address");
	
	public static Map<String,Map<Integer,String>> dictionaries = new HashMap<String, Map<Integer,String>>();
	
	static{
		dictionaries.put("paymentMethod", PaymentMethodStatus.paymentMethodMap);
	}
	
	@Override
	public String getColumnsMappingValue(String dictionarieName,String key){
		if(dictionaries.containsKey(dictionarieName)){
			return dictionaries.get(dictionarieName).get(Integer.parseInt(key));
		}
		return key;
	}
	
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