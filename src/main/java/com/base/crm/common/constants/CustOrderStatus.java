package com.base.crm.common.constants;

import java.util.LinkedHashMap;
import java.util.Map;

public enum CustOrderStatus {
	none( "未购"),
	ordered( "订购"),
	reordered( "复购");
	
	private String value;
	
	private CustOrderStatus( String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public static String convertStatus(String name){
		return OrderStatus.valueOf(name).getValue();
	}

	public static Map<String,String> getValues(){
		Map<String,String> custOrderStatusMap = new LinkedHashMap<String,String>();	
		CustOrderStatus[] aa = CustOrderStatus.values(); 
		for(CustOrderStatus obj : aa){
			custOrderStatusMap.put(obj.toString(), obj.getValue());
		}
		return custOrderStatusMap;
	}
}
