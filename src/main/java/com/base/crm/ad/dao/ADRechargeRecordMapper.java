package com.base.crm.ad.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.base.crm.ad.entity.ADRechargeRecord;

@Mapper
public interface ADRechargeRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(ADRechargeRecord record);

    ADRechargeRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ADRechargeRecord record);

	Long selectPageTotalCount(ADRechargeRecord queryObject);

	List<ADRechargeRecord> selectPageByObjectForList(ADRechargeRecord queryObject);

}