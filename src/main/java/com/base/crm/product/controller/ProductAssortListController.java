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
import com.base.crm.product.entity.ProductAssortList;
import com.base.crm.product.service.ProductAssortListService;
import com.base.crm.users.entity.UserInfo;

@Controller
@RequestMapping(value = "/productAssortList")
@SessionAttributes("user")
public class ProductAssortListController {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private ProductAssortListService productAssortListService;
	
	private static final String PRIMARY_MODAL_VIEW = "page/productAssortList/ModifyModal";
	private static final String LOAD_PAGE_VIEW = "page/productAssortList/Content :: container-fluid";
	
	@RequestMapping(value = "/primaryModalView")
	public String primaryModalView(Long id, String modifyModel, Model model) throws Exception {
		logger.debug("primaryModalView request:" + id + ",model:" + model);
		if (id != null) {
			ProductAssortList result = productAssortListService.selectByPrimaryKey(id);
			model.addAttribute("modifyInfo", result);
		} 

		model.addAttribute("modifyModel", modifyModel);
		logger.debug("primaryModalView model : " + model);

		return PRIMARY_MODAL_VIEW;
	}

	@RequestMapping(value = "/pageView")
	@ResponseBody
	public Map<String, Object> pageView(ProductAssortList queryObject, PageTools pageTools,
			@ModelAttribute("user") UserInfo user) {
		logger.debug("adConsumetView request : " +queryObject);
		
		Long size = productAssortListService.selectPageTotalCount(queryObject);
		pageTools.setTotal(size);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("pageTools", pageTools);
		return result;
	}
	
	@RequestMapping(value = "/loadPage")
	public ModelAndView loadPage(ProductAssortList queryObject, PageTools pageTools,
			@ModelAttribute("user") UserInfo user) throws Exception {
		logger.debug("loadPage ProductAssortList request:" + queryObject + " page info ===" + pageTools);
		
		queryObject.setPageTools(pageTools);
		ModelAndView mv = new ModelAndView(LOAD_PAGE_VIEW);
		Long size = productAssortListService.selectPageTotalCount(queryObject);
		pageTools.setTotal(size);
		List<ProductAssortList> ciList = productAssortListService.selectPageByObjectForList(queryObject);
		logger.debug("loadPage ProductAssortList result list info =====:" + ciList);
		
		mv.addObject("resultList", ciList);
		mv.addObject("pageTools", pageTools);
		mv.addObject("queryObject", queryObject);

		return mv;
	}

	@RequestMapping(value="/productAssortListEdit")
	@ResponseBody
	public Map<String,Object> edit(ProductAssortList editData){
		logger.info("productAssortListEdit request:{}",editData);
		int num = productAssortListService.updateByPrimaryKeySelective(editData);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("success", true);
		map.put("editNumber", num);
		return map;
	}
	
	@RequestMapping(value="/productAssortListAdd")
	@ResponseBody
	public Map<String,Object> add(ProductAssortList addData) throws Exception{
		logger.info("productAssortListAdd request:{}",addData);
		addData.setCreatedDate(DateUtils.dateToStrLong(new Date()));
		int num = productAssortListService.insertSelective(addData);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("success", true);
		map.put("addNumber", num);
		return map;
	}
	
	@RequestMapping(value="/productAssortListDel")
	@ResponseBody
	public Map<String,Object> productAssortListDel(Long id) throws Exception{
		logger.info("productAssortListDel request:{}",id);
		int num = productAssortListService.deleteByPrimaryKey(id);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("success", true);
		map.put("addNumber", num);
		return map;
	}
}
