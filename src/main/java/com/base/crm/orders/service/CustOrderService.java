package com.base.crm.orders.service;

import java.math.BigDecimal;
import java.util.List;

import com.base.crm.orders.entity.CustOrder;

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

	BigDecimal querySumAmountByMonth(String month);
}