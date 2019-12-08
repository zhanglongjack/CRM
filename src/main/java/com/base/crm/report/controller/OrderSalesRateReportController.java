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
import com.base.crm.orders.entity.OrderSalesRateReport;
import com.base.crm.orders.service.CustOrderService;
import com.base.crm.users.entity.UserInfo;

@Controller
@RequestMapping(value = "/orderSalesRateReport")
@SessionAttributes("user")
public class OrderSalesRateReportController {
	private final Logger logger = LoggerFactory.getLogger(getClass()); 
	@Autowired
	private CustOrderService orderService;
	
	private static final String LOAD_PAGE_VIEW = "page/report/orderSalesRateReport/Content :: container-fluid";
	private static final String LOAD_PAGE_SUM_VIEW = "page/report/salesRateReport/Content :: container-fluid";
	
	@RequestMapping(value = "/orderSalesRateReport/pageView")
	@ResponseBody
	public Map<String, Object> orderSalesRateReportPageView(OrderSalesRateReport queryObject,PageTools pageTools,@ModelAttribute("user") UserInfo user){
		queryObject.setPageTools(pageTools);
		logger.info("orderSalesRateReportPageView request:"+queryObject);
		
		long size = orderService.selectOrderSalesRateReportPageCountByMonth(queryObject);
		pageTools.setTotal(size);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("pageTools", pageTools);
		return result;
	}
	
	@RequestMapping("/orderSalesRateReport/loadPage")
	public ModelAndView orderSalesRateReportLoadPage(OrderSalesRateReport queryObject,PageTools pageTools,@ModelAttribute("user") UserInfo user){
		queryObject.setPageTools(pageTools);
		logger.info("orderSalesRateReportLoadPage loadPage request:"+queryObject);
		Assert.isTrue(user.isAdmin(), "非管理员不允许查询"); 
		
		long size = orderService.selectOrderSalesRateReportPageCountByMonth(queryObject);
		pageTools.setTotal(size);
		
		List<OrderSalesRateReport> resultList = orderService.selectOrderSalesRateReportPageByMonth(queryObject);
		
		ModelAndView mv = new ModelAndView(LOAD_PAGE_VIEW);
		mv.addObject("resultList", resultList);
		mv.addObject("pageTools", pageTools);
		mv.addObject("queryObject", queryObject);
		return mv;
	}
	
	@RequestMapping(value = "/salesRateReport/pageView")
	@ResponseBody
	public Map<String, Object> salesRateReportPageView(OrderSalesRateReport queryObject,PageTools pageTools,@ModelAttribute("user") UserInfo user){
		queryObject.setPageTools(pageTools);
		logger.info("salesRateReportPageView request:"+queryObject);
		
		long size = orderService.selectOrderSalesRateReportPageCountByMonth(queryObject);
		pageTools.setTotal(size);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("pageTools", pageTools);
		return result;
	}
	
	@RequestMapping("/salesRateReport/loadPage")
	public ModelAndView salesRateReportLoadPage(OrderSalesRateReport queryObject,PageTools pageTools,@ModelAttribute("user") UserInfo user){
		queryObject.setPageTools(pageTools);
		logger.info("salesRateReportLoadPage loadPage request:"+queryObject);
		Assert.isTrue(user.isAdmin(), "非管理员不允许查询"); 
		
		long size = orderService.selectSalesRateReportPageCountByMonth(queryObject);
		pageTools.setTotal(size);
		
		List<OrderSalesRateReport> resultList = orderService.selectSalesRateReportPageByMonth(queryObject);
		
		ModelAndView mv = new ModelAndView(LOAD_PAGE_SUM_VIEW);
		mv.addObject("resultList", resultList);
		mv.addObject("pageTools", pageTools);
		mv.addObject("queryObject", queryObject);
		return mv;
	}

}
