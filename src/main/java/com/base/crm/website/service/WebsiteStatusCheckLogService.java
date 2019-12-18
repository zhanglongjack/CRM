package com.base.crm.website.service;

import java.util.List;

import com.base.crm.website.entity.WebsiteStatusCheckLog;

public interface WebsiteStatusCheckLogService {
    int deleteByPrimaryKey(Long id);

    int insertSelective(WebsiteStatusCheckLog record);

    WebsiteStatusCheckLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WebsiteStatusCheckLog record);

	Long selectPageTotalCount(WebsiteStatusCheckLog queryObject);

	List<WebsiteStatusCheckLog> selectPageByObjectForList(WebsiteStatusCheckLog queryObject);
	
}