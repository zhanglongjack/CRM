package com.base.crm.product.controller;


import java.util.Date;
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

import com.base.common.util.DateUtils;
import com.base.common.util.PageTools;
import com.base.crm.common.constants.CommonConstants;
import com.base.crm.product.entity.ProductAssort;
import com.base.crm.product.service.ProductAssortService;
import com.base.crm.users.entity.UserInfo;

@Controller
@RequestMapping(value = "/productAssort")
@SessionAttributes("user")
public class ProductAssortController {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private ProductAssortService productAssortService;
	@Autowired
	private CommonConstants commonConstants;
	
	private static final String PRIMARY_MODAL_VIEW = "page/productAssort/ModifyModal";
	private static final String LOAD_PAGE_VIEW = "page/productAssort/Content :: container-fluid";
	
	@RequestMapping(value = "/primaryModalView")
	public String primaryModalView(Long id, String modifyModel, Model model) throws Exception {
		logger.debug("primaryModalView request:" + id + ",model:" + model);
		if (id != null) {
			ProductAssort result = productAssortService.selectByPrimaryKey(id);
			model.addAttribute("modifyInfo", result);
		} 

		model.addAttribute("modifyModel", modifyModel);
		logger.debug("primaryModalView model : " + model);

		return PRIMARY_MODAL_VIEW;
	}

	@RequestMapping(value = "/pageView")
	@ResponseBody
	public Map<String, Object> pageView(ProductAssort queryObject, PageTools pageTools,
			@ModelAttribute("user") UserInfo user) {
		logger.debug("adConsumetView request : " +queryObject);
		
		Long size = productAssortService.selectPageTotalCount(queryObject);
		pageTools.setTotal(size);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("pageTools", pageTools);
		return result;
	}
	
	@RequestMapping(value = "/loadPage")
	public ModelAndView loadPage(ProductAssort queryObject, PageTools pageTools,
			@ModelAttribute("user") UserInfo user) throws Exception {
		logger.debug("loadPage ProductAssort request:" + queryObject + " page info ===" + pageTools);
		
		queryObject.setPageTools(pageTools);
		ModelAndView mv = new ModelAndView(LOAD_PAGE_VIEW);
		Long size = productAssortService.selectPageTotalCount(queryObject);
		pageTools.setTotal(size);
		List<ProductAssort> ciList = productAssortService.selectPageByObjectForList(queryObject);
		logger.debug("loadPage ProductAssort result list info =====:" + ciList);
		
		mv.addObject("resultList", ciList);
		mv.addObject("pageTools", pageTools);
		mv.addObject("queryObject", queryObject);

		return mv;
	}

	@RequestMapping(value="/productAssortEdit")
	@ResponseBody
	public Map<String,Object> edit(ProductAssort editData){
		logger.info("productAssortEdit request:{}",editData);
		editData.setUpdatedDate(DateUtils.dateToStrLong(new Date()));
		int num = productAssortService.updateByPrimaryKeySelective(editData);
		commonConstants.initAssort();
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("success", true);
		map.put("editNumber", num);
		return map;
	}
	
	@RequestMapping(value="/productAssortAdd")
	@ResponseBody
	public Map<String,Object> add(ProductAssort addData) throws Exception{
		logger.info("productAssortAdd request:{}",addData);
		addData.setCreatedDate(DateUtils.dateToStrLong(new Date()));
		int num = productAssortService.insertSelective(addData);
		commonConstants.initAssort();
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("success", true);
		map.put("addNumber", num);
		return map;
	}
	
	@RequestMapping(value="/productAssortDel")
	@ResponseBody
	public Map<String,Object> productAssortDel(Long id) throws Exception{
		logger.info("productAssortDel request:{}",id);
		int num = productAssortService.deleteByPrimaryKey(id);
		commonConstants.initAssort();
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("success", true);
		map.put("addNumber", num);
		return map;
	}
}
