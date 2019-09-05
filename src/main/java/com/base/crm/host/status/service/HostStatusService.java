package com.base.crm.host.status.service;

import java.util.List;

import com.base.crm.host.status.entity.HostStatus;

public interface HostStatusService {
    int deleteByPrimaryKey(Long id);

    int insertSelective(HostStatus record);

    HostStatus selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HostStatus record);
    
	List<HostStatus> selectBySelective(HostStatus record);

	Long selectPageTotalCount(HostStatus queryObject);

	List<HostStatus> selectPageByObjectForList(HostStatus queryObject);
}