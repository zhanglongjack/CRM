package com.base.crm.users.service;

import com.base.crm.users.entity.UserInfo;

public interface UserService {

	int deleteByPrimaryKey(Long uId);

	int insert(UserInfo record);

	int insertSelective(UserInfo record);

	UserInfo selectByPrimaryKey(Long uId);

	int updateByPrimaryKeySelective(UserInfo record);

	int updateByPrimaryKey(UserInfo record);
	
	UserInfo selectByUserPhone(Long phone);
}