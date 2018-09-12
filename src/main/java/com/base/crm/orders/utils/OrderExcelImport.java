package com.base.crm.orders.utils;

import org.apache.poi.ss.usermodel.Row;

import com.base.common.util.ExcelImport;
import com.base.crm.common.constants.OrderStatus;
import com.base.crm.orders.entity.CustOrder;

public class OrderExcelImport extends ExcelImport<CustOrder> {
	
	@Override
	protected CustOrder buildObjectByRow(Row row) {
		CustOrder order = new CustOrder();
		order.setOrderNo(Long.parseLong(conversCell(row.getCell(0))));
		order.setExpressCompany(conversCell(row.getCell(1)));
		order.setExpressNo(conversCell(row.getCell(2)));
		order.setOrderStatus(OrderStatus.DELIVERING.getKey());
		order.setOldOrderStatus(OrderStatus.WAITING.getKey());
		return order;
	}
}
