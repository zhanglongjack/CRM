package com.base.crm.report.excel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.base.common.util.ExcelMappingsAbstract;
import com.base.crm.ad.entity.ADConsume;
import com.base.crm.ad.service.ADConsumeService;
import com.base.crm.customer.service.CustInfoService;
import com.base.crm.procurement.service.ProcurementCostService;
import com.base.crm.report.entity.ServerSaleReport;
import com.base.crm.serve.wechat.entity.ServeWechat;
import com.base.crm.serve.wechat.service.ServeWechatService;
import com.base.crm.users.constants.UserContainer;

@Component
public class ServerSaleMonthReportExcelMappings extends ExcelMappingsAbstract {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private static String sheetName ="客服销售业绩";
	
	private static List<String> headerList = Arrays.asList( 
			"月份","客服名称","服务号","加粉总数","订购数","订购率","复购人数","复购率"
			,"加粉单价","订购广告单价","销售业绩");
	
	public static List<String> columnsMappings = Arrays.asList(
			"month","name","wechatNumber","custAddSumNum","orderNumber","orderRate","reorderNumber","reorderRate"
			,"addCustPrice","orderAdPrice","salePerforman");
	
	public void setExcelMappings(List<ServerSaleReport> data,ServerSaleReport summeryData) {
		this.data = JSON.parseArray(JSON.toJSONString(data));
		this.collectData = JSON.parseObject(JSON.toJSONString(summeryData));
		this.setSheetName(sheetName);
	}

	@Autowired
	private ProcurementCostService procurementCostService;
	@Autowired
	private ADConsumeService consumeADService;
//	@Autowired
//	private CustOrderService custOrderService;
//	@Autowired
//	private ServerSalaryService serverSalaryService;
	@Autowired
	private ServeWechatService serveWechatService;
	@Autowired
	private CustInfoService custInfoService;
	@Autowired
	private UserContainer userContainer;
//	@Autowired
//	private CommonConstants commonConstants;
	
	@Override
	public List<String> getHeaderList() {
		return headerList;
	}

	@Override
	public List<String> getColumnsMappings() {
		return columnsMappings;
	}

	private List<ServerSaleReport> reportResult;
	
	public List<ServerSaleReport> getReportResult() {
		return reportResult;
	}

	public void setReportResult(List<ServerSaleReport> reportResult) {
		this.reportResult = reportResult;
	}
	
	public ExcelMappingsAbstract statistics(ServerSaleReport serverSaleReport) {
		logger.info("开始统计"+sheetName+"报表新 ServerSaleReport ==== "+serverSaleReport);
		reportResult = new ArrayList<ServerSaleReport>();
		
		List<String> monthList = procurementCostService.queryMonthBy(serverSaleReport.getMonth());
		BigDecimal zero = new BigDecimal(0);
		
		ServerSaleReport sumReportResult = new ServerSaleReport();
		sumReportResult.setMonth("总计");
		
		for(String month : monthList){
			List<ADConsume> realConsumeADList = consumeADService.queryRealConsumeAmount(month);
			ADConsume monthConsume = new ADConsume();
			monthConsume.setConsumeAmount(zero);
			monthConsume.setRealAmount(zero);
			monthConsume.setConsumeDate(month);
			
			ServerSaleReport monthReport = new ServerSaleReport();
			monthReport.setMonth("小计");
			
			
			for(ADConsume consume : realConsumeADList){
				monthConsume.setConsumeAmount(monthConsume.getConsumeAmount().add(consume.getConsumeAmount()));
				monthConsume.setRealAmount(monthConsume.getRealAmount().add(consume.getRealAmount()));
				
				Map<String,Object> map = custInfoService.queryAddCustCountBy(month,consume.getConsumeWechatNo(),consume.getServerId());
				BigDecimal salePerforman= custInfoService.queryServerSalePerformanBy(month,consume.getConsumeWechatNo(),consume.getServerId());
				
				BigDecimal addSum = new BigDecimal(map.get("num")+"");
				BigDecimal orderedNum= new BigDecimal(map.get("ordered")+"");
				BigDecimal reorderedNum = new BigDecimal(map.get("reordered")+"");
				
				ServerSaleReport serverSaleReportResult = new ServerSaleReport();
				serverSaleReportResult.setMonth(month);
				serverSaleReportResult.setName(userContainer.get(consume.getServerId()));
				serverSaleReportResult.setWechatNumber(consume.getConsumeWechatNo());
				serverSaleReportResult.setCustAddSumNum(addSum);// 加粉总数
				serverSaleReportResult.setOrderNumber(orderedNum); // 订购总数
				serverSaleReportResult.setReorderNumber(reorderedNum);// 复购总数
				serverSaleReportResult.setAdConsumeSum(consume.getRealAmount());
				serverSaleReportResult.setSalePerforman(salePerforman);
				
				BigDecimal orderedRate = divide(serverSaleReportResult.getOrderNumber(), serverSaleReportResult.getCustAddSumNum());
				serverSaleReportResult.setOrderRate(orderedRate.doubleValue());
				
				BigDecimal reorderedRate = divide(serverSaleReportResult.getReorderNumber(), serverSaleReportResult.getOrderNumber());
				serverSaleReportResult.setReorderRate(reorderedRate.doubleValue()); 
				
				serverSaleReportResult.setAddCustPrice(divide(consume.getRealAmount(),addSum).doubleValue());
				serverSaleReportResult.setOrderAdPrice(divide(consume.getRealAmount(),orderedNum).doubleValue());
				reportResult.add(serverSaleReportResult);
				
				monthReport.setAdConsumeSum(monthReport.getAdConsumeSum().add(consume.getRealAmount()));
				monthReport.setCustAddSumNum(monthReport.getCustAddSumNum().add(addSum));
				monthReport.setOrderNumber(monthReport.getOrderNumber().add(orderedNum));
				monthReport.setReorderNumber(monthReport.getReorderNumber().add(reorderedNum));
				
				BigDecimal sumOrderedRate = divide(monthReport.getOrderNumber(), monthReport.getCustAddSumNum());
				monthReport.setOrderRate(sumOrderedRate.doubleValue());
				
				BigDecimal sumReorderedRate = divide(monthReport.getReorderNumber(), monthReport.getOrderNumber());
				monthReport.setReorderRate(sumReorderedRate.doubleValue());
				monthReport.setSalePerforman(monthReport.getSalePerforman().add(salePerforman));
			}
			
			monthReport.setAddCustPrice(divide(monthReport.getAdConsumeSum(), monthReport.getCustAddSumNum()).doubleValue());
			monthReport.setOrderAdPrice(divide(monthReport.getAdConsumeSum(), monthReport.getOrderNumber()).doubleValue());
			
			sumReportResult.setCustAddSumNum(sumReportResult.getCustAddSumNum().add(monthReport.getCustAddSumNum()));
			sumReportResult.setOrderNumber(sumReportResult.getOrderNumber().add(monthReport.getOrderNumber()));
			sumReportResult.setReorderNumber(sumReportResult.getReorderNumber().add(monthReport.getReorderNumber()));
			sumReportResult.setAdConsumeSum(monthReport.getAdConsumeSum().add(sumReportResult.getAdConsumeSum()));
			
			BigDecimal orderRate = divide(sumReportResult.getOrderNumber(), sumReportResult.getCustAddSumNum());
			sumReportResult.setOrderRate(orderRate.doubleValue());
			
			BigDecimal reorderRate = divide(sumReportResult.getReorderNumber(), sumReportResult.getOrderNumber());
			sumReportResult.setReorderRate(reorderRate.doubleValue());

			sumReportResult.setSalePerforman(sumReportResult.getSalePerforman().add(monthReport.getSalePerforman()));
			
			reportResult.add(monthReport);
		}
		
		sumReportResult.setAddCustPrice(divide(sumReportResult.getAdConsumeSum(), sumReportResult.getCustAddSumNum()).doubleValue());
		sumReportResult.setOrderAdPrice(divide(sumReportResult.getAdConsumeSum(), sumReportResult.getOrderNumber()).doubleValue());
		
		reportResult.add(sumReportResult);
		setExcelMappings(reportResult, sumReportResult);
		logger.info("结束统计"+sheetName+"报表新 ServerSaleReport ==== "+reportResult);
		return this;
	}
	
	private BigDecimal divide(BigDecimal first,BigDecimal second){
		BigDecimal result = new BigDecimal(0);
		if(first.doubleValue()>0 && second.doubleValue()>0){
			result = first.divide(second,4,BigDecimal.ROUND_HALF_EVEN);
		}
		return result;
	}

}