package com.base.crm.revisit.record.controller;

import java.util.HashMap;
import java.util.List;
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

import com.base.common.util.PageTools;
import com.base.crm.revisit.record.entity.CustRevisitRecord;
import com.base.crm.revisit.record.service.CustRevisitRecordService;
import com.base.crm.users.entity.UserInfo;

@Controller
@RequestMapping(value = "/revisit")
@SessionAttributes("user")
public class RevisitController {
	private static final Logger logger = LoggerFactory.getLogger(RevisitController.class);
	@Autowired
	private CustRevisitRecordService custRevisitRecordService;

	@RequestMapping(value = "/primaryModalView")
	public String primaryModalView(Long id, String modifyModel, Model model) throws Exception {
		logger.info("primaryModalView request:" + id + ",model:" + model);
		if (id != null) {
			CustRevisitRecord revisit = custRevisitRecordService.selectByPrimaryKey(id);
			model.addAttribute("modifyInfo", revisit);
		} else {

		}

		model.addAttribute("modifyModel", modifyModel);
		logger.info("primaryModalView model : " + model);

		return "page/revisit/ModifyModal";
	}

	@RequestMapping(value = "/revisitView")
	public ModelAndView revisitView(CustRevisitRecord revisit, PageTools pageTools,
			@ModelAttribute("user") UserInfo user) {
		logger.info("revisitView request : " +revisit);
		
		if (!user.isAdmin()) {
			revisit.setUserId(user.getuId());
		}
		Long size = custRevisitRecordService.selectPageTotalCount(revisit);
		pageTools.setTotal(size);
		ModelAndView mv = new ModelAndView("page/revisit/RevisitView");
		revisit.setPageTools(pageTools);
		mv.addObject("pageTools", pageTools);
		return mv;
	}

	@RequestMapping(value = "/loadPageRevisit")
	public ModelAndView loadPageRevisit(CustRevisitRecord revisit, PageTools pageTools,
			@ModelAttribute("user") UserInfo user) throws Exception {
		logger.info("loadPageRevisit request:" + revisit + " page info ===" + pageTools);
		
		if (!user.isAdmin()) {
			revisit.setUserId(user.getuId());
		}

		revisit.setPageTools(pageTools);
		ModelAndView mv = new ModelAndView("page/revisit/Content :: container-fluid");
		Long size = custRevisitRecordService.selectPageTotalCount(revisit);
		pageTools.setTotal(size);
		List<CustRevisitRecord> ciList = custRevisitRecordService.selectPageByObjectForList(revisit);
		logger.info("loadPageRevisit result list info =====:" + ciList);
		
		mv.addObject("resultList", ciList);
		mv.addObject("pageTools", pageTools);
		mv.addObject("queryObject", revisit);

		return mv;
	}

	@RequestMapping(value = "/revisitEdit")
	@ResponseBody
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Map<String, Object> revisitEdit(CustRevisitRecord revisit) throws Exception {
		logger.info("revisitEdit request :" + revisit);
		
		setTimeForRevisit(revisit);
		int num = custRevisitRecordService.updateByPrimaryKeySelective(revisit);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		map.put("editNumber", num);
		return map;
	}

	@RequestMapping(value = "/revisitAdd")
	@ResponseBody
	public Map<String, Object> revisitAdd(CustRevisitRecord revisit) throws Exception {
		logger.info("revisitAdd request:" + revisit);
		
		setTimeForRevisit(revisit);
		int num = custRevisitRecordService.insertSelective(revisit);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		map.put("addNumber", num);
		return map;
	}
	
	private void setTimeForRevisit(CustRevisitRecord revisit){
		String time = revisit.getRevisitTime();
		revisit.setRevisitDate(time.substring(0, 10));
		revisit.setRevisitTime(time.substring(11));
	}

}
