package com.base.crm.depository.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.crm.depository.dao.DepositoryMapper;
import com.base.crm.depository.entity.Depository;
import com.base.crm.depository.service.DepositoryService;
@Service
public class DepositoryServiceImpl implements DepositoryService {
	
	@Autowired
	private DepositoryMapper depositoryMapper;
	
	@Override
	public int deleteByPrimaryKey(Long depositoryId) {
		return depositoryMapper.deleteByPrimaryKey(depositoryId);
	}

	@Override
	public int insertSelective(Depository record) {
		return depositoryMapper.insertSelective(record);
	}

	@Override
	public Depository selectByPrimaryKey(Long depositoryId) {
		return depositoryMapper.selectByPrimaryKey(depositoryId);
	}

	@Override
	public int updateByPrimaryKeySelective(Depository record) {
		return depositoryMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public Long selectPageTotalCount(Depository record) {
		return depositoryMapper.selectPageTotalCount(record);
	}

	@Override
	public List<Depository> selectPageByObjectForList(Depository record) {
		return depositoryMapper.selectPageByObjectForList(record);
	} 
	
	
}