package com.base.crm.depository.controller;

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
import com.base.crm.common.constants.CommonConstants;
import com.base.crm.depository.entity.Depository;
import com.base.crm.depository.service.DepositoryService;
import com.base.crm.users.entity.UserInfo;

@Controller
@RequestMapping(value = "/depository")
@SessionAttributes("user")
public class DepositoryController {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private DepositoryService depositoryService;
	@Autowired
	private CommonConstants commonConstants;
	
	private static final String PRIMARY_MODAL_VIEW = "page/depository/ModifyModal";
	private static final String LOAD_PAGE_VIEW = "page/depository/Content :: container-fluid";
	
	@RequestMapping(value = "/primaryModalView")
	public String primaryModalView(Long id, String modifyModel, Model model) throws Exception {
		logger.debug("primaryModalView request:" + id + ",model:" + model);
		if (id != null) {
			Depository result = depositoryService.selectByPrimaryKey(id);
			model.addAttribute("modifyInfo", result);
		} 

		model.addAttribute("modifyModel", modifyModel);
		logger.debug("primaryModalView model : " + model);

		return PRIMARY_MODAL_VIEW;
	}

	@RequestMapping(value = "/pageView")
	@ResponseBody
	public Map<String, Object> pageView(Depository queryObject, PageTools pageTools,
			@ModelAttribute("user") UserInfo user) {
		logger.debug("adConsumetView request : " +queryObject);
		
		Long size = depositoryService.selectPageTotalCount(queryObject);
		pageTools.setTotal(size);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("pageTools", pageTools);
		return result;
	}
	
	@RequestMapping(value = "/loadPage")
	public ModelAndView loadPage(Depository queryObject, PageTools pageTools,
			@ModelAttribute("user") UserInfo user) throws Exception {
		logger.debug("loadPage Depository request:" + queryObject + " page info ===" + pageTools);
		
		queryObject.setPageTools(pageTools);
		ModelAndView mv = new ModelAndView(LOAD_PAGE_VIEW);
		Long size = depositoryService.selectPageTotalCount(queryObject);
		pageTools.setTotal(size);
		List<Depository> ciList = depositoryService.selectPageByObjectForList(queryObject);
		logger.debug("loadPage Depository result list info =====:" + ciList);
		
		mv.addObject("resultList", ciList);
		mv.addObject("pageTools", pageTools);
		mv.addObject("queryObject", queryObject);

		return mv;
	}

	@RequestMapping(value="/depositoryEdit")
	@ResponseBody
	public Map<String,Object> edit(Depository editData){
		logger.info("depositoryEdit request:{}",editData);
		int num = depositoryService.updateByPrimaryKeySelective(editData);
		commonConstants.initDepository();
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("success", true);
		map.put("editNumber", num);
		return map;
	}
	
	@RequestMapping(value="/depositoryAdd")
	@ResponseBody
	public Map<String,Object> add(Depository addData) throws Exception{
		logger.info("depositoryAdd request:{}",addData);
		addData.setCreatedDate(DateUtils.dateToStrLong(new Date()));
		int num = depositoryService.insertSelective(addData);
		commonConstants.initDepository();
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("success", true);
		map.put("addNumber", num);
		return map;
	}
	
	@RequestMapping(value="/depositoryDel")
	@ResponseBody
	public Map<String,Object> depositoryDel(Long id) throws Exception{
		logger.info("depositoryDel request:{}",id);
		int num = depositoryService.deleteByPrimaryKey(id);
		commonConstants.initDepository();
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("success", true);
		map.put("addNumber", num);
		return map;
	}
}
