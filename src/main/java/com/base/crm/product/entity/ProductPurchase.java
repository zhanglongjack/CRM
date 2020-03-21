package com.base.crm.product.entity;

import java.math.BigDecimal;

import com.base.common.util.PageTools;

public class ProductPurchase {
    private Long purchaseId;

    private Long depositoryId;

    private Long productId;

    private Long purchaseNum;

    private BigDecimal purchasePrice;

    private String unit;

    private String status;

    private String purchaseDatetime;

    private String updatedDate;

    private String createdDate;
    
    private String oldStatus;

    public Long getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(Long purchaseId) {
        this.purchaseId = purchaseId;
    }

    public Long getDepositoryId() {
        return depositoryId;
    }

    public void setDepositoryId(Long depositoryId) {
        this.depositoryId = depositoryId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getPurchaseNum() {
        return purchaseNum;
    }

    public void setPurchaseNum(Long purchaseNum) {
        this.purchaseNum = purchaseNum;
    }

    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(BigDecimal purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPurchaseDatetime() {
        return purchaseDatetime;
    }

    public void setPurchaseDatetime(String purchaseDatetime) {
        this.purchaseDatetime = purchaseDatetime;
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

	public String getOldStatus() {
		return oldStatus;
	}

	public void setOldStatus(String oldStatus) {
		this.oldStatus = oldStatus;
	}

	@Override
	public String toString() {
		return String.format(
				"ProductPurchase [purchaseId=%s, depositoryId=%s, productId=%s, purchaseNum=%s, purchasePrice=%s, unit=%s, status=%s, purchaseDatetime=%s]",
				purchaseId, depositoryId, productId, purchaseNum, purchasePrice, unit, status, purchaseDatetime);
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