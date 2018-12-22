package com.base.crm.salary.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.crm.salary.dao.ServerSalaryMapper;
import com.base.crm.salary.entity.ServerSalary;
import com.base.crm.salary.service.ServerSalaryService;

@Service
public class ServerSalaryServiceImpl implements ServerSalaryService {

	@Autowired
	private ServerSalaryMapper serverSalaryMapper;

	@Override
	public int deleteByPrimaryKey(Long id) {
		return serverSalaryMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(ServerSalary record) {
		return serverSalaryMapper.insertSelective(record);
	}

	@Override
	public ServerSalary selectByPrimaryKey(Long id) {
		return serverSalaryMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(ServerSalary record) {
		return serverSalaryMapper.updateByPrimaryKeySelective(record);
	}
 
	@Override
	public Long selectPageTotalCount(ServerSalary queryObject) {
		return serverSalaryMapper.selectPageTotalCount(queryObject);
	}

	@Override
	public List<ServerSalary> selectPageByObjectForList(ServerSalary queryObject) {
		return serverSalaryMapper.selectPageByObjectForList(queryObject);
	}

	@Override
	public BigDecimal querySumAmountByMonth(String month) {
		return serverSalaryMapper.querySumAmountByMonth(month);
	}

}