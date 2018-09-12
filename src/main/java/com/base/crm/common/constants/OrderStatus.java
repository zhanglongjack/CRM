package com.base.crm.common.constants;

import java.util.HashMap;
import java.util.Map;

public enum OrderStatus {
	NON_DELIVERY(0, "已下单"),
	WAITING(1, "待发货"),
	DELIVERING(2, "派送中"),
	SIGNED(3, "已签收"),
	REFUSED(4, "已拒签"),
	INVALIDATED(5, "已作废");
	
	public static final Map<Integer,String> orderStatusMap = new HashMap<Integer,String>();
	
	private Integer key;
	private String value;
	
	private OrderStatus(Integer key, String value) {
		this.key = key;
		this.value = value;
	}

	public Integer getKey() {
		return key;
	}

	public void setKey(Integer key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public static Map<Integer,String> getValues(){
		OrderStatus[] aa = OrderStatus.values();
		if(orderStatusMap.size()>0){
			return orderStatusMap;
		}
		
		for(OrderStatus obj : aa){
			orderStatusMap.put(obj.key, obj.value);
		}
		return orderStatusMap;
	}

	public String toString(){
		return this.value;
	}

}
