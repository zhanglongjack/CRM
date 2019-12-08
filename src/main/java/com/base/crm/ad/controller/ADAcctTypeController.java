package com.base.crm.ad.controller;

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

import com.base.common.util.PageTools;
import com.base.crm.ad.entity.ADAcctType;
import com.base.crm.ad.service.ADAcctTypeService;
import com.base.crm.users.entity.UserInfo;

@Controller
@RequestMapping(value = "/adAcctType")
@SessionAttributes("user")
public class ADAcctTypeController {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private ADAcctTypeService adAcctTypeService;
	private static final String PRIMARY_MODAL_VIEW = "page/ad/adAcctType/ModifyModal";
	private static final String LOAD_PAGE_VIEW = "page/ad/adAcctType/Content :: container-fluid";
	
	

	@RequestMapping(value = "/primaryModalView")
	public String primaryModalView(Long id, String modifyModel, Model model) throws Exception {
		logger.debug("primaryModalView request:" + id + ",model:" + model);
		if (id != null) {
			ADAcctType result = adAcctTypeService.selectByPrimaryKey(id);
			model.addAttribute("modifyInfo", result);
		} 

		model.addAttribute("modifyModel", modifyModel);
		logger.debug("primaryModalView model : " + model);

		return PRIMARY_MODAL_VIEW;
	}

	@RequestMapping(value = "/pageView")
	@ResponseBody
	public Map<String, Object> pageView(ADAcctType queryObject, PageTools pageTools,
			@ModelAttribute("user") UserInfo user) {
		logger.debug("adConsumetView request : " +queryObject);
		
		Assert.isTrue(user.isAdmin(), "非管理员不允许查询"); 
		
		Long size = adAcctTypeService.selectPageTotalCount(queryObject);
		pageTools.setTotal(size);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("pageTools", pageTools);
		return result;
	}
	
	@RequestMapping(value = "/loadPage")
	public ModelAndView loadPage(ADAcctType queryObject, PageTools pageTools,
			@ModelAttribute("user") UserInfo user) throws Exception {
		logger.debug("loadPage ADAcctType request:" + queryObject + " page info ===" + pageTools);
		
		Assert.isTrue(user.isAdmin(), "非管理员不允许查询"); 
		
		queryObject.setPageTools(pageTools);
		ModelAndView mv = new ModelAndView(LOAD_PAGE_VIEW);
		Long size = adAcctTypeService.selectPageTotalCount(queryObject);
		pageTools.setTotal(size);
		List<ADAcctType> ciList = adAcctTypeService.selectPageByObjectForList(queryObject);
		logger.debug("loadPage ADAcctType result list info =====:" + ciList);
		
		mv.addObject("resultList", ciList);
		mv.addObject("pageTools", pageTools);
		mv.addObject("queryObject", queryObject);

		return mv;
	}

	@RequestMapping(value="/adAcctTypeEdit")
	@ResponseBody
	public Map<String,Object> edit(ADAcctType editData){
		logger.info("adAcctTypeEdit request:{}",editData);
		int num = adAcctTypeService.updateByPrimaryKeySelective(editData);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("success", true);
		map.put("editNumber", num);
		return map;
	}
	
	@RequestMapping(value="/adAcctTypeAdd")
	@ResponseBody
	public Map<String,Object> add(ADAcctType addData) throws Exception{
		logger.info("adAcctTypeAdd request:{}",addData);
		int num = adAcctTypeService.insertSelective(addData);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("success", true);
		map.put("addNumber", num);
		return map;
	}
	
	@RequestMapping(value="/adAcctTypeDel")
	@ResponseBody
	public Map<String,Object> adAcctTypeDel(ADAcctType addData) throws Exception{
		logger.info("adAcctTypeDel request:{}",addData);
		int num = adAcctTypeService.deleteByPrimaryKey(addData.getId());
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("success", true);
		map.put("addNumber", num);
		return map;
	}
}
