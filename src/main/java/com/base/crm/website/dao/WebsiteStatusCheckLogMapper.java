package com.base.crm.website.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.base.crm.website.entity.WebsiteStatusCheckLog;

@Mapper
public interface WebsiteStatusCheckLogMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(WebsiteStatusCheckLog record);

    WebsiteStatusCheckLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WebsiteStatusCheckLog record);

	Long selectPageTotalCount(WebsiteStatusCheckLog queryObject);

	List<WebsiteStatusCheckLog> selectPageByObjectForList(WebsiteStatusCheckLog queryObject);
	
}