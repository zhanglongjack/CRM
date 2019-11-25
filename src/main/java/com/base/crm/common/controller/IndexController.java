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
		mv.addObject("currentDayCount", daysCountMap.get("currentCount")); 	// 当日粉丝数
		mv.addObject("currentOrderCount", orderList.get("currentCount"));	// 当日订单总数
		mv.addObject("currentAmount", orderList.get("currentAmount"));		// 当日业绩总额
		
		mv.addObject("yesterdayCount", daysCountMap.get("yesterdayCount"));		// 昨日粉丝数
		mv.addObject("yesterdayOrdCount", orderList.get("yesterdayOrdCount")); 	// 昨日订单总数
		mv.addObject("yesterdayAmount", orderList.get("yesterdayAmount"));		// 昨日业绩总额
		
		
		mv.addObject("monthCustCount", daysCountMap.get("monthCustCount"));	// 当月粉丝数
		mv.addObject("handselAmount", orderList.get("handselAmount")); 		// 当月定金总额
		mv.addObject("sumAmount", orderList.get("sumAmount")); 				// 当月业绩总额
		mv.addObject("sumOrders", orderList.get("sumOrders"));				// 当月订单总数
		
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
