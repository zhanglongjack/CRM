package com.base.crm.orders.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.base.crm.orders.dao.CustOrderMapper;
import com.base.crm.orders.entity.CustOrder;
import com.base.crm.orders.service.CustOrderService;
@Service
public class CustOrderServiceImpl implements CustOrderService {

	@Autowired
	private CustOrderMapper custOrderMapper;
	@Override
	public int deleteByPrimaryKey(Long orderNo) {
		return custOrderMapper.deleteByPrimaryKey(orderNo);
	}

	@Override
	public int insert(CustOrder record) {
		return custOrderMapper.insert(record);
	}

	@Override
	public int insertSelective(CustOrder record) {
		return custOrderMapper.insertSelective(record);
	}

	@Override
	public CustOrder selectByPrimaryKey(Long orderNo) {
		return custOrderMapper.selectByPrimaryKey(orderNo);
	}

	@Override
	public int updateByPrimaryKeySelective(CustOrder record) {
		return custOrderMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(CustOrder record) {
		return custOrderMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<CustOrder> selectByObjectForList(CustOrder order) {
		return custOrderMapper.selectByObjectForList(order);
	}

	@Override
	public Long selectPageTotalCount(CustOrder order) {
		return custOrderMapper.selectPageTotalCount(order);
	}

	@Override
	public List<CustOrder> selectPageByObjectForList(CustOrder order) {
		return custOrderMapper.selectPageByObjectForList(order);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void batchUpdateOrders(List<CustOrder> orderList) {
        for(CustOrder record : orderList){
        	custOrderMapper.updateByPrimaryKeySelective(record);
        }
	}

}