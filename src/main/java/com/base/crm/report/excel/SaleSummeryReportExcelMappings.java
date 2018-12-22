package com.base.crm.report.excel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.base.common.util.ExcelMappingsAbstract;
import com.base.crm.ad.service.ADConsumeService;
import com.base.crm.orders.service.CustOrderService;
import com.base.crm.procurement.service.ProcurementCostService;
import com.base.crm.report.entity.SummaryReport;
import com.base.crm.salary.service.ServerSalaryService;

@Component
public class SaleSummeryReportExcelMappings extends ExcelMappingsAbstract {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private static String sheetName ="销售报表汇总";
	
	private static List<String> headerList = Arrays.asList( 
			"月份","销售总额","采购成本","广告实际消耗","实际毛利润");
	
	public static List<String> columnsMappings = Arrays.asList(
			"month"," saleTotalAmount"," procurementCosts"," consumeAD"," realProfit");
	
	public void setExcelMappings(List<SummaryReport> data,SummaryReport summeryData) {
		this.data = JSON.parseArray(JSON.toJSONString(data));
		this.collectData = JSON.parseObject(JSON.toJSONString(summeryData));
		this.setSheetName(sheetName);
	}

	@Autowired
	private ProcurementCostService procurementCostService;
	@Autowired
	private ADConsumeService consumeADService;
	@Autowired
	private CustOrderService custOrderService;
	@Autowired
	private ServerSalaryService serverSalaryService;
	
	@Override
	public List<String> getHeaderList() {
		return headerList;
	}

	@Override
	public List<String> getColumnsMappings() {
		return columnsMappings;
	}

	private List<SummaryReport> reportResult;
	
	public List<SummaryReport> getReportResult() {
		return reportResult;
	}

	public void setReportResult(List<SummaryReport> reportResult) {
		this.reportResult = reportResult;
	}

	public ExcelMappingsAbstract statistics(SummaryReport summaryReport) {
		logger.info("开始统计销售报表 SummaryReport ==== "+summaryReport);
		List<String> monthList = procurementCostService.queryMonthBy(summaryReport.getMonth());
		reportResult = new ArrayList<SummaryReport>();
		SummaryReport sumReport = new SummaryReport();
		sumReport.setMonth("总计");
		for(String month : monthList){
			logger.info("开始统计{}月销售业绩 ",month);
			SummaryReport report = new SummaryReport();
			report.setMonth(month);
			BigDecimal consumeAmount = consumeADService.querySummaryConsumeAmount(month);
			BigDecimal procurementAmount = procurementCostService.querySumAmountByMonth(month);
			BigDecimal orderAmount =custOrderService.querySumAmountByMonth(month);
			BigDecimal salaryAmount = serverSalaryService.querySumAmountByMonth(month);
			BigDecimal zore = new BigDecimal("0.00");
			report.setConsumeAD(consumeAmount==null?zore:consumeAmount);
			report.setProcurementCosts(procurementAmount==null?zore:procurementAmount);
			report.setSaleTotalAmount(orderAmount==null?zore:orderAmount);
			report.setRealSalary(salaryAmount==null?zore:salaryAmount);
			
			sumReport.setConsumeAD(sumReport.getConsumeAD().add(report.getConsumeAD()));
			sumReport.setProcurementCosts(sumReport.getProcurementCosts().add(report.getProcurementCosts()));
			sumReport.setSaleTotalAmount(sumReport.getSaleTotalAmount().add(report.getSaleTotalAmount()));
			sumReport.setRealSalary(sumReport.getRealSalary().add(report.getRealSalary()));
			
			BigDecimal sum = new BigDecimal("0.00");
			sum = sum.add(report.getConsumeAD());
			sum = sum.add(report.getProcurementCosts());
			sum = sum.add(report.getRealSalary());
			report.setRealProfit(sumReport.getSaleTotalAmount().subtract(sum));
			sumReport.setRealProfit(sumReport.getRealProfit().add(report.getRealProfit()));
			
			reportResult.add(report);
			logger.info("结束统计{}月销售业绩:{} ",month,report);
		}
		reportResult.add(sumReport);
		setExcelMappings(reportResult, sumReport);
		logger.info("结束统计销售报表 SummaryReport ==== "+sumReport);
		return this;
	}

}