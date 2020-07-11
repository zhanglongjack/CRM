package com.base.crm.ad.service;

import java.util.List;
import java.util.Map;

import com.base.crm.ad.entity.ADAcctType;
import com.sun.istack.internal.NotNull;

public interface ADAcctTypeService {
	
    int deleteByPrimaryKey(Long id);

    int insertSelective(ADAcctType record);

    ADAcctType selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ADAcctType record);
    
	Long selectPageTotalCount(ADAcctType queryObject);

	List<ADAcctType> selectPageByObjectForList(ADAcctType queryObject);
	
	List<Map<String, Object>> selectAdAcctIdByWechatNo(String wechatNo);

	Long countWechatGroupExistsBy(@NotNull String serveWechatNo, @NotNull String addDate);

}