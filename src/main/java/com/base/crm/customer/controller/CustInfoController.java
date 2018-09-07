package com.base.crm.customer.controller;

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
import com.base.crm.customer.entity.CustInfo;
import com.base.crm.customer.service.CustInfoService;
import com.base.crm.users.entity.UserInfo;

@Controller
@RequestMapping(value="/customer")
@SessionAttributes("user")
public class CustInfoController {
	private static final Logger logger = LoggerFactory.getLogger(CustInfoController.class);
	@Autowired
	private CustInfoService custInfoService;
	private static Map<String,String> wechatMap = new HashMap<String,String>();
	
	@RequestMapping(value="/custInfoView")
	public ModelAndView custInfoView(CustInfo ci,PageTools pageTools,@ModelAttribute("user") UserInfo user){
		logger.info("custInfoView request");
		if(!user.isAdmin()){
			ci.setUserId(user.getuId());
		}
		ci.setPageTools(pageTools);
		Long size = custInfoService.selectPageTotalCount(ci);
		pageTools.setTotal(size);
		ModelAndView mv = new ModelAndView("page/customer/CustInfoView");
		List<CustInfo> ciList = custInfoService.selectByObjectForList(ci);
		mv.addObject("custInfoList", ciList);
		mv.addObject("pageTools", pageTools);
		
		return mv;
	}
	
	@RequestMapping(value="/custInfoEdit")
	@ResponseBody
	public Map<String,Object> custInfoEdit(CustInfo custInfo){
		logger.info("custInfoEdit request");
		int num = custInfoService.updateByPrimaryKeySelective(custInfo);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("success", true);
		map.put("editNumber", num);
		return map;
	}
	
	@RequestMapping(value="/custInfoAdd")
	@ResponseBody
	public Map<String,Object> custInfoAdd(CustInfo custInfo) throws Exception{
		logger.info("custInfoAdd request:"+custInfo);
		int num = custInfoService.insertSelective(custInfo);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("success", true);
		map.put("addNumber", num);
		return map;
	}
	
	@RequestMapping(value="/loadPageCustInfo")
	public ModelAndView loadPageCustInfo(CustInfo ci,PageTools pageTools,@ModelAttribute("user") UserInfo user) throws Exception{
		logger.info("loadPageCustInfo request:"+ci +" page info ==="+pageTools);
		logger.info("loadPageCustInfo request: user =="+user);
		if(!user.isAdmin()){
			ci.setUserId(user.getuId());
		}
		ci.setPageTools(pageTools);
		ModelAndView mv = new ModelAndView("page/customer/Content :: cust-info-content");
		Long size = custInfoService.selectPageTotalCount(ci);
		pageTools.setTotal(size);
		
		List<CustInfo> ciList = custInfoService.selectByObjectForList(ci);
		mv.addObject("custInfoList", ciList);
		mv.addObject("pageTools", pageTools);
		
		return mv;
	}
	
	@RequestMapping(value="/primaryCustInfoModalView")
	public String primaryCustInfoModalView(Long id,String modifyModel,Model model) throws Exception{
		logger.info("primaryCustInfoModalView request:"+id+",model:"+model);
		if(id!=null){
			CustInfo ci = custInfoService.selectByPrimaryKey(id);
			model.addAttribute("custInfo", ci);
		}
		model.addAttribute("modifyModel", modifyModel);
		logger.info("model : "+model);
		
		return "page/customer/ModifyModal";
	}
	
	@RequestMapping(value="/checkWechatNo")
	@ResponseBody
	public CustInfo checkWechatNo(String oWechatNo) throws Exception{
		logger.info("checkWechatNo request:"+oWechatNo);
		return custInfoService.selectByPrimaryWechatNo(oWechatNo);
	}
}
