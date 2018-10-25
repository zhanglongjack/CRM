package com.base.crm.sign.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.DateUtils;

import com.base.common.util.DateFormateType;
import com.base.common.util.PageTools;
import com.base.crm.sign.entity.UserSign;
import com.base.crm.sign.service.UserSignService;
import com.base.crm.users.entity.UserInfo;

@Controller
@RequestMapping(value = "/sign")
@SessionAttributes("user")
public class UserSignController {
	private static final Logger logger = LoggerFactory.getLogger(UserSignController.class);
	@Autowired
	private UserSignService userSignService;

	@RequestMapping(value = "/isSign")
	@ResponseBody
	public Map<String, Object> isSign(Long userId) throws Exception {
		logger.info("isSign request:{}",userId);
		UserSign us = new UserSign();
		us.setUserId(userId);
		us.setSignDate(DateUtils.format(new Date(), DateFormateType.TIGHT_SHORT_FORMAT, Locale.getDefault()));
		UserSign result = userSignService.selectSimpleObjectBy(us);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("success", result !=null);
		return map;

	}

	@RequestMapping(value = "/signView")
	public ModelAndView signView(UserSign paramObj, PageTools pageTools,
			@ModelAttribute("user") UserInfo user) {
		logger.debug("signView request : " +paramObj);
		
		if (!user.isAdmin()) {
			paramObj.setUserId(user.getuId());
		}
		Long size = userSignService.selectPageTotalCount(paramObj);
		pageTools.setTotal(size);
		ModelAndView mv = new ModelAndView("page/sign/SignView");
		paramObj.setPageTools(pageTools);
		mv.addObject("pageTools", pageTools);
		return mv;
	}

	@RequestMapping(value = "/loadPage")
	public ModelAndView loadPage(UserSign queryObject, PageTools pageTools,
			@ModelAttribute("user") UserInfo user) throws Exception {
		logger.debug("loadPage UserSign request:" + queryObject + " page info ===" + pageTools);
		
		if (!user.isAdmin()) {
			queryObject.setUserId(user.getuId());
		}

		queryObject.setPageTools(pageTools);
		ModelAndView mv = new ModelAndView("page/sign/Content :: container-fluid");
		Long size = userSignService.selectPageTotalCount(queryObject);
		pageTools.setTotal(size);
		List<UserSign> resultList = userSignService.selectPageByObjectForList(queryObject);
		logger.debug("loadPage UserSign result list info =====:" + resultList);
		
		mv.addObject("resultList", resultList);
		mv.addObject("pageTools", pageTools);
		mv.addObject("queryObject", queryObject);

		return mv;
	}

	@RequestMapping(value="/signEdit")
	@ResponseBody
	public Map<String,Object> signEdit(UserSign paramObj){
		logger.info("signEdit request:{}",paramObj);
		int num = userSignService.updateByPrimaryKeySelective(paramObj);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("success", true);
		map.put("editNumber", num);
		return map;
	}
	
	@RequestMapping(value="/signAdd")
	@ResponseBody
	public Map<String,Object> signAdd(UserSign paramObj,@ModelAttribute("user") UserInfo user) throws Exception{
		logger.info("signAdd request:{}",paramObj);
		paramObj.setUserId(user.getuId());
		paramObj.setUserName(user.getName());
		paramObj.setSignDate(DateUtils.format(new Date(), DateFormateType.TIGHT_SHORT_FORMAT, Locale.getDefault()));
		paramObj.setSignTime(DateUtils.format(new Date(), DateFormateType.TIGHT_TIME_FORMAT, Locale.getDefault()));
		int num = userSignService.insertSelective(paramObj);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("success", true);
		map.put("addNumber", num);
		return map;
	}
	
	@RequestMapping(value="/signDel")
	@ResponseBody
	public Map<String,Object> signDel(Long id,@ModelAttribute("user") UserInfo user) throws Exception{
		logger.info("signDel request:{}",id);
		userSignService.deleteByPrimaryKey(id);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("success", true);
		return map;
	}
	
}
