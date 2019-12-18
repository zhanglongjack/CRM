package com.base.crm.website.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.base.crm.website.dao.WebsiteStatusCheckLogMapper;
import com.base.crm.website.entity.WebsiteStatusCheckLog;
import com.base.crm.website.service.WebsiteStatusCheckLogService;

@Component
public class WebsiteStatusCheckLogServiceImpl implements WebsiteStatusCheckLogService {

	@Autowired
	private WebsiteStatusCheckLogMapper websiteStatusCheckLogMapper;
	
	@Override
	public int deleteByPrimaryKey(Long id) {
		return websiteStatusCheckLogMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(WebsiteStatusCheckLog record) {
		return websiteStatusCheckLogMapper.insertSelective(record);
	}

	@Override
	public WebsiteStatusCheckLog selectByPrimaryKey(Long id) {
		return websiteStatusCheckLogMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(WebsiteStatusCheckLog record) {
		return websiteStatusCheckLogMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public Long selectPageTotalCount(WebsiteStatusCheckLog queryObject) {
		return websiteStatusCheckLogMapper.selectPageTotalCount(queryObject);
	}

	@Override
	public List<WebsiteStatusCheckLog> selectPageByObjectForList(WebsiteStatusCheckLog queryObject) {
		return websiteStatusCheckLogMapper.selectPageByObjectForList(queryObject);
	} 
	
	
}