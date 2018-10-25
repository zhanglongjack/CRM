package com.base.crm.common.constants;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class StaffLevel {
	public static Map<Integer,String> staffLevelMap = new HashMap<Integer,String>();
	
	static{
		staffLevelMap.put(0, "超级管理员");
		staffLevelMap.put(1, "客服主管");
		staffLevelMap.put(2, "客服领班");
		staffLevelMap.put(3, "普通客服");
	}

	public String get(Integer key){
		return staffLevelMap.get(key);
	}

	
	
	
	
}
