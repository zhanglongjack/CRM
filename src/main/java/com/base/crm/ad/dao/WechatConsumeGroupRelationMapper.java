package com.base.crm.ad.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.base.crm.ad.entity.WechatConsumeGroupRelation;

@Mapper
public interface WechatConsumeGroupRelationMapper {
    int deleteByPrimaryKey(WechatConsumeGroupRelation record);

    int insertSelective(WechatConsumeGroupRelation record);

    WechatConsumeGroupRelation selectByPrimaryKey(WechatConsumeGroupRelation record);

    int updateByPrimaryKeySelective(WechatConsumeGroupRelation record);

	Long selectPageTotalCount(WechatConsumeGroupRelation queryObject);

	List<WechatConsumeGroupRelation> selectPageByObjectForList(WechatConsumeGroupRelation queryObject);

	List<String> queryJSWechatNo(String acctId);
	
}