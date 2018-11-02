package com.base.crm.consume.controller;

import java.math.BigDecimal;
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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.base.common.util.ExcelView;
import com.base.common.util.PageTools;
import com.base.crm.consume.constants.ConsumerExcelMappings;
import com.base.crm.consume.entity.CustomerConsume;
import com.base.crm.consume.service.CustomerConsumeService;
import com.base.crm.customer.entity.CustInfo;
import com.base.crm.customer.service.CustInfoService;
import com.base.crm.users.entity.UserInfo;

@Controller
@RequestMapping(value = "/consume")
@SessionAttributes("user")
public class CustomerConsumeController {
	private static final Logger logger = LoggerFactory.getLogger(CustomerConsumeController.class);
	@Autowired
	private CustomerConsumeService customerConsumeService;
	@Autowired
	private ConsumerExcelMappings consumerExcelMappings;

	@RequestMapping(value = "/primaryModalView")
	public String primaryModalView(Long id, String modifyModel, Model model) throws Exception {
		logger.info("primaryModalView request:" + id + ",model:" + model);
		if (id != null) {
			CustomerConsume consume = customerConsumeService.selectByPrimaryKey(id);
			model.addAttribute("modifyInfo", consume);
		} else {

		}

		model.addAttribute("modifyModel", modifyModel);
		logger.info("primaryModalView model : " + model);

		return "page/consume/ModifyModal";
	}

	@RequestMapping(value = "/consumeView")
	public ModelAndView CustomerConsumeView(CustomerConsume consume, PageTools pageTools,
			@ModelAttribute("user") UserInfo user) {
		logger.info("consumeView request : " +consume);
		
		if (!user.isAdmin()) {
			consume.setUserId(user.getuId());
		}
		Long size = customerConsumeService.selectPageTotalCount(consume);
		pageTools.setTotal(size);
		ModelAndView mv = new ModelAndView("page/consume/CustomerConsumeView");
		consume.setPageTools(pageTools);
		mv.addObject("pageTools", pageTools);
		return mv;
	}

	@RequestMapping(value = "/loadPage")
	public ModelAndView loadPage(CustomerConsume consume, PageTools pageTools,
			@ModelAttribute("user") UserInfo user) throws Exception {
		logger.info("loadPage CustomerConsume request:" + consume + " page info ===" + pageTools);
		
		if (!user.isAdmin()) {
			consume.setUserId(user.getuId());
		}

		consume.setPageTools(pageTools);
		ModelAndView mv = new ModelAndView("page/consume/Content :: container-fluid");
		Long size = customerConsumeService.selectPageTotalCount(consume);
		pageTools.setTotal(size);
		List<CustomerConsume> ciList = customerConsumeService.selectPageByObjectForList(consume);
		logger.info("loadPage CustomerConsume result list info =====:" + ciList);
		
		mv.addObject("resultList", ciList);
		mv.addObject("pageTools", pageTools);
		mv.addObject("queryObject", consume);

		return mv;
	}
	
	@RequestMapping("/export")
    public ModelAndView export(CustomerConsume queryParams){
		logger.info("CustomerConsume request:"+queryParams);

        List<CustomerConsume> data = customerConsumeService.selectByObjectForList(queryParams);
        JSONArray jsonObj = JSON.parseArray(JSON.toJSONString(data));
        logger.info("CustomerConsume========"+jsonObj);
         
        consumerExcelMappings.setOrderExcelMappings(jsonObj);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("ExcelMappings", consumerExcelMappings);

        return new ModelAndView(new ExcelView(), map);
    }
	
}
