package com.base.crm.report.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.base.common.util.PageTools;
import com.base.crm.orders.entity.CustOrder;
import com.base.crm.orders.service.CustOrderService;
import com.base.crm.report.entity.SummaryReport;
import com.base.crm.users.entity.UserInfo;

@Controller
@RequestMapping(value = "/kpiReport")
@SessionAttributes("user")
public class DayliKPIReportController {
	private final Logger logger = LoggerFactory.getLogger(getClass());
//	@Autowired
//	private ConsumeAcctGroupService consumeAcctGroupService;
	@Autowired
	private CustOrderService orderService;
	
	private static final String LOAD_PAGE_VIEW = "page/report/dailyKPIReport/Content :: container-fluid";
	private static final String LOAD_PAGE_MONTH_VIEW = "page/report/monthKPIReport/Content :: container-fluid";
	
	@RequestMapping(value = "/dailyKPIReport/pageView")
	@ResponseBody
	public Map<String, Object> serverSaleReportView(CustOrder queryObject,PageTools pageTools,@ModelAttribute("user") UserInfo user){
		queryObject.setPageTools(pageTools);
		logger.info("saleSummaryReportExport request:"+queryObject);
		
		long size = orderService.selectDailyCountBy(queryObject);
		pageTools.setTotal(size);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("pageTools", pageTools);
		return result;
	}
	
	@RequestMapping("/dailyKPIReport/loadPage")
	public ModelAndView loadPage(CustOrder queryObject,PageTools pageTools,@ModelAttribute("user") UserInfo user){
		queryObject.setPageTools(pageTools);
		logger.info("serverSaleReport loadPage request:"+queryObject);
		
		long size = orderService.selectDailyCountBy(queryObject);
		pageTools.setTotal(size);
		
		List<SummaryReport> resultList = orderService.selectServicerKPIForDalilyPageBy(queryObject);
		
		ModelAndView mv = new ModelAndView(LOAD_PAGE_VIEW);
		mv.addObject("resultList", resultList);
		mv.addObject("pageTools", pageTools);
		mv.addObject("queryObject", queryObject);
		return mv;
	}
	
	@RequestMapping(value = "/monthKPIReport/pageView")
	@ResponseBody
	public Map<String, Object> monthKPIReport(CustOrder queryObject,PageTools pageTools,@ModelAttribute("user") UserInfo user){
		queryObject.setPageTools(pageTools);
		logger.info("saleSummaryReportExport request:"+queryObject);
		
		long size = orderService.selectMonthCountBy(queryObject);
		pageTools.setTotal(size);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("pageTools", pageTools);
		return result;
	}
	
	@RequestMapping("/monthKPIReport/loadPage")
	public ModelAndView loadPageMonth(CustOrder queryObject,PageTools pageTools,@ModelAttribute("user") UserInfo user){
		queryObject.setPageTools(pageTools);
		logger.info("serverSaleReport loadPage request:"+queryObject);
		
		long size = orderService.selectMonthCountBy(queryObject);
		pageTools.setTotal(size);
		
		List<SummaryReport> resultList = orderService.selectServicerKPIForMonthPageBy(queryObject);
		
		ModelAndView mv = new ModelAndView(LOAD_PAGE_MONTH_VIEW);
		mv.addObject("resultList", resultList);
		mv.addObject("pageTools", pageTools);
		mv.addObject("queryObject", queryObject);
		return mv;
	}

}
