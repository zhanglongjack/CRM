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
import com.base.crm.product.entity.ProductStock;
import com.base.crm.product.service.ProductStockService;
import com.base.crm.users.entity.UserInfo;

@Controller
@RequestMapping(value = "/productStock")
@SessionAttributes("user")
public class ProductStockController {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private ProductStockService productStockService;
	@Autowired
	private CommonConstants commonConstants;
	
	private static final String PRIMARY_MODAL_VIEW = "page/productStock/ModifyModal";
	private static final String LOAD_PAGE_VIEW = "page/productStock/Content :: container-fluid";
	
	@RequestMapping(value = "/primaryModalView")
	public String primaryModalView(Long id, String modifyModel, Model model) throws Exception {
		logger.debug("primaryModalView request:" + id + ",model:" + model);
		if (id != null) {
			ProductStock result = productStockService.selectByPrimaryKey(id);
			model.addAttribute("modifyInfo", result);
		} 

		model.addAttribute("modifyModel", modifyModel);
		logger.debug("primaryModalView model : " + model);

		return PRIMARY_MODAL_VIEW;
	}

	@RequestMapping(value = "/pageView")
	@ResponseBody
	public Map<String, Object> pageView(ProductStock queryObject, PageTools pageTools,
			@ModelAttribute("user") UserInfo user) {
		logger.debug("adConsumetView request : " +queryObject);
		
		Long size = productStockService.selectPageTotalCount(queryObject);
		pageTools.setTotal(size);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("pageTools", pageTools);
		return result;
	}
	
	@RequestMapping(value = "/loadPage")
	public ModelAndView loadPage(ProductStock queryObject, PageTools pageTools,
			@ModelAttribute("user") UserInfo user) throws Exception {
		logger.debug("loadPage ProductStock request:" + queryObject + " page info ===" + pageTools);
		
		queryObject.setPageTools(pageTools);
		ModelAndView mv = new ModelAndView(LOAD_PAGE_VIEW);
		Long size = productStockService.selectPageTotalCount(queryObject);
		pageTools.setTotal(size);
		List<ProductStock> ciList = productStockService.selectPageByObjectForList(queryObject);
		logger.debug("loadPage ProductStock result list info =====:" + ciList);
		
		mv.addObject("resultList", ciList);
		mv.addObject("pageTools", pageTools);
		mv.addObject("queryObject", queryObject);

		return mv;
	}

	@RequestMapping(value="/productStockEdit")
	@ResponseBody
	public Map<String,Object> edit(ProductStock editData){
		logger.info("productStockEdit request:{}",editData);
		editData.setUpdatedDate(DateUtils.dateToStrLong(new Date()));
		int num = productStockService.updateByPrimaryKeySelective(editData);
		commonConstants.initProduct();
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("success", true);
		map.put("editNumber", num);
		return map;
	}
	
	@RequestMapping(value="/productStockAdd")
	@ResponseBody
	public Map<String,Object> add(ProductStock addData) throws Exception{
		logger.info("productStockAdd request:{}",addData);
		addData.setCreatedDate(DateUtils.dateToStrLong(new Date()));
		int num = productStockService.insertSelective(addData);
		commonConstants.initProduct();
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("success", true);
		map.put("addNumber", num);
		return map;
	}
	
	@RequestMapping(value="/productStockDel")
	@ResponseBody
	public Map<String,Object> productStockDel(Long id) throws Exception{
		logger.info("productStockDel request:{}",id);
		int num = productStockService.deleteByPrimaryKey(id);
		commonConstants.initProduct();
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("success", true);
		map.put("addNumber", num);
		return map;
	}
}
