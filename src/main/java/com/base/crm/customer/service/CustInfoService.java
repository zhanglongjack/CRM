package com.base.crm.customer.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.base.crm.customer.entity.CustInfo;
import com.base.crm.customer.entity.CustInfoReport;

public interface CustInfoService {
    int deleteByPrimaryKey(Long custId);

    int insertSelective(CustInfo record);

    CustInfo selectByPrimaryKey(Long custId);

    int updateByPrimaryKeySelective(CustInfo record);

	List<CustInfo> selectByObjectForList(CustInfo ci);

	Long selectPageTotalCount(CustInfo ci);

	CustInfo selectByPrimaryWechatNo(String checkWechatNo);

//	Map<String, Integer> selectCustCountByMonth(String month,Long userId);

	int updateCustOrderStatus();

	Map<String, Object> queryAddCustCountBy(String month, String serveWechatNo);
	Map<String, Object> queryAddCustCountBy(String month, String consumeWechatNo, Long userId);

	BigDecimal queryServerSalePerformanBy(String month, String serveWechatNo);
	BigDecimal queryServerSalePerformanBy(String month, String consumeWechatNo, Long serverId);

	/**
	 * 微信加粉报表分页
	 */
	Long selectCustCountByMonth(Map<String, Object> params);
	
	Long selectWechatAddFansNumByDailyPageCount(CustInfoReport queryObject);
	
	List<CustInfoReport> selectWechatAddFansNumByDailyPage(CustInfoReport queryObject);
	
	Long selectWechatAddFansNumByMonthPageCount(CustInfoReport queryObject);
	
	List<CustInfoReport> selectWechatAddFansNumByMonthPage(CustInfoReport queryObject);


}