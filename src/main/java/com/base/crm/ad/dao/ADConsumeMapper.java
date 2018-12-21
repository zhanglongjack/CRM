package com.base.crm.ad.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.base.crm.ad.entity.ADConsume;

@Mapper
public interface ADConsumeMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(ADConsume record);

    ADConsume selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ADConsume record);

	Long selectPageTotalCount(ADConsume queryObject);

	List<ADConsume> selectPageByObjectForList(ADConsume queryObject);
}