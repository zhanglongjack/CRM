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

import com.base.common.util.ExcelView;
import com.base.common.util.PageTools;
import com.base.crm.procurement.service.ProcurementCostService;
import com.base.crm.report.entity.ServerSaleReport;
import com.base.crm.report.excel.ServerSaleReportExcelMappings;
import com.base.crm.users.entity.UserInfo;

@Controller
@RequestMapping(value = "/serverSaleReport")
@SessionAttributes("user")
public class ServerSaleReportController {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ServerSaleReportExcelMappings mapping;
	@Autowired
	private ProcurementCostService procurementCostService;
	
	@RequestMapping("/serverSaleReportExport")
    public ModelAndView serverSaleReportExport(ServerSaleReport queryObject){
		logger.info("serverSaleReportExport request:"+queryObject);
         
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("ExcelMappings", mapping.statistics(queryObject));

        return new ModelAndView(new ExcelView(), map);
    }
	
	@RequestMapping(value = "/serverSaleReport/pageView")
	@ResponseBody
	public Map<String, Object> serverSaleReportView(ServerSaleReport queryObject,PageTools pageTools,@ModelAttribute("user") UserInfo user){
		logger.info("saleSummaryReportExport request:"+queryObject);
		List<String> monthList = procurementCostService.queryMonthBy(queryObject.getMonth());
		
		pageTools.setTotal((long) monthList.size());
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("pageTools", pageTools);
		result.put("monthList", monthList);
		return result;
	}
	
	@RequestMapping("/serverSaleReport/loadPage")
	public ModelAndView loadPage(ServerSaleReport queryObject,PageTools pageTools,@ModelAttribute("user") UserInfo user){
		logger.info("serverSaleReport loadPage request:"+queryObject);
		Assert.isTrue(user.isAdmin(), "非管理员不允许查询"); 
		
		mapping.statistics(queryObject);
		pageTools.setTotal( (long) mapping.getReportResult().size());
		
		ModelAndView mv = new ModelAndView("page/report/serverSaleReport/Content :: container-fluid");
		mv.addObject("resultList", mapping.getReportResult());
//		mv.addObject("sumData", mapping.getCollectData());
		mv.addObject("pageTools", pageTools);
		mv.addObject("queryObject", queryObject);
		return mv;
	}

 
}
