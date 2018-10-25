package com.base.crm.common.constants;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class PaymentMethodStatus {
	public static Map<Integer,String> paymentMethodMap = new HashMap<Integer,String>();
	
	static{
		paymentMethodMap.put(0, "余额支付");
		paymentMethodMap.put(1, "货到付款");
		paymentMethodMap.put(2, "定金-到付");
		paymentMethodMap.put(3, "赠品"); 
	}
	
	public String get(Integer key){
		return paymentMethodMap.get(key);
	}
	
}
