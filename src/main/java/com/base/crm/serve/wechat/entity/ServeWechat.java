package com.base.crm.serve.wechat.entity;

import com.base.common.util.PageTools;

public class ServeWechat {
	private Long id;
	private String serveWechatNo;
	private Long userId;
	private String consumeAcctType;

	private PageTools pageTools;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getServeWechatNo() {
		return serveWechatNo;
	}

	public void setServeWechatNo(String serveWechatNo) {
		this.serveWechatNo = serveWechatNo;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getConsumeAcctType() {
		return consumeAcctType;
	}

	public void setConsumeAcctType(String consumeAcctType) {
		this.consumeAcctType = consumeAcctType;
	}

	public PageTools getPageTools() {
		return pageTools;
	}

	public void setPageTools(PageTools pageTools) {
		this.pageTools = pageTools;
	}

	@Override
	public String toString() {
		return String.format("ServeWechat [id=%s, serveWechatNo=%s, userId=%s, pageTools=%s]", id, serveWechatNo,
				userId, pageTools);
	}

}