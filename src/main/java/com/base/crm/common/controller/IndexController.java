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
import com.base.crm.customer.service.CustInfoService;
import com.base.crm.orders.entity.CustOrder;
import com.base.crm.orders.service.CustOrderService;
import com.base.crm.report.entity.ConsumeAcctGroupReport;
import com.base.crm.report.entity.SummaryReport;
import com.base.crm.users.entity.UserInfo;
import com.base.crm.website.entity.WebsiteStatusCheckLog;
import com.base.crm.website.service.WebsiteStatusCheckLogService;

@Controller
@SessionAttributes("user")
public class IndexController {
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

	@Autowired
	private CustInfoService custInfoService;
	@Autowired
	private CustOrderService orderService;
	@Autowired
	private WebsiteStatusCheckLogService websiteStatusCheckLogService;
	@Autowired
	private ConsumeAcctGroupService consumeAcctGroupService;

	@RequestMapping(value = "/index")
	public ModelAndView index(@ModelAttribute("user") UserInfo user) {
		logger.info("index request");
		Long userId = user.isAdmin() ? null : user.getuId();
		ModelAndView mv = new ModelAndView("page/index");
		mv.addObject("date", new Date());
		orderReport(mv, userId);
		if (userId==null) {
			ConsumeAcctGroupReport queryObject = new ConsumeAcctGroupReport();
			queryObject.setStatus("1");
			queryObject.setPageTools(new PageTools(7l));
			List<ConsumeAcctGroupReport> resultList = consumeAcctGroupService.selectConsumeAcctGroupReportPage(queryObject);

			CustOrder queryOrderParams = new CustOrder();
			queryOrderParams.setPageTools(new PageTools(1, 15));
			List<SummaryReport> kpiList = orderService.selectDailyKPIOrderSummaryPageBy(queryOrderParams);

			PageTools pageTools = new PageTools();
			pageTools.setPageSize(15);
			WebsiteStatusCheckLog log = new WebsiteStatusCheckLog();
			log.setStatus("1");
			log.setPageTools(pageTools);
			
			Long size = websiteStatusCheckLogService.selectPageTotalCount(log);
			
			List<WebsiteStatusCheckLog> netWorkCheckList = websiteStatusCheckLogService.selectPageByObjectForList(log);
			mv.addObject("netWorkCheckList", netWorkCheckList);
			mv.addObject("consumeAcctGroupReportList", resultList);
			mv.addObject("kpiList", kpiList);
			mv.addObject("errorSize", size);
		}

		logger.info("index response===" + mv);
		return mv;
	}

	private ModelAndView orderReport(ModelAndView mv, Long userId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		
		// 查询当日订单报表
		String date = DateUtils.dateToTightStr(new Date());
		CustOrder queryOrderParams = new CustOrder();
		queryOrderParams.setUserId(userId);
		queryOrderParams.setStartDate(date);
		queryOrderParams.setEndDate(date);
		List<SummaryReport> kpiList = orderService.selectDailyKPIOrderSummaryPageBy(queryOrderParams);
		mv.addObject("currentReport", kpiList.size()>0?kpiList.get(0):new SummaryReport());
		
//		params.put("orderDate", DateUtils.dateToTightStr(new Date()));
//		Map<String, Double> orderSummaryMap = orderService.selectOrderSummaryBy(params);
//		Long count = custInfoService.selectCustCountByMonth(params);
//		mv.addObject("date", new Date());
//		mv.addObject("currentReport", kpiList);
//		mv.addObject("currentDayCount", count); // 当日粉丝数

		// 查询昨日订单报表
		date = DateUtils.dateToTightStr(DateUtils.getYesterdayDate());
		queryOrderParams = new CustOrder();
		queryOrderParams.setUserId(userId);
		queryOrderParams.setStartDate(date);
		queryOrderParams.setEndDate(date);
		kpiList = orderService.selectDailyKPIOrderSummaryPageBy(queryOrderParams);
		mv.addObject("yesterdayReport", kpiList.size()>0?kpiList.get(0):new SummaryReport());
		
//		params.put("orderDate", DateUtils.dateToTightStr(DateUtils.getYesterdayDate()));
//		orderSummaryMap = orderService.selectOrderSummaryBy(params);
//		Long yesterdayCount = custInfoService.selectCustCountByMonth(params);
//		mv.addObject("yesterdayReport", orderSummaryMap);
//		mv.addObject("yesterdayCount", yesterdayCount); // 昨日粉丝数

		// 查询当月订单报表
		String month = DateUtils.dateToTightStr(new Date()).substring(0, 6);
		queryOrderParams = new CustOrder();
		queryOrderParams.setUserId(userId);
		queryOrderParams.setStartDate(month);
		queryOrderParams.setEndDate(month);
		kpiList = orderService.selectDailyKPIOrderSummaryPageByMonth(queryOrderParams);
		mv.addObject("currentMonthReport", kpiList.size()>0?kpiList.get(0):new SummaryReport());
		
//		params.clear();
//		String month = DateUtils.dateToTightStr(new Date()).substring(0, 6);
//		params.put("userId", userId);
//		params.put("startDate", month);
//		params.put("endDate", month);
//		orderSummaryMap = orderService.selectOrderSummaryBy(params);
//		Long monthCustCount = custInfoService.selectCustCountByMonth(params);
//		mv.addObject("currentMonthReport", orderSummaryMap);
//		mv.addObject("monthCustCount", monthCustCount); // 当月粉丝数

		// 查询上月订单报表
		queryOrderParams = new CustOrder();
		queryOrderParams.setUserId(userId);
		queryOrderParams.setStartDate(DateUtils.getLastMonth());
		queryOrderParams.setEndDate(DateUtils.getLastMonth());
		kpiList = orderService.selectDailyKPIOrderSummaryPageByMonth(queryOrderParams);
		mv.addObject("lastMonthReport", kpiList.size()>0?kpiList.get(0):new SummaryReport());
		
//		params.put("startDate", DateUtils.getLastMonth());
//		params.put("endDate", DateUtils.getLastMonth());
//		orderSummaryMap = orderService.selectOrderSummaryBy(params);
//		Long lastMonthCustCount = custInfoService.selectCustCountByMonth(params);
//		mv.addObject("lastMonthReport", orderSummaryMap);
//		mv.addObject("lastMonthCustCount", lastMonthCustCount); // 上月粉丝数

		return mv;
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
