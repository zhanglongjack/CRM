package com.base.crm.ad.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.base.crm.ad.entity.ADAcctType;

@Mapper
public interface ADAcctTypeMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(ADAcctType record);

    ADAcctType selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ADAcctType record);
    
	Long selectPageTotalCount(ADAcctType queryObject);

	List<ADAcctType> selectPageByObjectForList(ADAcctType queryObject);

	ADAcctType selectAdAcctIdByWechatNo(String wechatNo);
}