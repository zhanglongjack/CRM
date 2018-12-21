package com.base.crm.ad.service;

import java.util.List;

import com.base.crm.ad.entity.ADRechargeRecord;

public interface ADRechargeRecordService {
    int deleteByPrimaryKey(Long id);

    int insertSelective(ADRechargeRecord record);

    ADRechargeRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ADRechargeRecord record);

	Long selectPageTotalCount(ADRechargeRecord queryObject);

	List<ADRechargeRecord> selectPageByObjectForList(ADRechargeRecord queryObject);

}