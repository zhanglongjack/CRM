package com.base.crm.common.constants;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class ConsumeType {
	public static Map<Integer,String> consumeTypeMap = new HashMap<Integer,String>();
	
	static{
		consumeTypeMap.put(1, "余额充值");
		consumeTypeMap.put(2, "订单金额消费");
		consumeTypeMap.put(3, "订单余额消费");
		consumeTypeMap.put(4, "拒签");
		consumeTypeMap.put(5, "赠送余额");
		consumeTypeMap.put(6, "退款金额");
	}
}
