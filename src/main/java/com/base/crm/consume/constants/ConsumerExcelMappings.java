package com.base.crm.consume.constants;

import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.base.common.util.ExcelMappingsAbstract;

@Component
public class ConsumerExcelMappings extends ExcelMappingsAbstract {

	private static String sheetName = "消费信息";

	private static List<String> headerList = Arrays.asList(
			"消费编号", "客服微信", "促消客服", "消费金额", "订单号", 
			"消费类型", "消费日期", "备注");

	public static List<String> columnsMappings = Arrays.asList(
			"consumeId", "wechatNo", "userId", "amount", "orderNo",
			"consumeType", "consumeDate" , "remark");

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