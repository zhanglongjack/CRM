package com.base.crm.orders.service;

import java.util.List;

import com.base.crm.orders.entity.OrderProductList;

public interface OrderProductListService {
    int deleteByPrimaryKey(Long listId);

    int insertSelective(OrderProductList record);

    OrderProductList selectByPrimaryKey(Long listId);

    int updateByPrimaryKeySelective(OrderProductList record);

	Long selectPageTotalCount(OrderProductList record);

	List<OrderProductList> selectPageByObjectForList(OrderProductList record);

	List<OrderProductList> selectByObjectForList(OrderProductList record);

	void deleteByOrderId(Long orderNo);
}