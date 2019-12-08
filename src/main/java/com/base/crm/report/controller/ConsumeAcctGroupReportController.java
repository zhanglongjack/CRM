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
import com.base.crm.ad.service.ConsumeAcctGroupService;
import com.base.crm.report.entity.ConsumeAcctGroupReport;
import com.base.crm.users.entity.UserInfo;

@Controller
@RequestMapping(value = "/consumeAcctGroupReport")
@SessionAttributes("user")
public class ConsumeAcctGroupReportController {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private ConsumeAcctGroupService consumeAcctGroupService;
	
	private static final String LOAD_PAGE_VIEW = "page/report/consumeAcctGroupReport/Content :: container-fluid";
	
	@RequestMapping(value = "/consumeAcctGroupReport/pageView")
	@ResponseBody
	public Map<String, Object> serverSaleReportView(ConsumeAcctGroupReport queryObject,PageTools pageTools,@ModelAttribute("user") UserInfo user){
		queryObject.setStatus(queryObject.getStatus()==null?"1":queryObject.getStatus());
		queryObject.setPageTools(pageTools);
		logger.info("saleSummaryReportExport request:"+queryObject);
		
		long size = consumeAcctGroupService.selectConsumeAcctGroupReportPageTotalCount(queryObject);
		pageTools.setTotal(size);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("pageTools", pageTools);
		return result;
	}
	
	@RequestMapping("/consumeAcctGroupReport/loadPage")
	public ModelAndView loadPage(ConsumeAcctGroupReport queryObject,PageTools pageTools,@ModelAttribute("user") UserInfo user){
		queryObject.setStatus(queryObject.getStatus()==null?"1":queryObject.getStatus());
		queryObject.setPageTools(pageTools);
		logger.info("serverSaleReport loadPage request:"+queryObject);
		Assert.isTrue(user.isAdmin(), "非管理员不允许查询"); 
		
		long size = consumeAcctGroupService.selectConsumeAcctGroupReportPageTotalCount(queryObject);
		pageTools.setTotal(size);
		List<ConsumeAcctGroupReport> resultList = consumeAcctGroupService.selectConsumeAcctGroupReportPage(queryObject);
		ModelAndView mv = new ModelAndView(LOAD_PAGE_VIEW);
		mv.addObject("resultList", resultList);
		mv.addObject("pageTools", pageTools);
		mv.addObject("queryObject", queryObject);
		return mv;
	}
	
	@RequestMapping(value = "/consumeAcctGroupReportMonth/pageView")
	@ResponseBody
	public Map<String, Object> monthPageView(ConsumeAcctGroupReport queryObject,PageTools pageTools,@ModelAttribute("user") UserInfo user){
		queryObject.setStatus(queryObject.getStatus()==null?"1":queryObject.getStatus());
		queryObject.setPageTools(pageTools);
		logger.info("saleSummaryReportExport request:"+queryObject);
		
		long size = consumeAcctGroupService.selectConsumeAcctGroupReportPageTotalCountMonth(queryObject);
		pageTools.setTotal(size);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("pageTools", pageTools);
		return result;
	}
	
	@RequestMapping("/consumeAcctGroupReportMonth/loadPage")
	public ModelAndView loadMonthPage(ConsumeAcctGroupReport queryObject,PageTools pageTools,@ModelAttribute("user") UserInfo user){
		queryObject.setStatus(queryObject.getStatus()==null?"1":queryObject.getStatus());
		queryObject.setPageTools(pageTools);
		logger.info("serverSaleReport loadPage request:"+queryObject);
		Assert.isTrue(user.isAdmin(), "非管理员不允许查询"); 
		
		long size = consumeAcctGroupService.selectConsumeAcctGroupReportPageTotalCountMonth(queryObject);
		pageTools.setTotal(size);
		List<ConsumeAcctGroupReport> resultList = consumeAcctGroupService.selectConsumeAcctGroupReportPageMonth(queryObject);
		ModelAndView mv = new ModelAndView(LOAD_PAGE_VIEW);
		mv.addObject("resultList", resultList);
		mv.addObject("pageTools", pageTools);
		mv.addObject("queryObject", queryObject);
		return mv;
	}

}
