package com.base.crm.customer.entity;

import com.base.common.util.PageTools;
import com.base.crm.level.entity.Level;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class CustInfo {
    private Long custId;
    
    private Long userId;
    
    private String custName;
    
    private String custWechatNo;
    
    private String serveWechatNo;

    private Long custPhone;

    private Integer level;
    
    private Double amt;

    private String addTime;

    private String address;

    private String remark;
    
    private String orderStatus;
    
    private String status;
    
    private String createDate;
    
    private String updateDate;
    

    @JsonIgnore
    private PageTools pageTools;
    private Level levelInfo;

    public PageTools getPageTools() {
		return pageTools;
	}

	public void setPageTools(PageTools pageTools) {
		this.pageTools = pageTools;
	}

	public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustWechatNo() {
        return custWechatNo;
    }

    public void setCustWechatNo(String custWechatNo) {
        this.custWechatNo = custWechatNo;
    }

    public String getServeWechatNo() {
		return serveWechatNo;
	}

	public void setServeWechatNo(String serveWechatNo) {
		this.serveWechatNo = serveWechatNo;
	}

	public Long getCustPhone() {
        return custPhone;
    }

    public void setCustPhone(Long custPhone) {
        this.custPhone = custPhone;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Double getAmt() {
		return amt;
	}

	public void setAmt(Double amt) {
		this.amt = amt;
	}

	public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public Level getLevelInfo() {
		return levelInfo;
	}

	public void setLevelInfo(Level levelInfo) {
		this.levelInfo = levelInfo;
	}

	@Override
	public String toString() {
		return String.format(
				"CustInfo [custId=%s, userId=%s, custName=%s, custWechatNo=%s, serveWechatNo=%s, custPhone=%s, level=%s, amt=%s, addTime=%s, address=%s, remark=%s, orderStatus=%s, status=%s, createDate=%s, updateDate=%s, pageTools=%s, levelInfo=%s]",
				custId, userId, custName, custWechatNo, serveWechatNo, custPhone, level, amt, addTime, address, remark,
				orderStatus, status, createDate, updateDate, pageTools, levelInfo);
	}



}