package com.base.common.util;

public class PageTools {
	private Integer index = 1;
	private Integer pageSize = 50;
	private Integer rowIndex;
	private Long total;
	private Long limitRows;
	
	public PageTools() { }
	public PageTools(Integer index, Integer pageSize) {
		this.index = index;
		this.pageSize = pageSize;
	}
	
	public PageTools(Long limitRows) {
		this.limitRows = limitRows;
	}
	public Integer getIndex() {
		return index;
	}
	public void setIndex(Integer index) {
		this.index = index;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
	public Integer getRowIndex() {
		rowIndex =(index-1)*pageSize;
		return rowIndex;
	}
	public void setRowIndex(Integer rowIndex) {
		this.rowIndex = rowIndex;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
		int totalPage = (int) ((this.total-1)/this.pageSize+1);
		if(this.index>totalPage){
			this.index=totalPage;
		}
	}
	
	public Long getLimitRows() {
		return limitRows;
	}
	public void setLimitRows(Long limitRows) {
		this.limitRows = limitRows;
	}
	@Override
	public String toString() {
		return String.format("PageTools [index=%s, pageSize=%s, rowIndex=%s, total=%s]", index, pageSize, rowIndex,
				total);
	}

}
