package com.base.crm.orders.constants;

import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.base.common.util.ExcelMappingsAbstract;
import com.base.crm.orders.entity.CustOrder;

@Component
public class OrderExcelMappings extends ExcelMappingsAbstract {
//	private static final Logger logger = LoggerFactory.getLogger(OrderExcelMappings.class);
	private static String sheetName ="订单信息";
	
	private static List<String> headerList = Arrays.asList(
			"订单编号","快递公司","快递单号","快递费","发货日期",
			"联系人","电话","地址","产品列表","付款方式",
			"定金","到付金额","总金额","实收金额","快递手续费",
			"下单日期","订单状态","客服名称");
	
	public static List<String> columnsMappings = Arrays.asList(
			"orderNo","expressCompany","expressNo","expressAmount","deliveryDate",
			"contact","oPhone","address","productList","paymentMethod",
			"deposits","cashOnDeliveryAmt","totalAmt","payAmount","commission",
			"orderDate","orderStatus","userId");
	
	public void setOrderExcelMappings(List<CustOrder> data) {
		this.data = JSON.parseArray(JSON.toJSONString(data));
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