package com.base.crm.product.entity;

import com.base.common.util.PageTools;

public class Product {
    private Long productId;

    private String productName;

    private String status;

    private String updatedDate;

    private String createdDate;

	// query
	private String startDate;
	private String endDate;
	
//    @JsonIgnore
    private PageTools pageTools;
    
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

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