package com.base.crm.sign.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.base.crm.sign.dao.UserSignMapper;
import com.base.crm.sign.entity.UserSign;
import com.base.crm.sign.service.UserSignService;

@Component
public class UserSignServiceImpl implements UserSignService {

	@Autowired
	private UserSignMapper userSignMapper;

	@Override
	public int deleteByPrimaryKey(Long id) {
		return userSignMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(UserSign record) {
		return userSignMapper.insertSelective(record);
	}

	@Override
	public UserSign selectByPrimaryKey(Long id) {
		return userSignMapper.selectSimpleObjectBy( new UserSign(id));
	}

	@Override
	public int updateByPrimaryKeySelective(UserSign record) {
		return userSignMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public Long selectPageTotalCount(UserSign userSign) {
		return userSignMapper.selectPageTotalCount(userSign);
	}

	@Override
	public List<UserSign> selectPageByObjectForList(UserSign userSign) {
		return userSignMapper.selectPageByObjectForList(userSign);
	}

	@Override
	public UserSign selectSimpleObjectBy(UserSign us) {
		return userSignMapper.selectSimpleObjectBy(us);
	}
 

}