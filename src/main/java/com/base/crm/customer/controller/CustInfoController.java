package com.base.crm.customer.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.expression.Dates;

import com.base.common.util.DateUtils;
import com.base.common.util.PageTools;
import com.base.crm.ad.entity.ADAcctType;
import com.base.crm.ad.service.ADAcctTypeService;
import com.base.crm.consume.entity.CustomerConsume;
import com.base.crm.consume.service.CustomerConsumeService;
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
	@Autowired
	private CustomerConsumeService customerConsumeService;
	@Autowired
	private ADAcctTypeService adAcctTypeService;
	
	@RequestMapping(value = "/pageView")
	@ResponseBody
	public Map<String,Object> pageView(CustInfo ci,PageTools pageTools,@ModelAttribute("user") UserInfo user){
		logger.info("pageView request");
		if(!user.isAdmin()){
			ci.setUserId(user.getuId());
		}
		
		if(ci.getAddTime()==null&&user.isAdmin()){
			ci.setAddTime(DateUtils.getStringDateShort());
		}
		
		ci.setPageTools(pageTools);
		Long size = custInfoService.selectPageTotalCount(ci);
		pageTools.setTotal(size);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("pageTools", pageTools);
		return result;
	}
	
	@RequestMapping(value="/custInfoEdit")
	@ResponseBody
	public Map<String,Object> custInfoEdit(CustInfo custInfo){
		custInfo.setAddDate(custInfo.getAddTime().substring(0,10).replaceAll("-", ""));
		logger.info("custInfoEdit request");
		custInfo.setAdAcctId(queryAcctTypeId(custInfo));
		int num = custInfoService.updateByPrimaryKeySelective(custInfo);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("success", true);
		map.put("editNumber", num);
		return map;
	}
	
	private Long queryAcctTypeId(CustInfo custInfo){
		ADAcctType acctType = adAcctTypeService.selectAdAcctIdByWechatNo(custInfo.getServeWechatNo());
		return acctType==null?null:acctType.getId();
	}
	
	@RequestMapping(value="/custInfoRecharge")
	@ResponseBody
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public Map<String,Object> custInfoRecharge(CustomerConsume consume){
		logger.info("custInfoRecharge request:{}",consume);
		 
		consume.setConsumeDate(new Dates(Locale.ROOT).format(new Date(), "yyyyMMdd"));
		if(consume.getConsumeType() == 1){
			consume.setRemark(String.format("微信号[%s]的会员充值:%s元",consume.getWechatNo(), consume.getAmount()));
		}else {
			consume.setRemark(String.format("微信号[%s]的赠送余额:%s元",consume.getWechatNo(), consume.getAmount()));
		}
		logger.info(consume.getRemark());
		customerConsumeService.insertSelective(consume);
		
		CustInfo custInfo = new CustInfo();
		custInfo.setCustWechatNo(consume.getWechatNo());
		custInfo.setAmt(consume.getAmount().doubleValue());
		
		int num = custInfoService.updateByPrimaryKeySelective(custInfo);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("success", true);
		map.put("editNumber", num);
		return map;
	}
	
	
	@RequestMapping(value="/custInfoAdd")
	@ResponseBody
	public Map<String,Object> custInfoAdd(CustInfo custInfo) throws Exception{
		custInfo.setAddDate(custInfo.getAddTime().substring(0,10).replaceAll("-", ""));
		logger.info("custInfoAdd request:"+custInfo);
		custInfo.setAdAcctId(queryAcctTypeId(custInfo));
		int num = custInfoService.insertSelective(custInfo);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("success", true);
		map.put("addNumber", num);
		return map;
	}
	
	@RequestMapping(value="/loadPage")
	public ModelAndView loadPage(CustInfo ci,PageTools pageTools,@ModelAttribute("user") UserInfo user) throws Exception{
		logger.info("loadPage request:"+ci +" page info ==="+pageTools);
		logger.info("loadPage request: user =="+user);
		if(!user.isAdmin()){
			ci.setSearchUser(user.getuId());
		}
		if(ci.getAddTime()==null&&user.isAdmin()){
			ci.setAddTime(DateUtils.getStringDateShort());
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
		if(modifyModel.equals("custInfoRecharge")){
			return "page/customer/ModifyModalRecharge";
		}
		return "page/customer/ModifyModal";
	}
	
	@RequestMapping(value="/checkWechatNo")
	@ResponseBody
	public CustInfo checkWechatNo(String oWechatNo) throws Exception{
		logger.info("checkWechatNo request:"+oWechatNo);
		CustInfo cust = custInfoService.selectByPrimaryWechatNo(oWechatNo);
		logger.info("customer : "+cust);
		return cust;
		
	}
}
