package com.base.crm.host.status.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.base.crm.host.status.entity.HostStatus;

@Mapper
public interface HostStatusMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(HostStatus record);

    HostStatus selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HostStatus record);
    
	List<HostStatus> selectBySelective(HostStatus record);

	Long selectPageTotalCount(HostStatus queryObject);

	List<HostStatus> selectPageByObjectForList(HostStatus queryObject);
}