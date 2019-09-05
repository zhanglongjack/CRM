package com.base.crm.common.controller;

import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.base.common.util.DateUtils;
import com.base.crm.customer.service.CustInfoService;
import com.base.crm.extension.check.ExtensionStatusCheckData;
import com.base.crm.orders.service.CustOrderService;
import com.base.crm.users.entity.UserInfo;

@Controller
@SessionAttributes("user")
public class IndexController {
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
	
	@Autowired
	private CustInfoService custInfoService;
	@Autowired
	private CustOrderService orderService;
	@Autowired
	private ExtensionStatusCheckData data;
	
	@RequestMapping(value="/index")
	public ModelAndView index(@ModelAttribute("user") UserInfo user){
		logger.info("index request");
		String yearMonthShort = DateUtils.dateToTightStr(new Date()).substring(0, 6);
		String yearMonthLong = DateUtils.dateToStr(new Date()).substring(0, 7);
		Map<String,Integer> daysCountMap = custInfoService.selectCustCountByMonth(yearMonthLong,user.isAdmin()?null:user.getuId());
		Map<String,Integer> orderList = orderService.selectOrderCountByMonth(yearMonthShort,user.isAdmin()?null:user.getuId());
		
		ModelAndView mv = new ModelAndView("page/index");
		mv.addObject("date", new Date());
		mv.addObject("currentDayCount", daysCountMap.get("currentCount"));
		mv.addObject("monthCustCount", daysCountMap.get("monthCustCount"));
		mv.addObject("yesterdayCount", daysCountMap.get("yesterdayCount"));
		
		mv.addObject("sumAmount", orderList.get("sumAmount"));
		mv.addObject("sumOrders", orderList.get("sumOrders"));
		
		mv.addObject("yesterdayOrdCount", orderList.get("yesterdayOrdCount"));
		mv.addObject("currentOrderCount", orderList.get("currentCount"));
		
		mv.addObject("yesterdayAmount", orderList.get("yesterdayAmount"));
		mv.addObject("currentAmount", orderList.get("currentAmount"));
		
		mv.addObject("netWorkCheckList", data.getData());
		logger.info("index response order count ==="+daysCountMap);
		logger.info("index response==="+mv);
		return mv;
	}
	
	@RequestMapping(value="/home")
	public ModelAndView home(){
		logger.info("home request");
		ModelAndView mv = new ModelAndView("page/Home");
		mv.addObject("test", "hello world !!!home");
		mv.addObject("date", new Date());
//		mv.setViewName("forward:/login.html");
		return mv;
	}
}
