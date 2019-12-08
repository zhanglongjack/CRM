package com.base.crm.ad.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.crm.ad.dao.WechatConsumeGroupRelationMapper;
import com.base.crm.ad.entity.WechatConsumeGroupRelation;
import com.base.crm.ad.service.WechatConsumeGroupRelationService;

@Service
public class WechatConsumeGroupRelationServiceImpl  implements WechatConsumeGroupRelationService {
	
	@Autowired
	private WechatConsumeGroupRelationMapper wechatConsumeGroupRelationMapper;

	@Override
	public int deleteByPrimaryKey(WechatConsumeGroupRelation record) {
		return wechatConsumeGroupRelationMapper.deleteByPrimaryKey(record);
	}

	@Override
	public int insertSelective(WechatConsumeGroupRelation record) {
		return wechatConsumeGroupRelationMapper.insertSelective(record);
	}

	@Override
	public WechatConsumeGroupRelation selectByPrimaryKey(WechatConsumeGroupRelation record) {
		return wechatConsumeGroupRelationMapper.selectByPrimaryKey(record);
	}

	@Override
	public int updateByPrimaryKeySelective(WechatConsumeGroupRelation record) {
		return wechatConsumeGroupRelationMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public Long selectPageTotalCount(WechatConsumeGroupRelation queryObject) {
		return wechatConsumeGroupRelationMapper.selectPageTotalCount(queryObject);
	}

	@Override
	public List<WechatConsumeGroupRelation> selectPageByObjectForList(WechatConsumeGroupRelation queryObject) {
		return wechatConsumeGroupRelationMapper.selectPageByObjectForList(queryObject);
	}

 

}