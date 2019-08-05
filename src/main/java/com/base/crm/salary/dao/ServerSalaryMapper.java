package com.base.crm.salary.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.base.crm.salary.entity.ServerSalary;

@Mapper
public interface ServerSalaryMapper {

	int deleteByPrimaryKey(Long id);

    int insertSelective(ServerSalary record);

    ServerSalary selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ServerSalary record);

	Long selectPageTotalCount(ServerSalary queryObject);

	List<ServerSalary> selectPageByObjectForList(ServerSalary queryObject);

	BigDecimal querySumAmountByMonth(String month);

}