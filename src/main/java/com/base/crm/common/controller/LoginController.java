package com.base.crm.common.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.base.common.util.DateUtils;
import com.base.crm.ad.entity.WechatConsumeGroupRelation;
import com.base.crm.ad.service.WechatConsumeGroupRelationService;
import com.base.crm.common.constants.ConsumeType;
import com.base.crm.common.constants.CustomerLevelContainer;
import com.base.crm.common.constants.OrderStatus;
import com.base.crm.common.constants.PaymentMethodStatus;
import com.base.crm.common.constants.StaffLevel;
import com.base.crm.host.config.entity.HostConfig;
import com.base.crm.host.config.service.HostConfigService;
import com.base.crm.serve.wechat.constants.ServeWechatContainer;
import com.base.crm.users.entity.UserInfo;
import com.base.crm.users.service.UserService;

@Controller
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	@Autowired
	private UserService userService;
	@Autowired
	private HostConfigService hostConfigService;
	@Autowired
	private ServeWechatContainer serveWechatContainer;
	@Autowired
	private WechatConsumeGroupRelationService wechatConsumeGroupRelationService;
	
//	mv.setViewName("forward:/login.html");
//	mv.setViewName("redirect:/login.html");
//	mv.addObject("message", "用户名或密码错误");
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> login(String phone,String password,HttpServletRequest request,ModelMap model){
		Map<String,Object> map = new HashMap<String,Object>();
		UserInfo user  = userService.selectByUserPhone(Long.parseLong(phone));
		if(user==null || !user.getPassword().equals(password)){
			logger.info("用户名或密码错误");
			map.put("success", 0);
			map.put("message", "用户名或密码错误!"); 
			return map;
		}
		
		HttpSession session = request.getSession();
		user.setPassword("******");
		logger.info("登录成功:"+user);
		session.setAttribute("user", user);
		session.setAttribute("levelMap", CustomerLevelContainer.levelMap);
		session.setAttribute("levelList", CustomerLevelContainer.levelList);
		session.setAttribute("orderStatusMap", OrderStatus.getValues());
		session.setAttribute("paymentMethodMap", PaymentMethodStatus.paymentMethodMap);
		session.setAttribute("consumeTypeMap", ConsumeType.consumeTypeMap);
		session.setAttribute("staffLevelMap", StaffLevel.staffLevelMap);
		map.put("success", 1); 
		return map;
	}
	
	
	@RequestMapping(value="/logout")
	public ModelAndView logout(HttpServletRequest request){
		logger.info("注销");
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("forward:/login.html");
		return mv;
	}
	
	
	@RequestMapping(value = "/js/d.js", method = RequestMethod.GET)
	@ResponseBody
	public String dataJS(String d,String wx,String clickBtn) throws Exception {
		logger.info("跨域处理逻辑:"+d+",wx="+wx);
		
		String wechatNo = serveWechatContainer.get(wx);
		if(wechatNo==null){
			HostConfig hostConfig = new HostConfig();
			hostConfig.setWechatNo(wx);
			hostConfig.setHostName(d);
			List<HostConfig> hostConfigList = hostConfigService.selectBySelective(hostConfig);
			if(hostConfigList.size()==0){
				hostConfig.setFlag(0);
				hostConfig.setShowTime("0");
				hostConfig.setCreateTime(DateUtils.dateToStrLong(new Date()));
				hostConfigService.insertSelective(hostConfig);
			}else{
				hostConfig = hostConfigList.get(0);
			}
			
			if(hostConfig.getFlag()!=null && hostConfig.getFlag()==1){
				String result = "infoData = {flag: true, wx:'"+hostConfig.getReturnWechat()+"'};";
				if(hostConfig.getRedirectUrl()!=null && hostConfig.getRedirectUrl().length()>1){
					result += "window.location.href = '"+hostConfig.getRedirectUrl()+"';";
				}
				logger.info("跨域处理返回结果:"+result);
				return result;
			}
		}
		
		return "";
	}
	
	@RequestMapping(value = "/js/wx.js", method = RequestMethod.GET)
	@ResponseBody
	public String wx(String d) throws Exception {
		logger.info("跨域处理逻辑:acctId="+d);
		List<String> relationList = wechatConsumeGroupRelationService.queryJSWechatNo(d);
		
		if(relationList.size()>0){
			String wechats = JSON.toJSON(relationList).toString();
			return "arr_wx = "+wechats;
		}
		
		return "";
	}
	
}
