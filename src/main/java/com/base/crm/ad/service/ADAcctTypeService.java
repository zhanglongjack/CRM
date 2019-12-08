package com.base.crm.ad.service;

import java.util.List;

import com.base.crm.ad.entity.ADAcctType;

public interface ADAcctTypeService {
	
    int deleteByPrimaryKey(Long id);

    int insertSelective(ADAcctType record);

    ADAcctType selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ADAcctType record);
    
	Long selectPageTotalCount(ADAcctType queryObject);

	List<ADAcctType> selectPageByObjectForList(ADAcctType queryObject);
}