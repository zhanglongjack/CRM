package com.base.common.dictionary.controller;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.base.common.dictionary.entity.Dictionary;
import com.base.common.dictionary.service.DictionaryService;
import com.base.common.util.PageTools;
import com.base.crm.users.entity.UserInfo;
 
@Controller
@RequestMapping(value = "/dictionary")
@SessionAttributes("user")
public class DictionaryController {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private DictionaryService dictionaryService;
    
	@RequestMapping(value = "/primaryModalView.ser")
	public String primaryModalView(Long id, String modifyModel, Model model) throws Exception {
		logger.debug("primaryModalView request:" + id + ",model:" + model);
		if (id != null) {
			Dictionary dictionary = dictionaryService.selectByPrimaryKey(id);
			model.addAttribute("modifyInfo", dictionary);
		} 
 
		model.addAttribute("modifyModel", modifyModel);
		logger.debug("primaryModalView model : " + model);

		return "page/dictionary/ModifyModal";
	}

	@RequestMapping(value = "/pageView.ser")
	@ResponseBody
	public Map<String, Object> pageView(Dictionary dictionary, PageTools pageTools,
			@ModelAttribute("user") UserInfo user) {
		logger.debug("dictionaryView request : " +user);
		
		Long size = dictionaryService.selectPageTotalCount(dictionary);
		pageTools.setTotal(size);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("pageTools", pageTools);
		return result;
	}

	@RequestMapping(value = "/loadPage.ser")
	public ModelAndView loadPage(Dictionary dictionary, PageTools pageTools,
			@ModelAttribute("user") UserInfo user) throws Exception {
		logger.debug("loadPage Dictionary request:" + dictionary + " page info ===" + pageTools);
		ModelAndView mv = new ModelAndView("page/dictionary/Content :: container-fluid");
		return mv;
	}
	
	@RequestMapping(value = "/loadPageAjax.ser")
	@ResponseBody
	public Map<String,Object> loadPage(Dictionary dictionary, @ModelAttribute("user") UserInfo user) throws Exception {
		logger.debug("loadPage Dictionary request:{}" , dictionary );
		
		Long size = dictionaryService.selectPageTotalCount(dictionary);
		List<Dictionary> ciList = dictionaryService.selectPageByObjectForList(dictionary);
		logger.debug("loadPage Dictionary result list info =====:" + ciList);
		
		Map<String,Object> mv = new HashMap<String,Object>();
		mv.put("rows", ciList);
		mv.put("total", size);
		return mv;
	}

	@RequestMapping(value="/dictionaryEdit.ser")
	@ResponseBody
	public Map<String,Object> dictionaryEdit(Dictionary dictionary){
		logger.info("dictionaryEdit request:{}",dictionary);
		dictionaryService.updateByPrimaryKeySelectiveIncludeCache(dictionary);
		Map<String,Object> mv = new HashMap<String,Object>();
		
		
		return mv;
	}

	@RequestMapping(value = "/dictionaryList.req",method= RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> dictionaryList(String bizCode,boolean cache) throws Exception{
		logger.info("DictionaryController request dictionaryList bizCode ==  "+bizCode+"|cache ="+cache);
		Map<String, Object> result  = dictionaryService.dictionaryList(bizCode,cache);
		
		return result;
	}

}
