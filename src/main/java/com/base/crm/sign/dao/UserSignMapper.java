package com.base.crm.sign.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.base.crm.sign.entity.UserSign;
@Mapper
public interface UserSignMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(UserSign record);

    int updateByPrimaryKeySelective(UserSign record);
    
	Long selectPageTotalCount(UserSign userSign);

	List<UserSign> selectPageByObjectForList(UserSign userSign);

	UserSign selectSimpleObjectBy(UserSign us);

}