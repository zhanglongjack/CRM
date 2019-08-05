package com.base.crm.host.config.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.crm.host.config.dao.HostConfigMapper;
import com.base.crm.host.config.entity.HostConfig;
import com.base.crm.host.config.service.HostConfigService;

@Service
public class HostConfigServiceImpl implements HostConfigService {

	@Autowired
	private HostConfigMapper hostConfigMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return hostConfigMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(HostConfig record) {
		return hostConfigMapper.insertSelective(record);
	}

	@Override
	public HostConfig selectByPrimaryKey(Long id) {
		return hostConfigMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(HostConfig record) {
		return hostConfigMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(HostConfig record) {
		return hostConfigMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<HostConfig> selectBySelective(HostConfig hostConfig) {
		return hostConfigMapper.selectBySelective(hostConfig);
	}

	@Override
	public Long selectPageTotalCount(HostConfig queryObject) {
		return hostConfigMapper.selectPageTotalCount(queryObject);
	}

	@Override
	public List<HostConfig> selectPageByObjectForList(HostConfig queryObject) {
		return hostConfigMapper.selectPageByObjectForList(queryObject);
	}
 
}