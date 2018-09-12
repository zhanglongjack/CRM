package com.base.crm.revisit.record.entity;

import java.util.Date;

import com.base.common.util.PageTools;
import com.base.crm.users.entity.UserInfo;

public class CustRevisitRecord {
	private Long revisitId;

	private String wechatNo;

	private Long userId;

	private String revisitRecordInfo;

	private String revisitResult;

	private String revisitDate;

	private String revisitTime;

	private Date createDate;

	private PageTools pageTools;
	private UserInfo user;

	public Long getRevisitId() {
		return revisitId;
	}

	public void setRevisitId(Long revisitId) {
		this.revisitId = revisitId;
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

	public String getRevisitRecordInfo() {
		return revisitRecordInfo;
	}

	public void setRevisitRecordInfo(String revisitRecordInfo) {
		this.revisitRecordInfo = revisitRecordInfo;
	}

	public String getRevisitResult() {
		return revisitResult;
	}

	public void setRevisitResult(String revisitResult) {
		this.revisitResult = revisitResult;
	}

	public String getRevisitDate() {
		return revisitDate;
	}

	public void setRevisitDate(String revisitDate) {
		this.revisitDate = revisitDate;
	}

	public String getRevisitTime() {
		return revisitTime;
	}

	public void setRevisitTime(String revisitTime) {
		this.revisitTime = revisitTime;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
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
				"CustRevisitRecord [revisitId=%s, wechatNo=%s, userId=%s, revisitRecordInfo=%s, revisitResult=%s, revisitDate=%s, revisitTime=%s, createDate=%s, pageTools=%s, user=%s]",
				revisitId, wechatNo, userId, revisitRecordInfo, revisitResult, revisitDate, revisitTime, createDate,
				pageTools, user);
	}

}