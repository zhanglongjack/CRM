package com.base.crm.ad.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.base.crm.ad.entity.ADConsume;

public interface ADConsumeService {
    int deleteByPrimaryKey(Long id);

    int insertSelective(ADConsume record);

    ADConsume selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ADConsume record);

	Long selectPageTotalCount(ADConsume queryObject);

	List<ADConsume> selectPageByObjectForList(ADConsume queryObject);

	void batchInsert(List<ADConsume> data);

	List<ADConsume> selectBySelective(ADConsume queryObject);

	List<ADConsume> querySummaryConsumeAmount(String month);

	Map<String, BigDecimal> queryRealConsumeAd(String month);
}