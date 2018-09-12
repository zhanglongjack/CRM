package com.base.crm.consume.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.base.common.util.PageTools;
import com.base.crm.users.entity.UserInfo;

public class CustomerConsume {
    private Long consumeId;

    private String wechatNo;

    private Long userId;

    private BigDecimal amount;

    private Long orderNo;

    private Integer consumeType;

    private String consumeDate;

    private Date consumeTime;

    private String remark;

	private PageTools pageTools;
	private UserInfo user;
	
	
    public Long getConsumeId() {
        return consumeId;
    }

    public void setConsumeId(Long consumeId) {
        this.consumeId = consumeId;
    }

    public String getWechatNo() {
        return wechatNo;
    }

    public void setWechatNo(String wechatNo) {
        this.wechatNo = wechatNo;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getConsumeType() {
        return consumeType;
    }

    public void setConsumeType(Integer consumeType) {
        this.consumeType = consumeType;
    }

    public String getConsumeDate() {
        return consumeDate;
    }

    public void setConsumeDate(String consumeDate) {
        this.consumeDate = consumeDate;
    }

    public Date getConsumeTime() {
        return consumeTime;
    }

    public void setConsumeTime(Date consumeTime) {
        this.consumeTime = consumeTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

	public PageTools getPageTools() {
		return pageTools;
	}

	public void setPageTools(PageTools pageTools) {
		this.pageTools = pageTools;
	}

	public UserInfo getUser() {
		return user;
	}

	public void setUser(UserInfo user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return String.format(
				"CustomerConsume [consumeId=%s, wechatNo=%s, userId=%s, amount=%s, orderNo=%s, consumeType=%s, consumeDate=%s, consumeTime=%s, remark=%s, pageTools=%s, user=%s]",
				consumeId, wechatNo, userId, amount, orderNo, consumeType, consumeDate, consumeTime, remark, pageTools,
				user);
	}
    
    
}