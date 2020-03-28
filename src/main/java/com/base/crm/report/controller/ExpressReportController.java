package com.base.crm.report.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.base.common.util.PageTools;
import com.base.crm.orders.service.CustOrderService;
import com.base.crm.report.entity.ExpressReport;
import com.base.crm.users.entity.UserInfo;

@Controller
@RequestMapping(value = "/expressReport")
@SessionAttributes("user")
public class ExpressReportController {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private CustOrderService orderService;
	
	private static final String LOAD_PAGE_VIEW = "page/report/expressReport/Content :: container-fluid";
	
	@RequestMapping(value = "/expressReport/pageView")
	@ResponseBody
	public Map<String, Object> expressReportReportView(ExpressReport queryObject,PageTools pageTools,@ModelAttribute("user") UserInfo user){
		queryObject.setPageTools(pageTools);
		logger.info("expressReportReportView request:"+queryObject);
		
		long size = orderService.selectExpressReportPageCountBy(queryObject);
		pageTools.setTotal(size);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("pageTools", pageTools);
		return result;
	}
	
	@RequestMapping("/expressReport/loadPage")
	public ModelAndView loadPage(ExpressReport queryObject,PageTools pageTools,@ModelAttribute("user") UserInfo user){
		queryObject.setPageTools(pageTools);
		logger.info("expressReport loadPage request:"+queryObject);
		
		long size = orderService.selectExpressReportPageCountBy(queryObject);
		pageTools.setTotal(size);
		
		List<ExpressReport> resultList = orderService.selectExpressReportPageBy(queryObject);
		
		ModelAndView mv = new ModelAndView(LOAD_PAGE_VIEW);
		mv.addObject("resultList", resultList);
		mv.addObject("pageTools", pageTools);
		mv.addObject("queryObject", queryObject);
		return mv;
	}
}
