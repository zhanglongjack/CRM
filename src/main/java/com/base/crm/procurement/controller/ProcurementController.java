package com.base.crm.procurement.controller;

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
import com.base.crm.procurement.entity.ProcurementCosts;
import com.base.crm.procurement.service.ProcurementCostService;
import com.base.crm.users.entity.UserInfo;


@Controller
@RequestMapping(value = "/procurement")
@SessionAttributes("user")
public class ProcurementController {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private ProcurementCostService procurementCostService;

	@RequestMapping(value = "/primaryModalView")
	public String primaryModalView(Long id, String modifyModel, Model model) throws Exception {
		logger.debug("primaryModalView request:" + id + ",model:" + model);
		if (id != null) {
			ProcurementCosts procurementCosts = procurementCostService.selectByPrimaryKey(id);
			model.addAttribute("modifyInfo", procurementCosts);
		} 

		model.addAttribute("modifyModel", modifyModel);
		logger.debug("primaryModalView model : " + model);

		return "page/procurement/ModifyModal";
	}

	@RequestMapping(value = "/pageView")
	@ResponseBody
	public Map<String, Object> view(ProcurementCosts queryObject, PageTools pageTools,
			@ModelAttribute("user") UserInfo user) {
		logger.debug("procurementView request : " +queryObject);
		
		Assert.isTrue(user.isAdmin(), "非管理员不允许查询"); 
		
		Long size = procurementCostService.selectPageTotalCount(queryObject);
		pageTools.setTotal(size);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("pageTools", pageTools);
		return result;
	}

	@RequestMapping(value = "/loadPage")
	public ModelAndView loadPage(ProcurementCosts queryObject, PageTools pageTools,
			@ModelAttribute("user") UserInfo user) throws Exception {
		logger.debug("loadPage ProcurementCosts request:" + queryObject + " page info ===" + pageTools);
		
		Assert.isTrue(user.isAdmin(), "非管理员不允许查询"); 
		
		queryObject.setPageTools(pageTools);
		
		Long size = procurementCostService.selectPageTotalCount(queryObject);
		pageTools.setTotal(size);
		List<ProcurementCosts> resultList = procurementCostService.selectPageByObjectForList(queryObject);
		logger.debug("loadPage ProcurementCosts result list info =====:" + resultList);
		
		ModelAndView mv = new ModelAndView("page/procurement/Content :: container-fluid");
		mv.addObject("resultList", resultList);
		mv.addObject("pageTools", pageTools);
		mv.addObject("queryObject", queryObject);
		
		logger.debug("queryObject =====:" + mv);
		return mv;
	}

	@RequestMapping(value="/procurementEdit")
	@ResponseBody
	public Map<String,Object> edit(ProcurementCosts editData){
		logger.info("procurementEdit request:{}",editData);
		int num = procurementCostService.updateByPrimaryKeySelective(editData);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("success", true);
		map.put("editNumber", num);
		return map;
	}
	
	@RequestMapping(value="/procurementAdd")
	@ResponseBody
	public Map<String,Object> add(ProcurementCosts addData) throws Exception{
		logger.info("procurementAdd request:{}",addData);
		int num = procurementCostService.insertSelective(addData);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("success", true);
		map.put("addNumber", num);
		return map;
	}
	
	 
}
