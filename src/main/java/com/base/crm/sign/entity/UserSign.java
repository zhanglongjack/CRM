package com.base.crm.sign.entity;

import java.util.Date;
import java.util.Locale;

import org.thymeleaf.util.DateUtils;

import com.base.common.util.DateFormateType;
import com.base.common.util.PageTools;

public class UserSign {
	private Long id;

	private Long userId;

	private String userName;

	private String signDate;

	private String signTime;

	private String signExitTime;

	// query
	private String startDate = DateUtils.format(new Date(), "yyyyMM01", Locale.getDefault());
	private String endDate = DateUtils.format(new Date(), DateFormateType.TIGHT_SHORT_FORMAT, Locale.getDefault());

	private PageTools pageTools;

	public UserSign() {
	}

	public UserSign(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSignDate() {
		return signDate;
	}

	public void setSignDate(String signDate) {
		this.signDate = signDate;
	}

	public String getSignTime() {
		return signTime;
	}

	public void setSignTime(String signTime) {
		this.signTime = signTime;
	}

	public String getSignExitTime() {
		return signExitTime;
	}

	public void setSignExitTime(String signExitTime) {
		this.signExitTime = signExitTime;
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

	@Override
	public String toString() {
		return String.format(
				"UserSign [id=%s, userId=%s, userName=%s, signDate=%s, signTime=%s, signExitTime=%s, startDate=%s, endDate=%s, pageTools=%s]",
				id, userId, userName, signDate, signTime, signExitTime, startDate, endDate, pageTools);
	}

}