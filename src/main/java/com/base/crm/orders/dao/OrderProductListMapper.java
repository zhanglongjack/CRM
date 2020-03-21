package com.base.crm.orders.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.base.crm.orders.entity.OrderProductList;

@Mapper
public interface OrderProductListMapper {
    int deleteByPrimaryKey(Long listId);

    int insertSelective(OrderProductList record);

    OrderProductList selectByPrimaryKey(Long listId);

    int updateByPrimaryKeySelective(OrderProductList record);

	Long selectPageTotalCount(OrderProductList record);

	List<OrderProductList> selectPageByObjectForList(OrderProductList record);
}