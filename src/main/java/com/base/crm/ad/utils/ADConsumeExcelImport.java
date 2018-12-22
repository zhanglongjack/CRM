package com.base.crm.ad.utils;

import java.math.BigDecimal;

import org.apache.poi.ss.usermodel.Row;

import com.base.common.util.ExcelImport;
import com.base.crm.ad.entity.ADConsume;

public class ADConsumeExcelImport extends ExcelImport<ADConsume> {
	
	@Override
	protected ADConsume buildObjectByRow(Row row) {
		ADConsume consume = new ADConsume();
		consume.setConsumeAccountType(conversCell(row.getCell(0)));
		consume.setConsumeAmount(new BigDecimal(conversCell(row.getCell(1))));
		consume.setConsumeDate(conversCell(row.getCell(2)));
		
		return consume;
	}
}
