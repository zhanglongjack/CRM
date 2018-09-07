package com.base.crm.users.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.crm.users.dao.UserInfoMapper;
import com.base.crm.users.entity.UserInfo;
import com.base.crm.users.service.UserService;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserInfoMapper userInfoMapper;

	@Override
	public int deleteByPrimaryKey(Long uId) {
		return userInfoMapper.deleteByPrimaryKey(uId);
	}

	@Override
	public int insert(UserInfo record) {
		return userInfoMapper.insert(record);
	}

	@Override
	public int insertSelective(UserInfo record) {
		return userInfoMapper.insertSelective(record);
	}

	@Override
	public UserInfo selectByPrimaryKey(Long uId) {
		return userInfoMapper.selectByPrimaryKey(uId);
	}

	@Override
	public int updateByPrimaryKeySelective(UserInfo record) {
		return userInfoMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(UserInfo record) {
		return userInfoMapper.updateByPrimaryKey(record);
	}

	@Override
	public UserInfo selectByUserPhone(Long phone) {
		return userInfoMapper.selectByUserPhone(phone);
	}
    


}