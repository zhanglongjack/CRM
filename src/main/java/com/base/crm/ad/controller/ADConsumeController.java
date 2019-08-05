package com.base.crm.ad.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.base.common.util.PageTools;
import com.base.crm.ad.entity.ADConsume;
import com.base.crm.ad.service.ADConsumeService;
import com.base.crm.ad.utils.ADConsumeExcelImport;
import com.base.crm.common.constants.CommonConstants;
import com.base.crm.customer.entity.CustInfo;
import com.base.crm.users.entity.UserInfo;

@Controller
@RequestMapping(value = "/adConsume")
@SessionAttributes("user")
public class ADConsumeController {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private ADConsumeService adConsumeService;

	@RequestMapping(value = "/primaryModalView")
	public String primaryModalView(Long id, String modifyModel, Model model) throws Exception {
		logger.debug("primaryModalView request:" + id + ",model:" + model);
		if (id != null) {
			ADConsume result = adConsumeService.selectByPrimaryKey(id);
			model.addAttribute("modifyInfo", result);
		} 

		model.addAttribute("modifyModel", modifyModel);
		logger.debug("primaryModalView model : " + model);

		return "page/ad/adConsume/ModifyModal";
	}

	@RequestMapping(value = "/pageView")
	@ResponseBody
	public Map<String, Object> pageView(ADConsume queryObject, PageTools pageTools,
			@ModelAttribute("user") UserInfo user) {
		logger.debug("adConsumetView request : " +queryObject);
		
		Assert.isTrue(user.isAdmin(), "非管理员不允许查询"); 
		
		Long size = adConsumeService.selectPageTotalCount(queryObject);
		pageTools.setTotal(size);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("pageTools", pageTools);
		return result;
	}
	
	@RequestMapping(value = "/loadPage")
	public ModelAndView loadPage(ADConsume queryObject, PageTools pageTools,
			@ModelAttribute("user") UserInfo user) throws Exception {
		logger.debug("loadPage ADConsume request:" + queryObject + " page info ===" + pageTools);
		
		Assert.isTrue(user.isAdmin(), "非管理员不允许查询"); 
		
		queryObject.setPageTools(pageTools);
		ModelAndView mv = new ModelAndView("page/ad/adConsume/Content :: container-fluid");
		Long size = adConsumeService.selectPageTotalCount(queryObject);
		pageTools.setTotal(size);
		List<ADConsume> ciList = adConsumeService.selectPageByObjectForList(queryObject);
		logger.debug("loadPage ADConsume result list info =====:" + ciList);
		
		mv.addObject("resultList", ciList);
		mv.addObject("pageTools", pageTools);
		mv.addObject("queryObject", queryObject);

		return mv;
	}

	@RequestMapping(value="/adConsumeEdit")
	@ResponseBody
	public Map<String,Object> edit(ADConsume editData){
		editData.setRealAmount(computConsume(editData.getConsumeAmount(),editData.getRebate()));
		logger.info("adConsumeEdit request:{}",editData);
		int num = adConsumeService.updateByPrimaryKeySelective(editData);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("success", true);
		map.put("editNumber", num);
		return map;
	}
	
	@RequestMapping(value="/adConsumeAdd")
	@ResponseBody
	public Map<String,Object> add(ADConsume addData) throws Exception{
		// 计算实际消费
		addData.setRealAmount(computConsume(addData.getConsumeAmount(),addData.getRebate()));
		logger.info("adConsumeAdd request:{}",addData);
		int num = adConsumeService.insertSelective(addData);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("success", true);
		map.put("addNumber", num);
		return map;
	}
	private BigDecimal computConsume(BigDecimal consumeAmount,BigDecimal rebate){
		return consumeAmount.divide(rebate.add(new BigDecimal(1)),BigDecimal.ROUND_HALF_EVEN);
	}
	
	
	@RequestMapping("/importModalView")
	public ModelAndView importModalView(){
		logger.info("importModalView request");
		ModelAndView mv = new ModelAndView("page/excelImport/ImportModalView");
		mv.addObject("url", "/adConsume/import");
		mv.addObject("title", "广告消费信息导入");
		return mv;
	}
	

    @PostMapping("/import")
    @ResponseBody
    public Map<String, Object> importExcel(@RequestParam("file") MultipartFile file) throws Exception {
    	logger.info("importExcel request");
        List<ADConsume> data =new ADConsumeExcelImport().batchImport( file.getOriginalFilename(), file);

        adConsumeService.batchInsert(data);
        
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("message", "上传成功,总共上传数："+data.size());
        
        logger.info("import end:"+result);
        return result;
    }
    
     
	 
}
