package com.base.crm.serve.wechat.controller;

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

import com.base.common.util.PageTools;
import com.base.crm.serve.wechat.entity.ServeWechat;
import com.base.crm.serve.wechat.service.ServeWechatService;
import com.base.crm.users.entity.UserInfo;

@Controller
@RequestMapping(value = "/serve/wechat")
@SessionAttributes("user")
public class ServeWechatController {
	private static final Logger logger = LoggerFactory.getLogger(ServeWechatController.class);
	@Autowired
	private ServeWechatService serveWechatService;

	@RequestMapping(value = "/primaryModalView")
	public String primaryModalView(Long id, String modifyModel, Model model) throws Exception {
		logger.debug("primaryModalView request:" + id + ",model:" + model);
		if (id != null) {
			ServeWechat serveWechat = serveWechatService.selectByPrimaryKey(id);
			model.addAttribute("modifyInfo", serveWechat);
		} 

		model.addAttribute("modifyModel", modifyModel);
		logger.debug("primaryModalView model : " + model);

		return "page/serve/wechat/ModifyModal";
	}

	@RequestMapping(value = "/serveWechatView")
	public ModelAndView serveWechatView(ServeWechat serveWechat, PageTools pageTools,
			@ModelAttribute("user") UserInfo user) {
		logger.debug("serveWechatView request : " +serveWechat);
		
		if (!user.isAdmin()) {
			serveWechat.setUserId(user.getuId());
		}
		Long size = serveWechatService.selectPageTotalCount(serveWechat);
		pageTools.setTotal(size);
		ModelAndView mv = new ModelAndView("page/serve/wechat/ServeWechatView");
		serveWechat.setPageTools(pageTools);
		mv.addObject("pageTools", pageTools);
		return mv;
	}

	@RequestMapping(value = "/loadPage")
	public ModelAndView loadPage(ServeWechat queryObject, PageTools pageTools,
			@ModelAttribute("user") UserInfo user) throws Exception {
		logger.debug("loadPage ServeWechat request:" + queryObject + " page info ===" + pageTools);
		
		if (!user.isAdmin()) {
			queryObject.setUserId(user.getuId());
		}

		queryObject.setPageTools(pageTools);
		ModelAndView mv = new ModelAndView("page/serve/wechat/Content :: container-fluid");
		Long size = serveWechatService.selectPageTotalCount(queryObject);
		pageTools.setTotal(size);
		List<ServeWechat> ciList = serveWechatService.selectPageByObjectForList(queryObject);
		logger.debug("loadPage ServeWechat result list info =====:" + ciList);
		
		mv.addObject("resultList", ciList);
		mv.addObject("pageTools", pageTools);
		mv.addObject("queryObject", queryObject);

		return mv;
	}

	@RequestMapping(value="/serveWechatEdit")
	@ResponseBody
	public Map<String,Object> serveWechatEdit(ServeWechat serveWechat){
		logger.info("serveWechatEdit request:{}",serveWechat);
		int num = serveWechatService.updateByPrimaryKeySelective(serveWechat);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("success", true);
		map.put("editNumber", num);
		return map;
	}
	
	@RequestMapping(value="/serveWechatAdd")
	@ResponseBody
	public Map<String,Object> serveWechatAdd(ServeWechat serveWechat) throws Exception{
		logger.info("serveWechatAdd request:{}",serveWechat);
		int num = serveWechatService.insertSelective(serveWechat);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("success", true);
		map.put("addNumber", num);
		return map;
	}
	
	
	@RequestMapping(value="/checkWechatNo")
	@ResponseBody
	public ServeWechat checkWechatNo(String wechatNo) throws Exception{
		logger.info("wechat request:"+wechatNo);
		ServeWechat serveWechat = serveWechatService.selectByPrimaryWechatNo(wechatNo);
		logger.info("serveWechat : "+serveWechat);
		return serveWechat;
		
	}
}
