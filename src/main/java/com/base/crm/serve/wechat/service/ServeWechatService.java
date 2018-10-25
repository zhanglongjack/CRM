package com.base.crm.serve.wechat.service;

import java.util.List;

import com.base.crm.serve.wechat.entity.ServeWechat;

public interface ServeWechatService {

	ServeWechat selectByPrimaryKey(Long id);

	Long selectPageTotalCount(ServeWechat serveWechat);

	List<ServeWechat> selectPageByObjectForList(ServeWechat serveWechat);

	int updateByPrimaryKeySelective(ServeWechat serveWechat);

	int insertSelective(ServeWechat serveWechat);

	List<ServeWechat> selectAllForMap();

	ServeWechat selectByPrimaryWechatNo(String oWechatNo);
 

}