package com.base.crm.depository.entity;

import com.base.common.util.PageTools;

public class Depository {
    private Long depositoryId;

    private String depositoryName;

    private String createdDate;

    public Long getDepositoryId() {
        return depositoryId;
    }

    public void setDepositoryId(Long depositoryId) {
        this.depositoryId = depositoryId;
    }

    public String getDepositoryName() {
        return depositoryName;
    }

    public void setDepositoryName(String depositoryName) {
        this.depositoryName = depositoryName;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
    
	// query
	private String startDate;
	private String endDate;
	
//    @JsonIgnore
    private PageTools pageTools;

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public PageTools getPageTools() {
		return pageTools;
	}

	public void setPageTools(PageTools pageTools) {
		this.pageTools = pageTools;
	}
    
}