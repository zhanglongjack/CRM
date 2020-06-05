package com.base.common.email.service;

import com.base.common.email.entity.EmailTemplate;

public interface SendMailService {

	public void sendSimpleEmail(EmailTemplate temp);

	String buildContent(String content, String... values);

}
