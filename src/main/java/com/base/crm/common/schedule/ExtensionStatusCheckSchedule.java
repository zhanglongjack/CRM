package com.base.crm.common.schedule;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.base.common.util.DateUtils;
import com.base.common.util.HttpClientUtils;
import com.base.crm.common.constants.CommonConstants;
import com.base.crm.host.status.entity.HostStatus;
import com.base.crm.host.status.service.HostStatusService;
import com.base.crm.website.entity.WebsiteStatusCheckLog;
import com.base.crm.website.service.WebsiteStatusCheckLogService;

@Component
public class ExtensionStatusCheckSchedule {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private CommonConstants commonConstants;
	@Autowired
	private HostStatusService hostStatusService;
	@Autowired
	private WebsiteStatusCheckLogService websiteStatusCheckLogService;
	
//	@Scheduled(cron="0 0/1 * * * ?")
	@Scheduled(cron="0 0 0/1 * * ?")
    public void transferBackJob() {
		logger.info("开始检查网站是否正常");
		List<HostStatus> statusList = hostStatusService.selectBySelective(null);
		for(HostStatus status : statusList){
			if("1".equals(status.getStatus())){
		        String result = HttpClientUtils.doGet(status.getHostName());
		        boolean isOk = result.contains(status.getWechatNo());
		        logger.info("推广站检查,服务号[{}],推广站[{}],是否正常:{}",status.getWechatNo(),status.getHostName(),isOk);
		        WebsiteStatusCheckLog info = new WebsiteStatusCheckLog();
	        	info.setWechatNo(status.getWechatNo());
	        	info.setCheckUrl(status.getHostName());
	        	info.setCreateDate(DateUtils.dateToTightStr(new Date()));
	        	info.setCreateTime(DateUtils.getStringDate());
		        info.setStatus(!isOk?"1":"0"); 
		        
		        websiteStatusCheckLogService.insertSelective(info);
			}
		}
		// 每隔一小时更新一下缓存?
		commonConstants.init();
		logger.info("结束检查网站是否正常");
	}
}
