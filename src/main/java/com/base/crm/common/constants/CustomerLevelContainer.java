package com.base.crm.common.constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.base.crm.level.entity.Level;
import com.base.crm.level.service.LevelService;

@Component
public class CustomerLevelContainer implements ApplicationListener<ContextRefreshedEvent> {
	private static final Logger logger = LoggerFactory.getLogger(CustomerLevelContainer.class);
	public static Map<Integer,String> levelMap = new HashMap<Integer,String>();
	public static List<Level> levelList = new ArrayList<Level>();
	
	@Autowired
	private LevelService levelService;
	
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if(levelMap.size()==0){
			levelList = levelService.selectAllForMap();
			logger.debug("levelList:{}",levelList);
			for(Level level : levelList){
				levelMap.put(level.getlId(), level.getlName());
			}
			
			logger.debug("levelMap:{}",levelMap);
		}
		
	}

}
