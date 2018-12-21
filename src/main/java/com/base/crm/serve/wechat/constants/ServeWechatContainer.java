package com.base.crm.serve.wechat.constants;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.base.crm.serve.wechat.entity.ServeWechat;
import com.base.crm.serve.wechat.service.ServeWechatService;

@Component
public class ServeWechatContainer  implements ApplicationListener<ContextRefreshedEvent> {
	private static final Logger logger = LoggerFactory.getLogger(ServeWechatContainer.class);
	public Map<String,String> serveWechatMap = new HashMap<String,String>();
	public Map<Long,Map<Long,String>> userServeWechatMap = new HashMap<Long,Map<Long,String>>();
	@Autowired
	private ServeWechatService serveWechatService;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if(userServeWechatMap.size()==0){
			buildserveWechatInfo();
		}
	}

	public void buildserveWechatInfo() {
		List<ServeWechat> serveWechatList = serveWechatService.selectAllForMap();
		userServeWechatMap.clear();
		logger.debug("serveWechatList:{}",serveWechatList);
		for(ServeWechat serveWechat : serveWechatList){
			
			serveWechatMap.put(serveWechat.getServeWechatNo(), serveWechat.getServeWechatNo());
			if(userServeWechatMap.containsKey(serveWechat.getUserId())){
				userServeWechatMap.get(serveWechat.getUserId()).put(serveWechat.getId(), serveWechat.getServeWechatNo());
			}else{
				Map<Long,String> groupMap = new HashMap<Long,String>(); 
				groupMap.put(serveWechat.getId(), serveWechat.getServeWechatNo());
				userServeWechatMap.put(serveWechat.getUserId(), groupMap);
			}
		}
		 
		logger.debug("serveWechatMap:{}",serveWechatMap);
		logger.debug("userServeWechatMap:{}",userServeWechatMap);
	}
	
	public String get(String key){
		return serveWechatMap.get(key);
	}
	
	public String get(Long userId , Long key){
		return userServeWechatMap.get(userId).get(key);
	}
}
