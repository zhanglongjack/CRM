package com.base.crm.consume.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.base.crm.consume.entity.CustomerConsume;

@Mapper
public interface CustomerConsumeMapper {
    int deleteByPrimaryKey(Long consumeId);

    int insertSelective(CustomerConsume record);

    CustomerConsume selectByPrimaryKey(Long consumeId);

    int updateByPrimaryKeySelective(CustomerConsume record);

	Long selectPageTotalCount(CustomerConsume record);

	List<CustomerConsume> selectPageByObjectForList(CustomerConsume record);

	void updateByOrderNo(CustomerConsume consume);

}