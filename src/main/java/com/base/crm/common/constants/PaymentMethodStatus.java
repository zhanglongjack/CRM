package com.base.crm.common.constants;

import java.util.HashMap;
import java.util.Map;

public class PaymentMethodStatus {
	public static Map<Integer,String> paymentMethodMap = new HashMap<Integer,String>();
	
	static{
		paymentMethodMap.put(0, "微信支付");
		paymentMethodMap.put(1, "货到付款");
		paymentMethodMap.put(2, "定金-到付");
		paymentMethodMap.put(3, "赠品");
	}
	
	
}
