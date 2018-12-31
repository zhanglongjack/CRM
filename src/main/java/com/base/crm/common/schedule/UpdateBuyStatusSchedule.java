package com.base.crm.common.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.base.crm.orders.service.CustOrderService;

@Component
public class UpdateBuyStatusSchedule {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private CustOrderService custOrderService;
	@Scheduled(cron="0 0 1 * * ?")
    public void transferBackJob() {
		logger.info("开始更新客户购买状态以及订单订复购状态");
		try {
			custOrderService.doUpdateBuyStatus();
		} catch (Exception e) {
			logger.error("更新客户购买状态以及订单订复购状态异常",e);
		} 
		logger.info("结束更新客户购买状态以及订单订复购状态");
	}
}
