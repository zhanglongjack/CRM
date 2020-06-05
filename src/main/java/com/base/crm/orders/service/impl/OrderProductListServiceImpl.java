package com.base.crm.orders.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.crm.orders.dao.OrderProductListMapper;
import com.base.crm.orders.entity.OrderProductList;
import com.base.crm.orders.service.OrderProductListService;

@Service
public class OrderProductListServiceImpl implements OrderProductListService {

	@Autowired
	private OrderProductListMapper orderProductMapper;
	@Override
	public int deleteByPrimaryKey(Long listId) {
		return orderProductMapper.deleteByPrimaryKey(listId);
	}

	@Override
	public int insertSelective(OrderProductList record) {
		return orderProductMapper.insertSelective(record);
	}

	@Override
	public OrderProductList selectByPrimaryKey(Long listId) {
		return orderProductMapper.selectByPrimaryKey(listId);
	}

	@Override
	public int updateByPrimaryKeySelective(OrderProductList record) {
		return orderProductMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public Long selectPageTotalCount(OrderProductList record) {
		return orderProductMapper.selectPageTotalCount(record);
	}

	@Override
	public List<OrderProductList> selectPageByObjectForList(OrderProductList record) {
		return orderProductMapper.selectPageByObjectForList(record);
	}

	@Override
	public List<OrderProductList> selectByObjectForList(OrderProductList record) {
		if(record!=null){
			record.setPageTools(null);
		}
		return selectPageByObjectForList(record);
	}

	@Override
	public void deleteByOrderId(Long orderNo) {
		orderProductMapper.deleteByOrderId(orderNo);
	}

}