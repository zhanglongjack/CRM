package com.base.crm.salary.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.base.common.util.PageTools;
import com.base.crm.salary.entity.ServerSalary;
import com.base.crm.salary.service.ServerSalaryService;
import com.base.crm.users.entity.UserInfo;

@Controller
@RequestMapping(value = "/serverSalary")
@SessionAttributes("user")
public class ServerSalaryController {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private ServerSalaryService serverSalaryService;

	@RequestMapping(value = "/primaryModalView")
	public String primaryModalView(Long id, String modifyModel, Model model) throws Exception {
		logger.debug("primaryModalView request:" + id + ",model:" + model);
		if (id != null) {
			ServerSalary result = serverSalaryService.selectByPrimaryKey(id);
			model.addAttribute("modifyInfo", result);
		} 

		model.addAttribute("modifyModel", modifyModel);
		logger.debug("primaryModalView model : " + model);

		return "page/serverSalary/ModifyModal";
	}

	@RequestMapping(value = "/serverSalaryView")
	public ModelAndView view(ServerSalary queryObject, PageTools pageTools,
			@ModelAttribute("user") UserInfo user) {
		logger.debug("serverSalarytView request : " +queryObject);
		
		if(!user.isAdmin()){
			queryObject.setUserId(user.getuId());
		}
		
		Long size = serverSalaryService.selectPageTotalCount(queryObject);
		pageTools.setTotal(size);
		ModelAndView mv = new ModelAndView("page/serverSalary/ServerSalaryView");
		queryObject.setPageTools(pageTools);
		mv.addObject("pageTools", pageTools);
		return mv;
	}

	@RequestMapping(value = "/loadPage")
	public ModelAndView loadPage(ServerSalary queryObject, PageTools pageTools,
			@ModelAttribute("user") UserInfo user) throws Exception {
		logger.debug("loadPage ServerSalary request:" + queryObject + " page info ===" + pageTools);
		
		if(!user.isAdmin()){
			queryObject.setUserId(user.getuId());
		}
		
		queryObject.setPageTools(pageTools);
		ModelAndView mv = new ModelAndView("page/serverSalary/Content :: container-fluid");
		Long size = serverSalaryService.selectPageTotalCount(queryObject);
		pageTools.setTotal(size);
		List<ServerSalary> ciList = serverSalaryService.selectPageByObjectForList(queryObject);
		logger.debug("loadPage ServerSalary result list info =====:" + ciList);
		
		mv.addObject("resultList", ciList);
		mv.addObject("pageTools", pageTools);
		mv.addObject("queryObject", queryObject);

		return mv;
	}

	@RequestMapping(value="/serverSalaryEdit")
	@ResponseBody
	public Map<String,Object> edit(ServerSalary editData){
		logger.info("serverSalaryEdit request:{}",editData);
		int num = serverSalaryService.updateByPrimaryKeySelective(editData);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("success", true);
		map.put("editNumber", num);
		return map;
	}
	
	@RequestMapping(value="/serverSalaryAdd")
	@ResponseBody
	public Map<String,Object> add(ServerSalary addData) throws Exception{
		logger.info("serverSalaryAdd request:{}",addData);
		int num = serverSalaryService.insertSelective(addData);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("success", true);
		map.put("addNumber", num);
		return map;
	}

     
	 
}
