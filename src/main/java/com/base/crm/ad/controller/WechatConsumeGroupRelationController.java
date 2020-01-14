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
import com.base.crm.ad.entity.WechatConsumeGroupRelation;
import com.base.crm.ad.service.ConsumeAcctGroupService;
import com.base.crm.ad.service.WechatConsumeGroupRelationService;
import com.base.crm.report.entity.ConsumeAcctGroupReport;
import com.base.crm.users.entity.UserInfo;

@Controller
@RequestMapping(value = "/wechatConsumeGroupRelation")
@SessionAttributes("user")
public class WechatConsumeGroupRelationController {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private WechatConsumeGroupRelationService wechatConsumeGroupRelationService;
	@Autowired
	private ConsumeAcctGroupService consumeAcctGroupService;
	private static final String PRIMARY_MODAL_VIEW = "page/ad/wechatConsumeGroupRelation/ModifyModal";
	private static final String LOAD_PAGE_VIEW = "page/ad/wechatConsumeGroupRelation/Content :: container-fluid";
	
	

	@RequestMapping(value = "/primaryModalView")
	public String primaryModalView(WechatConsumeGroupRelation record, String modifyModel, Model model) throws Exception {
		logger.debug("primaryModalView request:" + record + ",model:" + model);
		if (record != null) {
			WechatConsumeGroupRelation result = wechatConsumeGroupRelationService.selectByPrimaryKey(record);
			model.addAttribute("modifyInfo", result);
		} 

		List<ConsumeAcctGroup> groupList = consumeAcctGroupService.selectPageByObjectForList(new ConsumeAcctGroup());
		model.addAttribute("modifyModel", modifyModel);
		model.addAttribute("groupList", groupList);
		logger.debug("primaryModalView model : " + model);

		return PRIMARY_MODAL_VIEW;
	}

	@RequestMapping(value = "/pageView")
	@ResponseBody
	public Map<String, Object> pageView(WechatConsumeGroupRelation queryObject, PageTools pageTools,
			@ModelAttribute("user") UserInfo user) {
		logger.debug("adConsumetView request : " +queryObject);
		
		Long size = wechatConsumeGroupRelationService.selectPageTotalCount(queryObject);
		pageTools.setTotal(size);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("pageTools", pageTools);
		return result;
	}
	
	@RequestMapping(value = "/loadPage")
	public ModelAndView loadPage(WechatConsumeGroupRelation queryObject, PageTools pageTools,
			@ModelAttribute("user") UserInfo user) throws Exception {
		logger.debug("loadPage WechatConsumeGroupRelation request:" + queryObject + " page info ===" + pageTools);

		queryObject.setPageTools(pageTools);
		ModelAndView mv = new ModelAndView(LOAD_PAGE_VIEW);
		Long size = wechatConsumeGroupRelationService.selectPageTotalCount(queryObject);
		pageTools.setTotal(size);
		List<WechatConsumeGroupRelation> ciList = wechatConsumeGroupRelationService.selectPageByObjectForList(queryObject);
		logger.debug("loadPage WechatConsumeGroupRelation result list info =====:" + ciList);
		List<ConsumeAcctGroup> consumeAcctGroupList = consumeAcctGroupService.selectPageByObjectForList(new ConsumeAcctGroup());
		Map<String,String> groupNameMap = new HashMap<String,String>();
		for(ConsumeAcctGroup group : consumeAcctGroupList){
			groupNameMap.put(group.getId().toString(), group.getGroupName());
		}
		
		mv.addObject("groupNameMap", groupNameMap);
		mv.addObject("resultList", ciList);
		mv.addObject("pageTools", pageTools);
		mv.addObject("queryObject", queryObject);

		return mv;
	}

	@RequestMapping(value="/wechatConsumeGroupRelationEdit")
	@ResponseBody
	public Map<String,Object> edit(WechatConsumeGroupRelation editData){
		logger.info("wechatConsumeGroupRelationEdit request:{}",editData);
		int num = wechatConsumeGroupRelationService.updateByPrimaryKeySelective(editData);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("success", true);
		map.put("editNumber", num);
		return map;
	}
	
	@RequestMapping(value="/wechatConsumeGroupRelationAdd")
	@ResponseBody
	public Map<String,Object> add(WechatConsumeGroupRelation addData) throws Exception{
		logger.info("wechatConsumeGroupRelationAdd request:{}",addData);
		addData.setCreatedDatetime(DateUtils.dateToTightStr(new Date()));
		int num = wechatConsumeGroupRelationService.insertSelective(addData);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("success", true);
		map.put("addNumber", num);
		return map;
	}
	
	@RequestMapping(value="/wechatConsumeGroupRelationDel")
	@ResponseBody
	public Map<String,Object> wechatConsumeGroupRelationDel(WechatConsumeGroupRelation record) throws Exception{
		logger.info("wechatConsumeGroupRelationDel request:{}",record);
		int num = wechatConsumeGroupRelationService.deleteByPrimaryKey(record);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("success", true);
		map.put("addNumber", num);
		return map;
	}
}
