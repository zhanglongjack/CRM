package com.base.crm.ad.dao;

import java.util.List;
import java.util.Map;

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

	List<Map<String, Object>> selectAdAcctIdByWechatNo(String wechatNo);

	Long countWechatGroupExistsBy(Map<String, String> params);
}