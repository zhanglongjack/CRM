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
import com.base.crm.ad.service.ADConsumeService;
import com.base.crm.customer.service.CustInfoService;
import com.base.crm.procurement.service.ProcurementCostService;
import com.base.crm.report.entity.ServerSaleReport;
import com.base.crm.serve.wechat.entity.ServeWechat;
import com.base.crm.serve.wechat.service.ServeWechatService;
import com.base.crm.users.constants.UserContainer;

@Component
public class ServerSaleReportExcelMappings extends ExcelMappingsAbstract {
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
		logger.info("开始统计"+sheetName+"报表 ServerSaleReport ==== "+serverSaleReport);
		List<String> monthList = procurementCostService.queryMonthBy(serverSaleReport.getMonth());
		
		ServeWechat sw = new ServeWechat();
		sw.setUserId(serverSaleReport.getUserId());
		List<ServeWechat> serverWechatList = serveWechatService.selectPageByObjectForList(sw);
//		Map<String,ServerSaleReport> sumMap = new HashMap<String,ServerSaleReport>();
		reportResult = new ArrayList<ServerSaleReport>();
		ServerSaleReport sumReportResult = new ServerSaleReport();
		sumReportResult.setMonth("总计");
		for(String month : monthList){
			Map<String,BigDecimal> realConsumeADMap = consumeADService.queryRealConsumeAd(month);
			ServerSaleReport sumServerSaleReportResult = new ServerSaleReport();
			sumServerSaleReportResult.setMonth("小计");
			BigDecimal value = new BigDecimal(0);
			
			for(ServeWechat serverWechat : serverWechatList){
				
				List<Map<String,Object>> custCountList = custInfoService.queryAddCustCountBy(month,serverWechat.getServeWechatNo());
				BigDecimal salePerforman= custInfoService.queryServerSalePerformanBy(month,serverWechat.getServeWechatNo());
				BigDecimal sumConsumeAmount = realConsumeADMap.get(serverWechat.getConsumeAcctType());
				if(sumConsumeAmount == null){
					sumConsumeAmount = new BigDecimal(0);
				}
				sumServerSaleReportResult.setAdConsumeSum(sumServerSaleReportResult.getAdConsumeSum().add(sumConsumeAmount));
				sumReportResult.setAdConsumeSum(sumReportResult.getAdConsumeSum().add(sumConsumeAmount));
				
				for(Map<String,Object> map : custCountList){
					
					BigDecimal addSum = new BigDecimal(map.get("num")+"");
					BigDecimal orderedNum= new BigDecimal(map.get("ordered")+"");
					
					ServerSaleReport serverSaleReportResult = new ServerSaleReport();
					serverSaleReportResult.setMonth(month);
					serverSaleReportResult.setName(userContainer.get(serverWechat.getUserId()));
					serverSaleReportResult.setWechatNumber(serverWechat.getServeWechatNo());
					serverSaleReportResult.setCustAddSumNum(addSum);// 加粉总数
					serverSaleReportResult.setOrderNumber(orderedNum); // 订购总数
					serverSaleReportResult.setReorderNumber(new BigDecimal(map.get("reordered")+""));// 复购总数
					serverSaleReportResult.setAdConsumeSum(sumConsumeAmount);
					serverSaleReportResult.setSalePerforman(salePerforman);
					
					value = divide(serverSaleReportResult.getOrderNumber(), serverSaleReportResult.getCustAddSumNum());
					serverSaleReportResult.setOrderRate(value.doubleValue());
					
					value = divide(serverSaleReportResult.getReorderNumber(), serverSaleReportResult.getOrderNumber());
					serverSaleReportResult.setReorderRate(value.doubleValue()); 
					
					serverSaleReportResult.setAddCustPrice(divide(sumConsumeAmount,addSum).doubleValue());
					serverSaleReportResult.setOrderAdPrice(divide(sumConsumeAmount,orderedNum).doubleValue());
					
					reportResult.add(serverSaleReportResult);
					
					sumServerSaleReportResult.setCustAddSumNum(sumServerSaleReportResult.getCustAddSumNum().add(addSum));
					sumServerSaleReportResult.setOrderNumber(sumServerSaleReportResult.getOrderNumber().add(orderedNum));
					sumServerSaleReportResult.setReorderNumber(sumServerSaleReportResult.getReorderNumber().add(serverSaleReportResult.getReorderNumber()));
					
					value = divide(sumServerSaleReportResult.getOrderNumber(), sumServerSaleReportResult.getCustAddSumNum());
					sumServerSaleReportResult.setOrderRate(value.doubleValue());
					
					value = divide(sumServerSaleReportResult.getReorderNumber(), sumServerSaleReportResult.getOrderNumber());
					sumServerSaleReportResult.setReorderRate(value.doubleValue());
					

					sumReportResult.setCustAddSumNum(sumReportResult.getCustAddSumNum().add(addSum));
					sumReportResult.setOrderNumber(sumReportResult.getOrderNumber().add(orderedNum));
					sumReportResult.setReorderNumber(sumReportResult.getReorderNumber().add(serverSaleReportResult.getReorderNumber()));

				}
				sumServerSaleReportResult.setAddCustPrice(divide(sumServerSaleReportResult.getAdConsumeSum(), sumServerSaleReportResult.getCustAddSumNum()).doubleValue());
				sumServerSaleReportResult.setOrderAdPrice(divide(sumServerSaleReportResult.getAdConsumeSum(), sumServerSaleReportResult.getOrderNumber()).doubleValue());
				sumServerSaleReportResult.setSalePerforman(sumServerSaleReportResult.getSalePerforman().add(salePerforman));
				
				value = divide(sumReportResult.getOrderNumber(), sumReportResult.getCustAddSumNum());
				sumReportResult.setOrderRate(value.doubleValue());
				
				value = divide(sumReportResult.getReorderNumber(), sumReportResult.getOrderNumber());
				sumReportResult.setReorderRate(value.doubleValue());
				
				sumReportResult.setAddCustPrice(divide(sumReportResult.getAdConsumeSum(), sumReportResult.getCustAddSumNum()).doubleValue());
				sumReportResult.setOrderAdPrice(divide(sumReportResult.getAdConsumeSum(), sumReportResult.getOrderNumber()).doubleValue());
				sumReportResult.setSalePerforman(sumReportResult.getSalePerforman().add(salePerforman));
				
				
			}
			
			reportResult.add(sumServerSaleReportResult);
		}
		
		reportResult.add(sumReportResult);
		setExcelMappings(reportResult, sumReportResult);
		logger.info("结束统计"+sheetName+"报表 ServerSaleReport ==== "+reportResult);
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