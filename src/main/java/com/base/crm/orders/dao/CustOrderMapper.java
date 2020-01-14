package com.base.crm.orders.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.base.crm.orders.entity.CustOrder;
import com.base.crm.orders.entity.OrderSalesRateReport;
import com.base.crm.report.entity.SummaryReport;
@Mapper
public interface CustOrderMapper {
    int deleteByPrimaryKey(Long orderNo);

    int insert(CustOrder record);

    int insertSelective(CustOrder record);

    CustOrder selectByPrimaryKey(Long orderNo);

    int updateByPrimaryKeySelective(CustOrder record);

    int updateByPrimaryKey(CustOrder record);

	List<CustOrder> selectByObjectForList(CustOrder order);

	Long selectPageTotalCount(CustOrder order);

	List<CustOrder> selectPageByObjectForList(CustOrder order);

//	SummaryReport querySumAmountByMonth(String month);
	SummaryReport querySumAmountByMonth(Map<String, Object> params);

//	Map<String, Integer> selectOrderCountByMonth(Map<String, Object> params);

	void updateBuyStatusByErrBuyStatus();

	Map<String, Double> selectOrderSummaryBy(Map<String, Object> params);

	List<SummaryReport> selectDailyKPIOrderSummaryPageBy(CustOrder queryObject);

	Long selectDailyKPIOrderSummaryPageCountBy(CustOrder queryObject);
	
	List<SummaryReport> selectDailyKPIOrderSummaryPageByMonth(CustOrder queryObject);
	
	Long selectDailyKPIOrderSummaryPageCountByMonth(CustOrder queryObject);
	
	List<OrderSalesRateReport> selectOrderSalesRateReportPageByMonth(OrderSalesRateReport queryObject);
	
	Long selectOrderSalesRateReportPageCountByMonth(OrderSalesRateReport queryObject);
	
	List<OrderSalesRateReport> selectSalesRateReportPageByMonth(OrderSalesRateReport queryObject);
	
	Long selectSalesRateReportPageCountByMonth(OrderSalesRateReport queryObject);

	
}