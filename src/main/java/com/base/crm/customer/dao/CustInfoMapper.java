package com.base.crm.customer.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.base.crm.customer.entity.CustInfo;
import com.base.crm.customer.entity.CustInfoReport;

@Mapper
public interface CustInfoMapper {
    int deleteByPrimaryKey(Long custId);

    int insertSelective(CustInfo record);

    CustInfo selectByPrimaryKey(Long custId);

    int updateByPrimaryKeySelective(CustInfo record);

	List<CustInfo> selectByObjectForList(CustInfo ci);

	Long selectPageTotalCount(CustInfo ci);

	CustInfo selectByPrimaryWechatNo(String checkWechatNo);

	Long selectCustCountByMonth(Map<String, Object> params);

	int updateCustOrderStatus();

	Map<String, Object> queryAddCustCountBy(Map<String, String> params);
	
	BigDecimal queryServerSalePerformanBy(Map<String, String> params);
	
	Long selectWechatAddFansNumByDailyPageCount(CustInfoReport queryObject);
	
	List<CustInfoReport> selectWechatAddFansNumByDailyPage(CustInfoReport queryObject);
	
	Long selectWechatAddFansNumByMonthPageCount(CustInfoReport queryObject);
	
	List<CustInfoReport> selectWechatAddFansNumByMonthPage(CustInfoReport queryObject);
	
	
	
	
	
	
	
}