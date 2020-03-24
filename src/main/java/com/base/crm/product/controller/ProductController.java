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
import com.base.crm.product.entity.Product;
import com.base.crm.product.service.ProductService;
import com.base.crm.users.entity.UserInfo;

@Controller
@RequestMapping(value = "/product")
@SessionAttributes("user")
public class ProductController {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private ProductService productService;
	@Autowired
	private CommonConstants commonConstants;
	
	private static final String PRIMARY_MODAL_VIEW = "page/product/ModifyModal";
	private static final String LOAD_PAGE_VIEW = "page/product/Content :: container-fluid";
	
	@RequestMapping(value = "/primaryModalView")
	public String primaryModalView(Long id, String modifyModel, Model model) throws Exception {
		logger.debug("primaryModalView request:" + id + ",model:" + model);
		if (id != null) {
			Product result = productService.selectByPrimaryKey(id);
			model.addAttribute("modifyInfo", result);
		} 

		model.addAttribute("modifyModel", modifyModel);
		logger.debug("primaryModalView model : " + model);

		return PRIMARY_MODAL_VIEW;
	}

	@RequestMapping(value = "/pageView")
	@ResponseBody
	public Map<String, Object> pageView(Product queryObject, PageTools pageTools,
			@ModelAttribute("user") UserInfo user) {
		logger.debug("adConsumetView request : " +queryObject);
		
		Long size = productService.selectPageTotalCount(queryObject);
		pageTools.setTotal(size);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("pageTools", pageTools);
		return result;
	}
	
	@RequestMapping(value = "/loadPage")
	public ModelAndView loadPage(Product queryObject, PageTools pageTools,
			@ModelAttribute("user") UserInfo user) throws Exception {
		logger.debug("loadPage Product request:" + queryObject + " page info ===" + pageTools);
		
		queryObject.setPageTools(pageTools);
		ModelAndView mv = new ModelAndView(LOAD_PAGE_VIEW);
		Long size = productService.selectPageTotalCount(queryObject);
		pageTools.setTotal(size);
		List<Product> ciList = productService.selectPageByObjectForList(queryObject);
		logger.debug("loadPage Product result list info =====:" + ciList);
		
		mv.addObject("resultList", ciList);
		mv.addObject("pageTools", pageTools);
		mv.addObject("queryObject", queryObject);

		return mv;
	}

	@RequestMapping(value="/productEdit")
	@ResponseBody
	public Map<String,Object> edit(Product editData){
		logger.info("productEdit request:{}",editData);
		editData.setUpdatedDate(DateUtils.dateToStrLong(new Date()));
		int num = productService.updateByPrimaryKeySelective(editData);
		commonConstants.initProduct();
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("success", true);
		map.put("editNumber", num);
		return map;
	}
	
	@RequestMapping(value="/productAdd")
	@ResponseBody
	public Map<String,Object> add(Product addData) throws Exception{
		logger.info("productAdd request:{}",addData);
		addData.setCreatedDate(DateUtils.dateToStrLong(new Date()));
		int num = productService.insertSelective(addData);
		commonConstants.initProduct();
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("success", true);
		map.put("addNumber", num);
		return map;
	}
	
	@RequestMapping(value="/productDel")
	@ResponseBody
	public Map<String,Object> productDel(Long id) throws Exception{
		logger.info("productDel request:{}",id);
		int num = productService.deleteByPrimaryKey(id);
		commonConstants.initProduct();
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("success", true);
		map.put("addNumber", num);
		return map;
	}
}
