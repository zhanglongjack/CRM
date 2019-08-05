package com.base.crm.host.config.controller;

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
import com.base.crm.host.config.entity.HostConfig;
import com.base.crm.host.config.service.HostConfigService;
import com.base.crm.users.entity.UserInfo;

@Controller
@RequestMapping(value = "/hostConfig")
@SessionAttributes("user")
public class HostConfigController {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private HostConfigService hostConfigService;

	@RequestMapping(value = "/primaryModalView")
	public String primaryModalView(Long id, String modifyModel, Model model) throws Exception {
		logger.debug("primaryModalView request:" + id + ",model:" + model);
		if (id != null) {
			HostConfig result = hostConfigService.selectByPrimaryKey(id);
			model.addAttribute("modifyInfo", result);
		} 

		model.addAttribute("modifyModel", modifyModel);
		logger.debug("primaryModalView model : " + model);

		return "page/hostConfig/ModifyModal";
	}

	@RequestMapping(value = "/pageView")
	@ResponseBody
	public Map<String, Object> view(HostConfig queryObject, PageTools pageTools,
			@ModelAttribute("user") UserInfo user) {
		logger.debug("hostConfigtView request : " +queryObject);
		
		
		Long size = hostConfigService.selectPageTotalCount(queryObject);
		pageTools.setTotal(size);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("pageTools", pageTools);
		return result;
	}

	@RequestMapping(value = "/loadPage")
	public ModelAndView loadPage(HostConfig queryObject, PageTools pageTools,
			@ModelAttribute("user") UserInfo user) throws Exception {
		logger.debug("loadPage HostConfig request:" + queryObject + " page info ===" + pageTools);
		
		queryObject.setPageTools(pageTools);
		ModelAndView mv = new ModelAndView("page/hostConfig/Content :: container-fluid");
		Long size = hostConfigService.selectPageTotalCount(queryObject);
		pageTools.setTotal(size);
		List<HostConfig> ciList = hostConfigService.selectPageByObjectForList(queryObject);
		logger.debug("loadPage HostConfig result list info =====:" + ciList);
		
		mv.addObject("resultList", ciList);
		mv.addObject("pageTools", pageTools);
		mv.addObject("queryObject", queryObject);

		return mv;
	}

	@RequestMapping(value="/hostConfigEdit")
	@ResponseBody
	public Map<String,Object> edit(HostConfig editData){
		logger.info("hostConfigEdit request:{}",editData);
		int num = hostConfigService.updateByPrimaryKeySelective(editData);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("success", true);
		map.put("editNumber", num);
		return map;
	}
	
	@RequestMapping(value="/hostConfigAdd")
	@ResponseBody
	public Map<String,Object> add(HostConfig addData) throws Exception{
		logger.info("hostConfigAdd request:{}",addData);
		int num = hostConfigService.insertSelective(addData);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("success", true);
		map.put("addNumber", num);
		return map;
	}

     
	 
}
