package com.base.crm.sign.service;

import java.util.List;

import com.base.crm.sign.entity.UserSign;

public interface UserSignService {

    int deleteByPrimaryKey(Long id);

    int insertSelective(UserSign record);

    UserSign selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserSign userSign);
    
	Long selectPageTotalCount(UserSign userSign);

	List<UserSign> selectPageByObjectForList(UserSign userSign);

	UserSign selectSimpleObjectBy(UserSign us);
}