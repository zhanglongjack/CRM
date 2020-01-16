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
import com.base.crm.customer.entity.CustInfoReport;
import com.base.crm.customer.service.CustInfoService;
import com.base.crm.users.entity.UserInfo;

@Controller
@RequestMapping(value = "/wechatAddFansNumReport")
@SessionAttributes("user")
public class WechatAddFansNumReportController {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private CustInfoService custInfoService;
	
	private static final String LOAD_PAGE_VIEW = "page/report/wechatAddFansNumReport/daily/Content :: container-fluid";
	private static final String LOAD_PAGE_MONTH_VIEW = "page/report/wechatAddFansNumReport/month/Content :: container-fluid";
	
	@RequestMapping(value = "/daily/pageView")
	@ResponseBody
	public Map<String, Object> serverSaleReportView(CustInfoReport queryObject,PageTools pageTools,@ModelAttribute("user") UserInfo user){
		queryObject.setPageTools(pageTools);
		logger.info("saleSummaryReportExport request:"+queryObject);
		
		long size = custInfoService.selectWechatAddFansNumByDailyPageCount(queryObject);
		pageTools.setTotal(size);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("pageTools", pageTools);
		return result;
	}
	
	@RequestMapping("/daily/loadPage")
	public ModelAndView loadPage(CustInfoReport queryObject,PageTools pageTools,@ModelAttribute("user") UserInfo user){
		queryObject.setPageTools(pageTools);
		logger.info("serverSaleReport loadPage request:"+queryObject);
		Assert.isTrue(user.isAdmin(), "非管理员不允许查询"); 
		
		long size = custInfoService.selectWechatAddFansNumByDailyPageCount(queryObject);
		pageTools.setTotal(size);
		
		List<CustInfoReport> resultList = custInfoService.selectWechatAddFansNumByDailyPage(queryObject);
		
		ModelAndView mv = new ModelAndView(LOAD_PAGE_VIEW);
		mv.addObject("resultList", resultList);
		mv.addObject("pageTools", pageTools);
		mv.addObject("queryObject", queryObject);
		return mv;
	}
	
	@RequestMapping(value = "/month/pageView")
	@ResponseBody
	public Map<String, Object> monthKPIReport(CustInfoReport queryObject,PageTools pageTools,@ModelAttribute("user") UserInfo user){
		queryObject.setPageTools(pageTools);
		logger.info("saleSummaryReportExport request:"+queryObject);
		
		long size = custInfoService.selectWechatAddFansNumByMonthPageCount(queryObject);
		pageTools.setTotal(size);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("pageTools", pageTools);
		return result;
	}
	
	@RequestMapping("/month/loadPage")
	public ModelAndView loadPageMonth(CustInfoReport queryObject,PageTools pageTools,@ModelAttribute("user") UserInfo user){
		queryObject.setPageTools(pageTools);
		logger.info("serverSaleReport loadPage request:"+queryObject);
		Assert.isTrue(user.isAdmin(), "非管理员不允许查询"); 
		
		long size = custInfoService.selectWechatAddFansNumByMonthPageCount(queryObject);
		pageTools.setTotal(size);
		
		List<CustInfoReport> resultList = custInfoService.selectWechatAddFansNumByMonthPage(queryObject);
		
		ModelAndView mv = new ModelAndView(LOAD_PAGE_MONTH_VIEW);
		mv.addObject("resultList", resultList);
		mv.addObject("pageTools", pageTools);
		mv.addObject("queryObject", queryObject);
		return mv;
	}

}
