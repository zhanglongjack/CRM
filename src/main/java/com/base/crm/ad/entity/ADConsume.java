package com.base.crm.ad.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Locale;

import org.thymeleaf.util.DateUtils;

import com.base.common.util.DateFormateType;
import com.base.common.util.PageTools;

public class ADConsume {
    private Integer id;

    private String consumeAccountType;

    private BigDecimal consumeAmount;

    private String consumeDate;
	// query
	private String startDate = DateUtils.format(new Date(),"yyyyMM01", Locale.getDefault());
	private String endDate = DateUtils.format(new Date(), DateFormateType.TIGHT_SHORT_FORMAT, Locale.getDefault());
	
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
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getConsumeAccountType() {
        return consumeAccountType;
    }

    public void setConsumeAccountType(String consumeAccountType) {
        this.consumeAccountType = consumeAccountType;
    }

    public BigDecimal getConsumeAmount() {
        return consumeAmount;
    }

    public void setConsumeAmount(BigDecimal consumeAmount) {
        this.consumeAmount = consumeAmount;
    }

	public String getConsumeDate() {
		return consumeDate;
	}

	public void setConsumeDate(String consumeDate) {
		this.consumeDate = consumeDate;
	}

    
}