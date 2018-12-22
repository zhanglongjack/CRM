package com.base.crm.salary.service;

import java.util.List;

import com.base.crm.salary.entity.ServerSalary;

public interface ServerSalaryService {
    int deleteByPrimaryKey(Long id);

    int insertSelective(ServerSalary record);

    ServerSalary selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ServerSalary record);
    
	Long selectPageTotalCount(ServerSalary queryObject);

	List<ServerSalary> selectPageByObjectForList(ServerSalary queryObject);
}