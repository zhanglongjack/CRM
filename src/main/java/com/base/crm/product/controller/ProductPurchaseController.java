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
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.base.common.util.DateUtils;
import com.base.common.util.PageTools;
import com.base.crm.product.entity.ProductPurchase;
import com.base.crm.product.service.ProductPurchaseService;
import com.base.crm.users.entity.UserInfo;

@Controller
@RequestMapping(value = "/productPurchase")
@SessionAttributes("user")
public class ProductPurchaseController {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private ProductPurchaseService productPurchaseService;
	
	private static final String PRIMARY_MODAL_VIEW = "page/productPurchase/ModifyModal";
	private static final String LOAD_PAGE_VIEW = "page/productPurchase/Content :: container-fluid";
	
	@RequestMapping(value = "/primaryModalView")
	public String primaryModalView(Long id, String modifyModel, Model model) throws Exception {
		logger.debug("primaryModalView request:" + id + ",model:" + model);
		if (id != null) {
			ProductPurchase result = productPurchaseService.selectByPrimaryKey(id);
			model.addAttribute("modifyInfo", result);
		} 

		model.addAttribute("modifyModel", modifyModel);
		logger.debug("primaryModalView model : " + model);

		return PRIMARY_MODAL_VIEW;
	}

	@RequestMapping(value = "/pageView")
	@ResponseBody
	public Map<String, Object> pageView(ProductPurchase queryObject, PageTools pageTools,
			@ModelAttribute("user") UserInfo user) {
		logger.debug("adConsumetView request : " +queryObject);
		
		Assert.isTrue(user.isAdmin(), "非管理员不允许查询"); 
		
		Long size = productPurchaseService.selectPageTotalCount(queryObject);
		pageTools.setTotal(size);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("pageTools", pageTools);
		return result;
	}
	
	@RequestMapping(value = "/loadPage")
	public ModelAndView loadPage(ProductPurchase queryObject, PageTools pageTools,
			@ModelAttribute("user") UserInfo user) throws Exception {
		logger.debug("loadPage ProductPurchase request:" + queryObject + " page info ===" + pageTools);
		
		Assert.isTrue(user.isAdmin(), "非管理员不允许查询"); 
		
		queryObject.setPageTools(pageTools);
		ModelAndView mv = new ModelAndView(LOAD_PAGE_VIEW);
		Long size = productPurchaseService.selectPageTotalCount(queryObject);
		pageTools.setTotal(size);
		List<ProductPurchase> ciList = productPurchaseService.selectPageByObjectForList(queryObject);
		logger.debug("loadPage ProductPurchase result list info =====:" + ciList);
		
		mv.addObject("resultList", ciList);
		mv.addObject("pageTools", pageTools);
		mv.addObject("queryObject", queryObject);

		return mv;
	}

	@RequestMapping(value="/productPurchaseEdit")
	@ResponseBody
	public Map<String,Object> edit(ProductPurchase editData){
		logger.info("productPurchaseEdit request:{}",editData);
		Map<String,Object> map = new HashMap<String,Object>();
		editData.setUpdatedDate(DateUtils.dateToStrLong(new Date()));
		
		int num = productPurchaseService.doUpdate(editData);
		map.put("editNumber", num);
		map.put("success", true);
		
		return map;
	}
	
	@RequestMapping(value="/productPurchaseAdd")
	@ResponseBody
	public Map<String,Object> add(ProductPurchase addData) throws Exception{
		logger.info("productPurchaseAdd request:{}",addData);
		addData.setCreatedDate(DateUtils.dateToStrLong(new Date()));
		int num = productPurchaseService.insertSelective(addData);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("success", true);
		map.put("addNumber", num);
		return map;
	}
	
	@RequestMapping(value="/productPurchaseDel")
	@ResponseBody
	public Map<String,Object> productPurchaseDel(Long id) throws Exception{
		logger.info("productPurchaseDel request:{}",id);
		int num = productPurchaseService.deleteByPrimaryKey(id);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("success", true);
		map.put("addNumber", num);
		return map;
	}
}
