package com.base.crm.host.config.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.base.crm.host.config.entity.HostConfig;

@Mapper
public interface HostConfigMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(HostConfig record);

    HostConfig selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HostConfig record);

    int updateByPrimaryKey(HostConfig record);

	List<HostConfig> selectBySelective(HostConfig hostConfig);

	Long selectPageTotalCount(HostConfig queryObject);

	List<HostConfig> selectPageByObjectForList(HostConfig queryObject);
}