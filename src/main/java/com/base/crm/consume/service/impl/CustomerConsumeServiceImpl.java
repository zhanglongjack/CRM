package com.base.crm.consume.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.crm.consume.dao.CustomerConsumeMapper;
import com.base.crm.consume.entity.CustomerConsume;
import com.base.crm.consume.service.CustomerConsumeService;

@Service
public class CustomerConsumeServiceImpl implements CustomerConsumeService {

	@Autowired
	private CustomerConsumeMapper customerConsumeMapper;

	@Override
	public int deleteByPrimaryKey(Long consumeId) {
		return customerConsumeMapper.deleteByPrimaryKey(consumeId);
	}

	@Override
	public int insertSelective(CustomerConsume record) {
		return customerConsumeMapper.insertSelective(record);
	}

	@Override
	public CustomerConsume selectByPrimaryKey(Long id) {
		return customerConsumeMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(CustomerConsume record) {
		return customerConsumeMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public Long selectPageTotalCount(CustomerConsume record) {
		return customerConsumeMapper.selectPageTotalCount(record);
	}

	@Override
	public List<CustomerConsume> selectPageByObjectForList(CustomerConsume record) {
		return customerConsumeMapper.selectPageByObjectForList(record);
	}

	@Override
	public void updateByOrderNo(CustomerConsume consume) {
		customerConsumeMapper.updateByOrderNo(consume);
	}

	@Override
	public List<CustomerConsume> selectByObjectForList(CustomerConsume queryParams) {
		return customerConsumeMapper.selectByObjectForList(queryParams);
	}

}