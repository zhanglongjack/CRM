package com.base.crm.orders.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.base.crm.orders.entity.CustOrder;
import com.base.crm.orders.entity.OrderSalesRateReport;
import com.base.crm.report.entity.ExpressReport;
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

	List<OrderSalesRateReport> selectOrderSalesRateReportPageByMonth(OrderSalesRateReport queryObject);
	
	Long selectOrderSalesRateReportPageCountByMonth(OrderSalesRateReport queryObject);
	
	List<OrderSalesRateReport> selectSalesRateReportPageByMonth(OrderSalesRateReport queryObject);
	
	Long selectSalesRateReportPageCountByMonth(OrderSalesRateReport queryObject);

	List<ExpressReport> selectExpressReportPageBy(ExpressReport queryObject);

	long selectExpressReportPageCountBy(ExpressReport queryObject);

	long selectDailyCountBy(CustOrder queryObject);

	List<SummaryReport> selectServicerKPIForDalilyPageBy(CustOrder queryObject);

	long selectMonthCountBy(CustOrder queryObject);
	
	List<SummaryReport> selectServicerKPIForMonthPageBy(CustOrder queryObject);

	List<SummaryReport> selectSalesPerformanceSummaryReport(CustOrder orderParams);
}