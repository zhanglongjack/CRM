package com.base.crm.customer.service;

import java.util.List;
import java.util.Map;

import com.base.crm.customer.entity.CustInfo;

public interface CustInfoService {
    int deleteByPrimaryKey(Long custId);

    int insertSelective(CustInfo record);

    CustInfo selectByPrimaryKey(Long custId);

    int updateByPrimaryKeySelective(CustInfo record);

	List<CustInfo> selectByObjectForList(CustInfo ci);

	Long selectPageTotalCount(CustInfo ci);

	CustInfo selectByPrimaryWechatNo(String checkWechatNo);

	Map<String, Integer> selectCustCountByMonth(String month);

	int updateCustOrderStatus();
}