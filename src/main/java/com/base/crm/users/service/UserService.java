package com.base.crm.users.service;

import java.util.List;

import com.base.crm.users.entity.UserInfo;

public interface UserService {

	int deleteByPrimaryKey(Long uId);

	int insertSelective(UserInfo record);

	UserInfo selectByPrimaryKey(Long uId);

	int updateByPrimaryKeySelective(UserInfo record);

	UserInfo selectByUserPhone(Long phone);

	Long selectPageTotalCount(UserInfo userInfo);

	List<UserInfo> selectPageByObjectForList(UserInfo userInfo);

	List<UserInfo> selectAllForMap();

}