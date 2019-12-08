package com.base.crm.host.status.controller;

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
import com.base.crm.host.status.entity.HostStatus;
import com.base.crm.host.status.service.HostStatusService;
import com.base.crm.users.entity.UserInfo;

@Controller
@RequestMapping(value = "/hostStatus")
@SessionAttributes("user")
public class HostStatusController {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private HostStatusService hostStatusService;

	@RequestMapping(value = "/primaryModalView")
	public String primaryModalView(Long id, String modifyModel, Model model) throws Exception {
		logger.debug("primaryModalView request:" + id + ",model:" + model);
		if (id != null) {
			HostStatus result = hostStatusService.selectByPrimaryKey(id);
			model.addAttribute("modifyInfo", result);
		} 

		model.addAttribute("modifyModel", modifyModel);
		logger.debug("primaryModalView model : " + model);

		return "page/hostStatus/ModifyModal";
	}

	@RequestMapping(value = "/pageView")
	@ResponseBody
	public Map<String, Object> view(HostStatus queryObject, PageTools pageTools,
			@ModelAttribute("user") UserInfo user) {
		logger.debug("hostStatusView request : " +queryObject);
		
		Long size = hostStatusService.selectPageTotalCount(queryObject);
		pageTools.setTotal(size);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("pageTools", pageTools);
		return result;
	}

	@RequestMapping(value = "/loadPage")
	public ModelAndView loadPage(HostStatus queryObject, PageTools pageTools,
			@ModelAttribute("user") UserInfo user) throws Exception {
		logger.debug("loadPage HostStatus request:" + queryObject + " page info ===" + pageTools);
		
		queryObject.setPageTools(pageTools);
		ModelAndView mv = new ModelAndView("page/hostStatus/Content :: container-fluid");
		Long size = hostStatusService.selectPageTotalCount(queryObject);
		pageTools.setTotal(size);
		List<HostStatus> ciList = hostStatusService.selectPageByObjectForList(queryObject);
		logger.debug("loadPage HostStatus result list info =====:" + ciList);
		
		mv.addObject("resultList", ciList);
		mv.addObject("pageTools", pageTools);
		mv.addObject("queryObject", queryObject);

		return mv;
	}

	@RequestMapping(value="/hostStatusEdit")
	@ResponseBody
	public Map<String,Object> edit(HostStatus editData){
		logger.info("hostStatusEdit request:{}",editData);
		int num = hostStatusService.updateByPrimaryKeySelective(editData);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("success", true);
		map.put("editNumber", num);
		return map;
	}
	
	@RequestMapping(value="/hostStatusAdd")
	@ResponseBody
	public Map<String,Object> add(HostStatus addData) throws Exception{
		logger.info("hostStatusAdd request:{}",addData);
		int num = hostStatusService.insertSelective(addData);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("success", true);
		map.put("addNumber", num);
		return map;
	}
	
	@RequestMapping(value="/hostStatusDel")
	@ResponseBody
	public Map<String,Object> hostStatusDel(HostStatus addData) throws Exception{
		logger.info("hostStatusDel request:{}",addData);
		int num = hostStatusService.deleteByPrimaryKey(addData.getId());
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("success", true);
		map.put("addNumber", num);
		return map;
	}

     
	 
}
