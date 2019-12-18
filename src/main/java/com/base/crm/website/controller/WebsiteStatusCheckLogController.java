package com.base.crm.website.controller;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.base.common.util.DateUtils;
import com.base.common.util.PageTools;
import com.base.crm.users.entity.UserInfo;
import com.base.crm.website.entity.WebsiteStatusCheckLog;
import com.base.crm.website.service.WebsiteStatusCheckLogService;

@Controller
@RequestMapping(value = "/websiteStatusCheckLog")
@SessionAttributes("user")
public class WebsiteStatusCheckLogController {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private WebsiteStatusCheckLogService websiteStatusCheckLogService;
	
	private static final String PRIMARY_MODAL_VIEW = "page/websiteStatusCheckLog/ModifyModal";
	private static final String LOAD_PAGE_VIEW = "page/websiteStatusCheckLog/Content :: container-fluid";
	
	@RequestMapping(value = "/primaryModalView")
	public String primaryModalView(Long record, String modifyModel, Model model) throws Exception {
		logger.debug("primaryModalView request:" + record + ",model:" + model);
		if (record != null) {
			WebsiteStatusCheckLog result = websiteStatusCheckLogService.selectByPrimaryKey(record);
			model.addAttribute("modifyInfo", result);
		} 

		model.addAttribute("modifyModel", modifyModel);
		logger.debug("primaryModalView model : " + model);

		return PRIMARY_MODAL_VIEW;
	}

	@RequestMapping(value = "/pageView")
	@ResponseBody
	public Map<String, Object> pageView(WebsiteStatusCheckLog queryObject, PageTools pageTools,
			@ModelAttribute("user") UserInfo user) {
		logger.debug("adConsumetView request : " +queryObject);
		
		Assert.isTrue(user.isAdmin(), "非管理员不允许查询"); 
		
		Long size = websiteStatusCheckLogService.selectPageTotalCount(queryObject);
		pageTools.setTotal(size);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("pageTools", pageTools);
		return result;
	}
	
	@RequestMapping(value = "/loadPage")
	public ModelAndView loadPage(WebsiteStatusCheckLog queryObject, PageTools pageTools,
			@ModelAttribute("user") UserInfo user) throws Exception {
		logger.debug("loadPage WebsiteStatusCheckLog request:" + queryObject + " page info ===" + pageTools);
		
		Assert.isTrue(user.isAdmin(), "非管理员不允许查询"); 
		
		queryObject.setPageTools(pageTools);
		ModelAndView mv = new ModelAndView(LOAD_PAGE_VIEW);
		Long size = websiteStatusCheckLogService.selectPageTotalCount(queryObject);
		pageTools.setTotal(size);
		List<WebsiteStatusCheckLog> ciList = websiteStatusCheckLogService.selectPageByObjectForList(queryObject);
		logger.debug("loadPage WebsiteStatusCheckLog result list info =====:" + ciList);
		
		mv.addObject("resultList", ciList);
		mv.addObject("pageTools", pageTools);
		mv.addObject("queryObject", queryObject);

		return mv;
	}

	@RequestMapping(value="/websiteStatusCheckLogEdit")
	@ResponseBody
	public Map<String,Object> edit(WebsiteStatusCheckLog editData){
		logger.info("websiteStatusCheckLogEdit request:{}",editData);
		int num = websiteStatusCheckLogService.updateByPrimaryKeySelective(editData);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("success", true);
		map.put("editNumber", num);
		return map;
	}
	
	@RequestMapping(value="/websiteStatusCheckLogAdd")
	@ResponseBody
	public Map<String,Object> add(WebsiteStatusCheckLog addData) throws Exception{
		logger.info("websiteStatusCheckLogAdd request:{}",addData);
		addData.setCreateDate(DateUtils.dateToTightStr(new Date()));
		addData.setCreateTime(DateUtils.dateToStrLong(new Date()));
		int num = websiteStatusCheckLogService.insertSelective(addData);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("success", true);
		map.put("addNumber", num);
		return map;
	}
	
	@RequestMapping(value="/websiteStatusCheckLogDel")
	@ResponseBody
	public Map<String,Object> websiteStatusCheckLogDel(Long id) throws Exception{
		logger.info("websiteStatusCheckLogDel request:{}",id);
		int num = websiteStatusCheckLogService.deleteByPrimaryKey(id);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("success", true);
		map.put("addNumber", num);
		return map;
	}
}
