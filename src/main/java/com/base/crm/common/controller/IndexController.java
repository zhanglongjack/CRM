package com.base.crm.common.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.base.common.util.DateUtils;
import com.base.common.util.PageTools;
import com.base.crm.ad.service.ConsumeAcctGroupService;
import com.base.crm.orders.entity.CustOrder;
import com.base.crm.orders.service.CustOrderService;
import com.base.crm.report.entity.ConsumeAcctGroupReport;
import com.base.crm.report.entity.SummaryReport;
import com.base.crm.users.entity.UserInfo;

@Controller
@SessionAttributes("user")
public class IndexController {
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
//
//	@Autowired
//	private CustInfoService custInfoService;
	@Autowired
	private CustOrderService orderService;
//	@Autowired
//	private WebsiteStatusCheckLogService websiteStatusCheckLogService;
	@Autowired
	private ConsumeAcctGroupService consumeAcctGroupService;

	@RequestMapping(value = "/index")
	public ModelAndView index(@ModelAttribute("user") UserInfo user) {
		logger.info("index request");
		Long userId = user.isAdmin() ? null : user.getuId();
		List<SummaryReport> kpiList = null;
		List<SummaryReport> kpiMonthList = null;
		ModelAndView mv = new ModelAndView("page/index");
		mv.addObject("date", new Date());
		orderReport(mv, userId); // 单日/月表查询处理
		
		CustOrder queryOrderParams = new CustOrder();
		queryOrderParams.setPageTools(new PageTools(1, 15));
		kpiMonthList = orderService.selectServicerKPIForMonthPageBy(queryOrderParams);
		
		queryOrderParams.setUserId(userId==null?null:userId);
		if (userId==null) {
			ConsumeAcctGroupReport queryObject = new ConsumeAcctGroupReport();
			queryObject.setStatus("1");
			queryObject.setPageTools(new PageTools(7l));
			List<ConsumeAcctGroupReport> resultList = consumeAcctGroupService.selectConsumeAcctGroupReportPage(queryObject);
			mv.addObject("consumeAcctGroupReportList", resultList);
			
			kpiList = orderService.selectServicerKPIForDalilyPageBy(queryOrderParams);
		}else{
			kpiList = orderService.selectServicerKPIForDalilyPageBy(queryOrderParams);
		}

		mv.addObject("kpiList", kpiList);
		mv.addObject("kpiMonthList", kpiMonthList);
		logger.info("index response===" + mv);
		return mv;
	}

	private ModelAndView orderReport(ModelAndView mv, Long userId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		
		// 查询当日订单报表
		String date = DateUtils.dateToTightStr(new Date());
		SummaryReport currentReport = selectReportBy(userId, date,1);
		mv.addObject("currentReport", currentReport);

		// 查询昨日订单报表
		date = DateUtils.dateToTightStr(DateUtils.getYesterdayDate());
		SummaryReport yesterdayReport = selectReportBy(userId, date,1);
		mv.addObject("yesterdayReport", yesterdayReport);
		
		// 查询当月订单报表
		String month = DateUtils.dateToTightStr(new Date()).substring(0, 6);
		SummaryReport currentMonthReport = selectReportBy(userId, month,2);
		mv.addObject("currentMonthReport", currentMonthReport);
		
		// 查询上月订单报表
		SummaryReport lastMonthReport = selectReportBy(userId, DateUtils.getLastMonth(),2);
		mv.addObject("lastMonthReport", lastMonthReport);

		return mv;
	}

	private SummaryReport selectReportBy(Long userId, String date,int reportFlag) {
		List<SummaryReport> kpiList = null;
		CustOrder queryOrderParams = new CustOrder();
		queryOrderParams.setUserId(userId);
		queryOrderParams.setStartDate(date);
		queryOrderParams.setEndDate(date);
		
		if(userId==null&&reportFlag==2){
			kpiList = orderService.selectSalesPerformanceSummaryReport(queryOrderParams);
		}else if(userId!=null&&reportFlag==2){
			kpiList = orderService.selectServicerKPIForMonthPageBy(queryOrderParams);
		}else if(userId!=null&&reportFlag==1){
			kpiList = orderService.selectServicerKPIForDalilyPageBy(queryOrderParams);
		}else if(userId==null&&reportFlag==1){
			kpiList = orderService.selectServicerKPIForDalilyPageBy(queryOrderParams);
			SummaryReport sr = new SummaryReport();
			if(kpiList.size()>0){
				for(SummaryReport temp : kpiList){
					sr.sum(temp);
				}
			}
			kpiList.clear();
			kpiList.add(sr);
		}
		return kpiList.size()>0?kpiList.get(0):new SummaryReport();
	}
	
	public static void main(String[] args) {
		System.err.println(DateUtils.getLastMonth());
	}

	@RequestMapping(value = "/home")
	public ModelAndView home() {
		logger.info("home request");
		ModelAndView mv = new ModelAndView("page/Home");
		mv.addObject("test", "hello world !!!home");
		mv.addObject("date", new Date());
		// mv.setViewName("forward:/login.html");
		return mv;
	}
}
