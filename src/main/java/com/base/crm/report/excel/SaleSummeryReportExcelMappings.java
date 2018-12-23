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
import com.base.crm.ad.entity.ADConsume;
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
			"月份","销售总额","实收总额","采购成本","广告消耗","广告实际消耗","快递费总额","毛利润","实际毛利润");
	
	public static List<String> columnsMappings = Arrays.asList(
			"month","saleTotalAmount","realncomeTotalAmount","procurementCosts","consumeAD","realConsumeAD","expressTotalFee","profit","realProfit");
	
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
		BigDecimal zore = new BigDecimal("0.00");
		for(String month : monthList){
			logger.info("开始统计{}月销售业绩 ",month);
//			SummaryReport report = new SummaryReport();
			SummaryReport orderReport = custOrderService.querySumAmountByMonth(month);
			SummaryReport report = orderReport==null?new SummaryReport():orderReport;
			
			List<ADConsume> consumeList = consumeADService.querySummaryConsumeAmount(month);
			BigDecimal procurementAmount = procurementCostService.querySumAmountByMonth(month);
			BigDecimal salaryAmount = serverSalaryService.querySumAmountByMonth(month);
			if(consumeList.size()>0){
				// 各帐号广告费
				for(ADConsume consume : consumeList){
					report.setConsumeAD(report.getConsumeAD().add(consume.getConsumeAmount()));
					if(consume.getConsumeAccountType().equals("normal_account")){
						report.setRealConsumeAD(report.getRealConsumeAD().add(consume.getConsumeAmount().divide(new BigDecimal("1.3"),2,BigDecimal.ROUND_HALF_EVEN)));
					}else{
						report.setRealConsumeAD(report.getRealConsumeAD().add(consume.getConsumeAmount().divide(new BigDecimal("1.25"),2,BigDecimal.ROUND_HALF_EVEN)));
					}
				}
			}
			report.setMonth(month);
			
			report.setProcurementCosts(procurementAmount==null?zore:procurementAmount);
//			report.setSaleTotalAmount(orderAmount==null?zore:orderAmount);
			report.setRealSalary(salaryAmount==null?zore:salaryAmount);
			
			sumReport.setConsumeAD(sumReport.getConsumeAD().add(report.getConsumeAD()));
			sumReport.setRealConsumeAD(sumReport.getRealConsumeAD().add(report.getRealConsumeAD()));
			sumReport.setProcurementCosts(sumReport.getProcurementCosts().add(report.getProcurementCosts()));
			sumReport.setSaleTotalAmount(sumReport.getSaleTotalAmount().add(report.getSaleTotalAmount()));
			sumReport.setRealSalary(sumReport.getRealSalary().add(report.getRealSalary()));
			sumReport.setRealncomeTotalAmount(sumReport.getRealncomeTotalAmount().add(report.getRealncomeTotalAmount()));
			sumReport.setExpressTotalFee(sumReport.getExpressTotalFee().add(report.getExpressTotalFee()));
			
			BigDecimal sum = new BigDecimal("0.00");
			sum = sum.add(report.getRealConsumeAD());
			sum = sum.add(report.getProcurementCosts());
			sum = sum.add(report.getRealSalary());
			sum = sum.add(report.getExpressTotalFee());
			// 实际利润
			report.setRealProfit(report.getRealncomeTotalAmount().subtract(sum));
			// 预计利润
			report.setProfit(report.getSaleTotalAmount().subtract(sum));
			
			// 总计实际利润
			sumReport.setRealProfit(sumReport.getRealProfit().add(report.getRealProfit()));
			// 总计利润
			sumReport.setProfit(sumReport.getProfit().add(report.getProfit()));
			
			reportResult.add(report);
			logger.info("结束统计{}月销售业绩:{} ",month,report);
		}
		reportResult.add(sumReport);
		setExcelMappings(reportResult, sumReport);
		logger.info("结束统计销售报表 SummaryReport ==== "+sumReport);
		return this;
	}

}