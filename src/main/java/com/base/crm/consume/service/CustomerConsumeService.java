package com.base.crm.consume.service;

import java.util.List;

import com.base.crm.consume.entity.CustomerConsume;
public interface CustomerConsumeService {
    int deleteByPrimaryKey(Long id);

    int insertSelective(CustomerConsume record);

    CustomerConsume selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CustomerConsume record);

	Long selectPageTotalCount(CustomerConsume record);

	List<CustomerConsume> selectPageByObjectForList(CustomerConsume record);

	void updateByOrderNo(CustomerConsume consume);

	List<CustomerConsume> selectByObjectForList(CustomerConsume queryParams);
}