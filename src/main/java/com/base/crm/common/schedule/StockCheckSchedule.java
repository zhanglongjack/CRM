package com.base.crm.common.schedule;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.base.common.email.entity.EmailTemplate;
import com.base.common.email.service.EmailTemplateService;
import com.base.common.email.service.SendMailService;
import com.base.crm.common.constants.CommonConstants;
import com.base.crm.product.entity.ProductStock;
import com.base.crm.product.service.ProductStockService;

@Component
public class StockCheckSchedule {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private CommonConstants commonConstants;
	@Autowired
	private ProductStockService productStockService;
	@Autowired
	private EmailTemplateService emailTemplateService;
	@Autowired
	private SendMailService sendMailService;
	
	@Scheduled(cron="0 0 12 * * ?")
    public void stockCheck() { 
		// 仓库位置:{0},产品名称:{1},库存数量:{2}
		String functionCode = "STOCK_NUM_CHECK_EMAIL";
		EmailTemplate temp = emailTemplateService.selectByFunCode(functionCode);
		String content = "";
		
		List<ProductStock> stockList = productStockService.selectPageByObjectForList(null);
		logger.info("开始检查库存");
		for(ProductStock stock : stockList){
			if(stock.getStockWarning()!=0&&stock.getStockWarning()>=stock.getStockNum()){
				String depository = commonConstants.getDictionarysBy(CommonConstants.DEPOSITORY_DICT, stock.getDepositoryId().toString());
				String productName = commonConstants.getDictionarysBy(CommonConstants.PRODUCT_DICT, stock.getProductId().toString());
				content+=sendMailService.buildContent(temp.getContent(), depository,productName,stock.getStockNum()+"")+"\r\n";
			}
		}
		temp.setContent(content);
		
		if(content.length()>0){
			logger.info("开始发送库存告警邮件:"+temp);
			sendMailService.sendSimpleEmail( temp);
		}
	}
}
