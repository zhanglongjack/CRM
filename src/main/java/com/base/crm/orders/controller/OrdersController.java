package com.base.crm.orders.controller;

import java.math.BigDecimal;
import java.nio.file.Files;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.expression.Dates;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.base.common.util.ExcelMappingsAbstract;
import com.base.common.util.ExcelView;
import com.base.common.util.PageTools;
import com.base.crm.common.constants.OrderStatus;
import com.base.crm.consume.entity.CustomerConsume;
import com.base.crm.consume.service.CustomerConsumeService;
import com.base.crm.customer.entity.CustInfo;
import com.base.crm.customer.service.CustInfoService;
import com.base.crm.orders.constants.OrderExcelMappings;
import com.base.crm.orders.entity.CustOrder;
import com.base.crm.orders.service.CustOrderService;
import com.base.crm.orders.utils.OrderExcelImport;
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
	@Autowired
	private CustomerConsumeService customerConsumeService;
	@Autowired
	private OrderExcelMappings orderExcelMappings;
	
	@RequestMapping(value = "/pageView")
	@ResponseBody
	public Map<String, Object> ordersView(CustOrder order,PageTools pageTools,@ModelAttribute("user") UserInfo user){
		logger.info("ordersView request:"+order);
		if(!user.isAdmin()){
			order.setUserId(user.getuId());
		}
		Long size = custOrderService.selectPageTotalCount(order);
		pageTools.setTotal(size);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("pageTools", pageTools);
		return result;
	}
	
	@RequestMapping(value="/orderEdit")
	@ResponseBody
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public Map<String,Object>  orderEdit(CustOrder order) throws Exception{
		logger.info("ordersView request :"+order);
		int num = custOrderService.updateByPrimaryKeySelective(order);
		CustOrder resultOrder = custOrderService.selectByPrimaryKey(order.getOrderNo());
		CustomerConsume consume = new CustomerConsume();
		consume.setUserId(order.getUserId());
		consume.setWechatNo(order.getoWechatNo());
		consume.setOrderNo(order.getOrderNo());
		consume.setConsumeDate(resultOrder.getOrderDate());
		
		if(order.getOrderStatus()==OrderStatus.WAITING.getKey()){
			if(order.getDeposits()!=null&&order.getDeposits()>0){
				consume.setConsumeType(2);
				consume.setAmount(new BigDecimal(order.getDeposits()));
				consume.setRemark(String.format("微信号[%s]的金额消费:%s元,订单号[%s]",consume.getWechatNo(), consume.getAmount().toPlainString(),consume.getOrderNo()));
			}else{
				updateCustomerAMT(order);
				consume.setConsumeType(3);
				consume.setAmount(new BigDecimal(order.getPayAmount()).negate());
				consume.setRemark(String.format("微信号[%s]的余额消费:%s元,订单号[%s]",consume.getWechatNo(), consume.getAmount().toPlainString(),consume.getOrderNo()));
			}
		}else if(order.getOrderStatus()==OrderStatus.SIGNED.getKey()){
			if(order.getCashOnDeliveryAmt()!=null&&order.getCashOnDeliveryAmt()>0){
				consume.setConsumeType(2);
				consume.setAmount(new BigDecimal(order.getCashOnDeliveryAmt()));
				consume.setRemark(String.format("微信号[%s]的金额消费:%s元,订单号[%s]",consume.getWechatNo(), consume.getAmount().toPlainString(),consume.getOrderNo()));
			}
		}else if(order.getOrderStatus()==OrderStatus.REFUSED.getKey()){
			consume.setConsumeType(4);
			customerConsumeService.updateByOrderNo(consume);
			
			BigDecimal amount = new BigDecimal(order.getDeposits() - order.getPayAmount());
			consume.setConsumeType(6);
			consume.setAmount(amount.negate());
			consume.setRemark(String.format("微信号[%s]的退款:%s元,订单号[%s]元,拒收或退款",consume.getWechatNo(), consume.getAmount().toPlainString(),consume.getOrderNo()));
		}
		int paymentMethod = order.getPaymentMethod()==null?-1:order.getPaymentMethod();
		if(consume.getRemark()!=null && paymentMethod!=3){
			logger.info(consume.getRemark());
			customerConsumeService.insertSelective(consume);
		}
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("success", true);
		map.put("editNumber", num);
		return map;
	}

	private void updateCustomerAMT(CustOrder order) throws Exception {
		CustInfo customer = new CustInfo();
		customer.setCustWechatNo(order.getoWechatNo());
		customer.setAmt(order.getPayAmount()*-1);
		custInfoService.updateByPrimaryKeySelective(customer);
	}
	
	@RequestMapping(value="/orderAdd")
	@ResponseBody
	public Map<String,Object>  orderAdd(CustOrder order) throws Exception{
		logger.info("ordersView request:"+order);
		int num = custOrderService.insertSelective(order);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("success", true);
		map.put("addNumber", num);
		return map;
	}

	@RequestMapping(value="/loadPage")
	public ModelAndView loadPage(CustOrder order,PageTools pageTools,@ModelAttribute("user") UserInfo user) throws Exception{
		logger.info("loadPage request:"+order +" page info ==="+pageTools);
		logger.info("loadPage request user info =====:"+user);
		if(!user.isAdmin()){
			order.setUserId(user.getuId());
		}
		
		order.setPageTools(pageTools);
		ModelAndView mv = new ModelAndView("page/orders/Content :: container-fluid");
		Long size = custOrderService.selectPageTotalCount(order);
		pageTools.setTotal(size);
		List<CustOrder> ciList = custOrderService.selectPageByObjectForList(order);
		logger.info("loadPage request list info =====:"+ciList);
		mv.addObject("orderList", ciList);
		mv.addObject("pageTools", pageTools);
		mv.addObject("queryOrder", order);
		
		return mv;
	}
	
	@RequestMapping(value="/primaryModalView")
	public String primaryModalView(Long id,String modifyModel,Model model,@ModelAttribute("user") UserInfo user) throws Exception{
		logger.info("primaryModalView request:"+id+",model:"+model);
		Map<Integer,String> orderStatusMap = new HashMap<Integer,String>();
		if(id!=null){
			CustOrder order = custOrderService.selectByPrimaryKey(id);
			model.addAttribute("modifyOrder", order);
			if(user.isAdmin()){
				orderStatusMap = OrderStatus.orderStatusMap;
			}else if(order.getOrderStatus()==OrderStatus.NON_DELIVERY.getKey()){
				orderStatusMap.put(OrderStatus.NON_DELIVERY.getKey(), OrderStatus.NON_DELIVERY.toString());
				orderStatusMap.put(OrderStatus.WAITING.getKey(), OrderStatus.WAITING.toString());
				orderStatusMap.put(OrderStatus.INVALIDATED.getKey(),  OrderStatus.INVALIDATED.toString());
			}else if(order.getOrderStatus()==OrderStatus.DELIVERING.getKey()){
				orderStatusMap.put(OrderStatus.DELIVERING.getKey(), OrderStatus.DELIVERING.toString());
				orderStatusMap.put(OrderStatus.SIGNED.getKey(), OrderStatus.SIGNED.toString());
				orderStatusMap.put(OrderStatus.REFUSED.getKey(), OrderStatus.REFUSED.toString());
			}else{
				
				orderStatusMap.put(order.getOrderStatus(), OrderStatus.orderStatusMap.get(order.getOrderStatus()));
			}
		}else{ 
			orderStatusMap.put(OrderStatus.NON_DELIVERY.getKey(), OrderStatus.NON_DELIVERY.toString());
		}
		
		model.addAttribute("orderStatusMap", orderStatusMap);
		model.addAttribute("modifyModel", modifyModel);
		logger.info("model : "+model+",  levelMap");
		
		return "page/orders/ModifyModal";
	}
	
	@RequestMapping("/orderExport")
    public ModelAndView orderExport(CustOrder order){
		logger.info("orderExport request:"+order);

        List<CustOrder> data = custOrderService.selectByObjectForList(order);
         
        orderExcelMappings.setOrderExcelMappings(data);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("ExcelMappings", orderExcelMappings);

        return new ModelAndView(new ExcelView(), map);
    }
	
	@RequestMapping("/importModalView")
	public String importModalView(){
		logger.info("importModalView request");
		return "page/orders/ImportModalView";
	}
	

    @PostMapping("/import")
    @ResponseBody
    public Map<String, Object> importOrder(@RequestParam("file") MultipartFile file) throws Exception {
    	logger.info("import request");
        List<CustOrder> orderList =new OrderExcelImport().batchImport( file.getOriginalFilename(), file);

        custOrderService.batchUpdateOrders(orderList);
        Map<String, Object> result = new HashMap<String, Object>();
        
        result.put("message", "上传成功,总共上传订单数："+orderList.size());
        logger.info("import end:"+result);
        return result;
    }
    
     

}
