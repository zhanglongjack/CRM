package com.base.crm.host.status.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.crm.host.status.dao.HostStatusMapper;
import com.base.crm.host.status.entity.HostStatus;
import com.base.crm.host.status.service.HostStatusService;

@Service
public class HostStatusServiceImpl implements HostStatusService {

	@Autowired
	private HostStatusMapper hostStatusMapper;
	
	@Override
	public int deleteByPrimaryKey(Long id) {
		return hostStatusMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(HostStatus record) {
		return hostStatusMapper.insertSelective(record);
	}

	@Override
	public HostStatus selectByPrimaryKey(Long id) {
		return hostStatusMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(HostStatus record) {
		return hostStatusMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<HostStatus> selectBySelective(HostStatus record) {
		return hostStatusMapper.selectBySelective(record);
	}

	@Override
	public Long selectPageTotalCount(HostStatus queryObject) {
		return hostStatusMapper.selectPageTotalCount(queryObject);
	}

	@Override
	public List<HostStatus> selectPageByObjectForList(HostStatus queryObject) {
		return hostStatusMapper.selectPageByObjectForList(queryObject);
	} 
}