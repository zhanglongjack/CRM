package com.base.crm.depository.service;

import java.util.List;

import com.base.crm.depository.entity.Depository;

public interface DepositoryService {
	
    int deleteByPrimaryKey(Long depositoryId);

    int insertSelective(Depository record);

    Depository selectByPrimaryKey(Long depositoryId);

    int updateByPrimaryKeySelective(Depository record);

	Long selectPageTotalCount(Depository record);

	List<Depository> selectPageByObjectForList(Depository record);

	List<Depository> selectByObjectForList(Depository object);
}