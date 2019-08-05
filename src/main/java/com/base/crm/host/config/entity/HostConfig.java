package com.base.crm.host.config.entity;

import com.base.common.util.PageTools;

public class HostConfig {
    private Integer id;

    private String hostName;

    private String wechatNo;

    private Integer flag;

    private String returnWechat;

    private String showTime;
    
    private String createTime;

    
    private PageTools pageTools;
    
    
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

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getWechatNo() {
        return wechatNo;
    }

    public void setWechatNo(String wechatNo) {
        this.wechatNo = wechatNo;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public String getReturnWechat() {
        return returnWechat;
    }

    public void setReturnWechat(String returnWechat) {
        this.returnWechat = returnWechat;
    }

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return String.format(
				"HostConfig [id=%s, hostName=%s, wechatNo=%s, flag=%s, returnWechat=%s, showTime=%s, createTime=%s]",
				id, hostName, wechatNo, flag, returnWechat, showTime, createTime);
	}
    
    
}