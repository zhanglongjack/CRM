package com.base.crm.ad.entity;

import java.math.BigDecimal;
import com.base.common.util.PageTools;

public class ADConsume {
    private Integer id;

    private String consumeAccountType;

    private BigDecimal consumeAmount;

    private String consumeDate;
    
    private BigDecimal realAmount;
    
    private BigDecimal rebate;
    
    private String consumeWechatNo;
    
    private Long serverId;
    
    private Long adAcctId;
    
    private double rate; // 新报表出来以后,这个可以删掉
    
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

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public BigDecimal getRealAmount() {
		return realAmount;
	}

	public void setRealAmount(BigDecimal realAmount) {
		this.realAmount = realAmount;
	}

	public BigDecimal getRebate() {
		return rebate;
	}

	public void setRebate(BigDecimal rebate) {
		this.rebate = rebate;
	}

	public String getConsumeWechatNo() {
		return consumeWechatNo;
	}

	public void setConsumeWechatNo(String consumeWechatNo) {
		this.consumeWechatNo = consumeWechatNo;
	}

	public Long getServerId() {
		return serverId;
	}

	public void setServerId(Long serverId) {
		this.serverId = serverId;
	}

	public Long getAdAcctId() {
		return adAcctId;
	}

	public void setAdAcctId(Long adAcctId) {
		this.adAcctId = adAcctId;
	}

}