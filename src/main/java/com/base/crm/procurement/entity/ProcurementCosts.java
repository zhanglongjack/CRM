package com.base.crm.procurement.entity;

import java.util.Date;
import java.util.Locale;

import org.thymeleaf.util.DateUtils;

import com.base.common.util.DateFormateType;
import com.base.common.util.PageTools;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class ProcurementCosts {
    private Long id;

    private String productName;

    private Integer num;

    private String amount;

    private String createdDate;
    
	// query
	private String startDate = DateUtils.format(new Date(),"yyyyMM01", Locale.getDefault());
	private String endDate = DateUtils.format(new Date(), DateFormateType.TIGHT_SHORT_FORMAT, Locale.getDefault());

    @JsonIgnore 
    private PageTools pageTools;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

	public PageTools getPageTools() {
		return pageTools;
	}

	public void setPageTools(PageTools pageTools) {
		this.pageTools = pageTools;
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

	@Override
	public String toString() {
		return String.format(
				"ProcurementCosts [id=%s, productName=%s, num=%s, amount=%s, createdDate=%s, startDate=%s, endDate=%s, pageTools=%s]",
				id, productName, num, amount, createdDate, startDate, endDate, pageTools);
	}
    
}