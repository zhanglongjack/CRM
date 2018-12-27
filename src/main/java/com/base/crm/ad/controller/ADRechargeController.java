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
import com.base.crm.ad.entity.ADRechargeRecord;
import com.base.crm.ad.service.ADRechargeRecordService;
import com.base.crm.common.constants.CommonConstants;
import com.base.crm.users.entity.UserInfo;


@Controller
@RequestMapping(value = "/adRecharge")
@SessionAttributes("user")
public class ADRechargeController {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private ADRechargeRecordService rechargeService;

	@RequestMapping(value = "/primaryModalView")
	public String primaryModalView(Long id, String modifyModel, Model model) throws Exception {
		logger.debug("primaryModalView request:" + id + ",model:" + model);
		if (id != null) {
			ADRechargeRecord result = rechargeService.selectByPrimaryKey(id);
			model.addAttribute("modifyInfo", result);
		} 

		model.addAttribute("modifyModel", modifyModel);
		logger.debug("primaryModalView model : " + model);

		return "page/ad/adRecharge/ModifyModal";
	}

	@RequestMapping(value = "/pageView")
	@ResponseBody
	public Map<String, Object> pageView(ADRechargeRecord queryObject, PageTools pageTools,
			@ModelAttribute("user") UserInfo user) {
		logger.debug("adRechargeView request : " +queryObject);
		
		Assert.isTrue(user.isAdmin(), "非管理员不允许查询"); 
		
		Long size = rechargeService.selectPageTotalCount(queryObject);
		pageTools.setTotal(size);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("pageTools", pageTools);
		return result;
	}

	@RequestMapping(value = "/loadPage")
	public ModelAndView loadPage(ADRechargeRecord queryObject, PageTools pageTools,
			@ModelAttribute("user") UserInfo user) throws Exception {
		logger.debug("loadPage ADRechargeRecord request:" + queryObject + " page info ===" + pageTools);
		
		Assert.isTrue(user.isAdmin(), "非管理员不允许查询"); 
		
		queryObject.setPageTools(pageTools);
		ModelAndView mv = new ModelAndView("page/ad/adRecharge/Content :: container-fluid");
		Long size = rechargeService.selectPageTotalCount(queryObject);
		pageTools.setTotal(size);
		List<ADRechargeRecord> ciList = rechargeService.selectPageByObjectForList(queryObject);
		logger.debug("loadPage ADRechargeRecord result list info =====:" + ciList);
		
		mv.addObject("resultList", ciList);
		mv.addObject("pageTools", pageTools);
		mv.addObject("queryObject", queryObject);

		return mv;
	}

	@RequestMapping(value="/adRechargeEdit")
	@ResponseBody
	public Map<String,Object> edit(ADRechargeRecord editData){
		logger.info("adRechargeEdit request:{}",editData);
		int num = rechargeService.updateByPrimaryKeySelective(editData);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("success", true);
		map.put("editNumber", num);
		return map;
	}
	
	@RequestMapping(value="/adRechargeAdd")
	@ResponseBody
	public Map<String,Object> add(ADRechargeRecord addData) throws Exception{
		logger.info("adRechargeAdd request:{}",addData);
		int num = rechargeService.insertSelective(addData);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("success", true);
		map.put("addNumber", num);
		return map;
	}
	
	 
}
