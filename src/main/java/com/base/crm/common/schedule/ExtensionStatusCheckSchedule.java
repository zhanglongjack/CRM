package com.base.crm.common.schedule;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.base.common.util.DateUtils;
import com.base.common.util.HttpClientUtils;
import com.base.crm.common.constants.CommonConstants;
import com.base.crm.extension.check.ExtensionStatusCheckData;
import com.base.crm.extension.check.ExtentensionCheckInfo;

@Component
public class ExtensionStatusCheckSchedule {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ExtensionStatusCheckData data;
	
	@Autowired
	private CommonConstants commonConstants;
	
	@Scheduled(cron="0 0 0/1 * * ?")
    public void transferBackJob() {
		logger.info("开始检查网站是否正常");
//		Map<String,String> map = new HashMap<String,String>();
//		map.put("GD01A01","http://zcs.whbaidianfeng.com/zc/");
//		map.put("GD01A03","http://zc.gzxpyy.com/zc/");
//		map.put("ZCCS01A01","http://tgx.hztjjm.cn/byw26595/");
//		
//		for(String key : map.keySet()){
//	        String result = HttpClientUtils.doGet(map.get(key));
//	        boolean isOk = result.contains(key);
//	        logger.info("推广站检查,服务号[{}],推广站[{}],是否正常:{}",key,map.get(key),isOk);
//	        if(!isOk){
//	        	// 缓存取
//	        	ExtentensionCheckInfo info = new ExtentensionCheckInfo();
//	        	info.setNumber(key);
//	        	info.setNetWork(map.get(key)+"");
//	        	info.setDateTime(DateUtils.getStringDate());
//	        	info.setStatus(1);
//	        	data.addData(info); 
//	        }
//		}
		// 每隔一小时更新一下缓存?
		commonConstants.init();
		logger.info("结束检查网站是否正常");
	}
}
