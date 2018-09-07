package com.base.crm.orders.controller;

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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.base.common.util.ExcelMappingsAbstract;
import com.base.common.util.ExcelView;
import com.base.common.util.PageTools;
import com.base.crm.common.constants.OrderStatus;
import com.base.crm.customer.entity.CustInfo;
import com.base.crm.customer.service.CustInfoService;
import com.base.crm.orders.constants.OrderExcelMappings;
import com.base.crm.orders.entity.CustOrder;
import com.base.crm.orders.service.CustOrderService;
import com.base.crm.users.entity.UserInfo;

@Controller
@RequestMapping(value="/orders")
@SessionAttributes("user")
public class OrdersController {
	private static final Logger logger = LoggerFactory.getLogger(OrdersController.class);
	@Autowired
	private CustOrderService custOrderService;
	@Autowired
	private CustInfoService custInfoService;
//	@Autowired
//	private UserService userService;
	
	@RequestMapping(value="/ordersView")
	public ModelAndView ordersView(CustOrder order,PageTools pageTools,@ModelAttribute("user") UserInfo user){
		logger.info("ordersView request");
		if(!user.isAdmin()){
			order.setUserId(user.getuId());
		}
		Long size = custOrderService.selectPageTotalCount(order);
		pageTools.setTotal(size);
		ModelAndView mv = new ModelAndView("page/orders/OrdersView");
		order.setPageTools(pageTools);
		List<CustOrder> ciList = custOrderService.selectPageByObjectForList(order);
		mv.addObject("orderList", ciList);
		mv.addObject("pageTools", pageTools);
		return mv;
	}
	
	@RequestMapping(value="/orderEdit")
	@ResponseBody
	public Map<String,Object>  orderEdit(CustOrder order){
		logger.info("ordersView request");
		int num = custOrderService.updateByPrimaryKeySelective(order);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("success", true);
		map.put("editNumber", num);
		return map;
	}
	
	@RequestMapping(value="/orderAdd")
	@ResponseBody
	public Map<String,Object>  orderAdd(CustOrder order){
		logger.info("ordersView request:"+order);
		int num = custOrderService.insertSelective(order);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("success", true);
		map.put("addNumber", num);
		return map;
	}

	@RequestMapping(value="/loadPageOrders")
	public ModelAndView loadPageOrders(CustOrder order,PageTools pageTools,@ModelAttribute("user") UserInfo user) throws Exception{
		logger.info("loadPageOrders request:"+order +" page info ==="+pageTools);
		logger.info("loadPageOrders request user info =====:"+user);
		if(!user.isAdmin()){
			order.setUserId(user.getuId());
		}
		
		order.setPageTools(pageTools);
		ModelAndView mv = new ModelAndView("page/orders/Content :: container-fluid");
		Long size = custOrderService.selectPageTotalCount(order);
		pageTools.setTotal(size);
		List<CustOrder> ciList = custOrderService.selectPageByObjectForList(order);
		logger.info("loadPageOrders request list info =====:"+ciList);
		mv.addObject("orderList", ciList);
		mv.addObject("pageTools", pageTools);
		mv.addObject("queryOrder", order);
		
		return mv;
	}
	
	@RequestMapping(value="/primaryModalView")
	public String primaryModalView(Long id,String modifyModel,Model model) throws Exception{
		logger.info("primaryModalView request:"+id+",model:"+model);
		Map<Integer,String> orderStatusMap = new HashMap<Integer,String>();
		if(id!=null){
			CustOrder order = custOrderService.selectByPrimaryKey(id);
			model.addAttribute("modifyOrder", order);
			if(order.getOrderStatus()==0){
				orderStatusMap.put(0, OrderStatus.NON_DELIVERY.toString());
				orderStatusMap.put(4,  OrderStatus.INVALIDATED.toString());
			}else if(order.getOrderStatus()==1){
				orderStatusMap.put(1,  OrderStatus.DELIVERING.toString());
				orderStatusMap.put(2, OrderStatus.SIGNED.toString());
				orderStatusMap.put(3, OrderStatus.REFUSED.toString());
			}
		}else{ 
			orderStatusMap.put(0, OrderStatus.NON_DELIVERY.toString());
		}
		
		model.addAttribute("orderStatusMap", orderStatusMap);
		model.addAttribute("modifyModel", modifyModel);
		logger.info("model : "+model);
		
		return "page/orders/ModifyModal";
	}
	
	@RequestMapping("/orderExport")
    public ModelAndView orderExport(CustOrder order){
		logger.info("orderExport request:"+order);

        List<CustOrder> data = custOrderService.selectByObjectForList(order);
        JSONArray jsonObj = JSON.parseArray(JSON.toJSONString(data));
        logger.info("order========"+jsonObj);
        
        ExcelMappingsAbstract mapping = new OrderExcelMappings(jsonObj);
        
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("ExcelMappings", mapping);

        return new ModelAndView(new ExcelView(), map);
    }
}
