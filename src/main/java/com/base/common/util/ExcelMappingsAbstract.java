package com.base.common.util;

import java.util.List;

import com.alibaba.fastjson.JSONArray;

public abstract class ExcelMappingsAbstract {
	protected String sheetName;

	protected JSONArray data;

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	public abstract List<String> getHeaderList();

	public abstract List<String> getColumnsMappings();

	public JSONArray getData() {
		return data;
	}

	public void setData(JSONArray data) {
		this.data = data;
	}
	
	
	

}
