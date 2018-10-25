package com.base.crm.serve.wechat.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.base.crm.serve.wechat.constants.ServeWechatContainer;
import com.base.crm.serve.wechat.dao.ServeWechatMapper;
import com.base.crm.serve.wechat.entity.ServeWechat;
import com.base.crm.serve.wechat.service.ServeWechatService;

@Component
public class ServeWechatServiceImpl implements ServeWechatService {

	@Autowired
	private ServeWechatMapper serveWechatMapper;
	@Autowired
	private ServeWechatContainer serveWechatContainer;
	
	@Override
	public ServeWechat selectByPrimaryKey(Long id) {
		return serveWechatMapper.selectByPrimaryKey(id);
	}

	@Override
	public Long selectPageTotalCount(ServeWechat serveWechat) {
		return serveWechatMapper.selectPageTotalCount(serveWechat);
	}

	@Override
	public List<ServeWechat> selectPageByObjectForList(ServeWechat serveWechat) {
		return serveWechatMapper.selectPageByObjectForList(serveWechat);
	}

	@Override
	public int updateByPrimaryKeySelective(ServeWechat serveWechat) {
		int count = serveWechatMapper.updateByPrimaryKeySelective(serveWechat);
		serveWechatContainer.buildserveWechatInfo();
		return count;
	}

	@Override
	public int insertSelective(ServeWechat serveWechat) {
		int count =  serveWechatMapper.insertSelective(serveWechat);
		serveWechatContainer.buildserveWechatInfo();
		return count;
	}

	@Override
	public List<ServeWechat> selectAllForMap() {
		return serveWechatMapper.selectAll();
	}

	@Override
	public ServeWechat selectByPrimaryWechatNo(String wechatNo) {
		return serveWechatMapper.selectByPrimaryWechatNo(wechatNo);
	}


 

}