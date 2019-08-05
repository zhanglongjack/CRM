package com.base.crm.host.config.service;

import java.util.List;

import com.base.crm.host.config.entity.HostConfig;

public interface HostConfigService {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(HostConfig record);

    HostConfig selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HostConfig record);

    int updateByPrimaryKey(HostConfig record);

	List<HostConfig> selectBySelective(HostConfig hostConfig);

	Long selectPageTotalCount(HostConfig queryObject);

	List<HostConfig> selectPageByObjectForList(HostConfig queryObject);
}