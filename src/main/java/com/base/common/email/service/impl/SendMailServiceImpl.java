package com.base.common.email.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.base.common.email.entity.EmailTemplate;
import com.base.common.email.service.EmailReceiverService;
import com.base.common.email.service.SendMailService;

@Component
public class SendMailServiceImpl implements SendMailService {
	// 邮件发送的核心类
	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private EmailReceiverService emailReceiverService;
	@Value("${mailserver.username}")
	private String from;
	
	@Override
	public void sendSimpleEmail(EmailTemplate temp) {
		List<String> reiceiverList = emailReceiverService.selectByEmailTemplateId(temp.getId());
		
		// 通过这个对象封装信息，相当于信封
		SimpleMailMessage message = new SimpleMailMessage();
		// 发件人
		message.setFrom(from);
		// 收件人
		message.setTo(reiceiverList.toArray(new String[reiceiverList.size()]));
		// 邮件主题
		message.setSubject(temp.getSubject());
		// 邮件内容
		message.setText(temp.getContent());
		// 发送邮件
		mailSender.send(message);
		System.out.println("发送结束"+from);
	}
	
	@Override
	public String buildContent(String content,String... values){
		String result = content;
		if(values!=null&&values.length>0){
			for(int i = 0;i<values.length;i++){
				result = result.replace("{"+i+"}", values[i]);
			}
		}
		
		return result;
	}
	
}
