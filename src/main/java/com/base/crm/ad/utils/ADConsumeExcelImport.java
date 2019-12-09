package com.base.crm.ad.utils;

import java.math.BigDecimal;

import org.apache.poi.ss.usermodel.Row;

import com.base.common.util.ExcelImport;
import com.base.crm.ad.entity.ADConsume;

public class ADConsumeExcelImport extends ExcelImport<ADConsume> {
	
	@Override
	protected ADConsume buildObjectByRow(Row row) {
		ADConsume consume = new ADConsume();
		consume.setAdAcctId((long)Double.parseDouble(conversCell(row.getCell(0))));
		consume.setConsumeAmount(new BigDecimal(conversCell(row.getCell(1))));
		consume.setRebate(new BigDecimal(conversCell(row.getCell(2))));
		consume.setConsumeDate(conversCell(row.getCell(3)));
		consume.setRealAmount(computConsume(consume.getConsumeAmount(), consume.getRebate()));
		
//		consume.setConsumeAccountType(conversCell(row.getCell(0)));
//		consume.setConsumeWechatNo(conversCell(row.getCell(3)));
//		consume.setServerId(Long.parseLong(conversCell(row.getCell(4))));
		
		return consume;
	}
	
	private BigDecimal computConsume(BigDecimal consumeAmount,BigDecimal rebate){
		return consumeAmount.divide(rebate.add(new BigDecimal(1)),BigDecimal.ROUND_HALF_EVEN);
	}
}
