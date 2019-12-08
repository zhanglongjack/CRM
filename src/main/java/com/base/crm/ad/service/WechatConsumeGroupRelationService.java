package com.base.crm.ad.service;

import java.util.List;

import com.base.crm.ad.entity.WechatConsumeGroupRelation;

public interface WechatConsumeGroupRelationService {
    int deleteByPrimaryKey(WechatConsumeGroupRelation record);

    int insertSelective(WechatConsumeGroupRelation record);

    WechatConsumeGroupRelation selectByPrimaryKey(WechatConsumeGroupRelation record);

    int updateByPrimaryKeySelective(WechatConsumeGroupRelation record);

	Long selectPageTotalCount(WechatConsumeGroupRelation queryObject);

	List<WechatConsumeGroupRelation> selectPageByObjectForList(WechatConsumeGroupRelation queryObject);
}