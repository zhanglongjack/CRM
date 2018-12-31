package com.base.crm.orders.service;

import java.util.List;
import java.util.Map;

import com.base.crm.orders.entity.CustOrder;
import com.base.crm.report.entity.SummaryReport;

public interface CustOrderService {
    int deleteByPrimaryKey(Long orderNo);

    int insert(CustOrder record);

    int insertSelective(CustOrder record);

    CustOrder selectByPrimaryKey(Long orderNo);

    int updateByPrimaryKeySelective(CustOrder record);

    int updateByPrimaryKey(CustOrder record);

	List<CustOrder> selectByObjectForList(CustOrder order);

	Long selectPageTotalCount(CustOrder order);

	List<CustOrder> selectPageByObjectForList(CustOrder order);

	void batchUpdateOrders(List<CustOrder> orderList);

	SummaryReport querySumAmountByMonth(String month);

	Map<String, Integer> selectOrderCountByMonth(String month);

	void doUpdateBuyStatus();

}