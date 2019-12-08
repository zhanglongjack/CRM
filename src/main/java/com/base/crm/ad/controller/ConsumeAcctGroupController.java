package com.base.crm.ad.controller;

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
import com.base.crm.ad.entity.ConsumeAcctGroup;
import com.base.crm.ad.service.ConsumeAcctGroupService;
import com.base.crm.users.entity.UserInfo;

@Controller
@RequestMapping(value = "/consumeAcctGroup")
@SessionAttributes("user")
public class ConsumeAcctGroupController {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private ConsumeAcctGroupService consumeAcctGroupService;
	private static final String PRIMARY_MODAL_VIEW = "page/ad/consumeAcctGroup/ModifyModal";
	private static final String LOAD_PAGE_VIEW = "page/ad/consumeAcctGroup/Content :: container-fluid";

	@RequestMapping(value = "/primaryModalView")
	public String primaryModalView(Long id, String modifyModel, Model model) throws Exception {
		logger.debug("primaryModalView request:" + id + ",model:" + model);
		if (id != null) {
			ConsumeAcctGroup result = consumeAcctGroupService.selectByPrimaryKey(id);
			model.addAttribute("modifyInfo", result);
		} 

		model.addAttribute("modifyModel", modifyModel);
		logger.debug("primaryModalView model : " + model);

		return PRIMARY_MODAL_VIEW;
	}

	@RequestMapping(value = "/pageView")
	@ResponseBody
	public Map<String, Object> pageView(ConsumeAcctGroup queryObject, PageTools pageTools,
			@ModelAttribute("user") UserInfo user) {
		logger.debug("adConsumetView request : " +queryObject);
		
		Assert.isTrue(user.isAdmin(), "非管理员不允许查询"); 
		
		Long size = consumeAcctGroupService.selectPageTotalCount(queryObject);
		pageTools.setTotal(size);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("pageTools", pageTools);
		return result;
	}
	
	@RequestMapping(value = "/loadPage")
	public ModelAndView loadPage(ConsumeAcctGroup queryObject, PageTools pageTools,
			@ModelAttribute("user") UserInfo user) throws Exception {
		logger.debug("loadPage ConsumeAcctGroup request:" + queryObject + " page info ===" + pageTools);
		
		Assert.isTrue(user.isAdmin(), "非管理员不允许查询"); 
		
		queryObject.setPageTools(pageTools);
		ModelAndView mv = new ModelAndView(LOAD_PAGE_VIEW);
		Long size = consumeAcctGroupService.selectPageTotalCount(queryObject);
		pageTools.setTotal(size);
		List<ConsumeAcctGroup> ciList = consumeAcctGroupService.selectPageByObjectForList(queryObject);
		logger.debug("loadPage ConsumeAcctGroup result list info =====:" + ciList);
		
		mv.addObject("resultList", ciList);
		mv.addObject("pageTools", pageTools);
		mv.addObject("queryObject", queryObject);

		return mv;
	}

	@RequestMapping(value="/consumeAcctGroupEdit")
	@ResponseBody
	public Map<String,Object> edit(ConsumeAcctGroup editData){
		logger.info("consumeAcctGroupEdit request:{}",editData);
		int num = consumeAcctGroupService.updateByPrimaryKeySelective(editData);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("success", true);
		map.put("editNumber", num);
		return map;
	}
	
	@RequestMapping(value="/consumeAcctGroupAdd")
	@ResponseBody
	public Map<String,Object> add(ConsumeAcctGroup addData) throws Exception{
		logger.info("consumeAcctGroupAdd request:{}",addData);
		addData.setCreatedDatetime(DateUtils.dateToStrLong(new Date()));
		int num = consumeAcctGroupService.insertSelective(addData);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("success", true);
		map.put("addNumber", num);
		return map;
	}
	
	@RequestMapping(value="/consumeAcctGroupDel")
	@ResponseBody
	public Map<String,Object> consumeAcctGroupDel(ConsumeAcctGroup addData) throws Exception{
		logger.info("consumeAcctGroupDel request:{}",addData);
		int num = consumeAcctGroupService.deleteByPrimaryKey(addData.getId());
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("success", true);
		map.put("addNumber", num);
		return map;
	}
}
