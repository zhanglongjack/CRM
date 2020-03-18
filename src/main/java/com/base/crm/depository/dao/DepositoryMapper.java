package com.base.crm.depository.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.base.crm.depository.entity.Depository;

@Mapper
public interface DepositoryMapper {
	
    int deleteByPrimaryKey(Long depositoryId);

    int insertSelective(Depository record);

    Depository selectByPrimaryKey(Long depositoryId);

    int updateByPrimaryKeySelective(Depository record);

	Long selectPageTotalCount(Depository record);

	List<Depository> selectPageByObjectForList(Depository record);
}