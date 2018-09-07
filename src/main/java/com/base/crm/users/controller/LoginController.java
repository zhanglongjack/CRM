package com.base.crm.users.controller;

import java.util.HashMap;
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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.base.crm.common.constants.CustomerLevelContainer;
import com.base.crm.common.constants.OrderStatus;
import com.base.crm.users.entity.UserInfo;
import com.base.crm.users.service.UserService;

@Controller
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	@Autowired
	private UserService userService;
//	mv.setViewName("forward:/login.html");
//	mv.setViewName("redirect:/login.html");
//	mv.addObject("message", "用户名或密码错误");
	private static Map<Integer,String> levelMap = new HashMap<Integer,String>();
//	public static Map<Integer,String> orderStatusMap = new HashMap<Integer,String>();
	private static Map<Integer,String> paymentMethodMap = new HashMap<Integer,String>();
	static{
//		levelMap.put(0, "普通");
//		levelMap.put(1, "白银");
//		levelMap.put(2, "黄金");
//		levelMap.put(3, "铂金");
//		levelMap.put(4, "钻石");
//		levelMap.put(5, "至尊");

//		orderStatusMap.put(0, "未派送");
//		orderStatusMap.put(1, "派送中");
//		orderStatusMap.put(2, "已签收");
//		orderStatusMap.put(3, "已拒签");
//		orderStatusMap.put(4, "已作废");

		paymentMethodMap.put(0, "微信支付");
		paymentMethodMap.put(1, "货到付款");
		paymentMethodMap.put(2, "定金-到付");
	}
	
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
		session.setAttribute("paymentMethodMap", paymentMethodMap);
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
	
}
