package com.base.common.email.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.common.email.dao.EmailTemplateMapper;
import com.base.common.email.entity.EmailTemplate;
import com.base.common.email.service.EmailTemplateService;

@Service
public class EmailTemplateServiceImpl implements EmailTemplateService {

	@Autowired
	private EmailTemplateMapper emailTemplateMapper;
	
	@Override
	public int deleteByPrimaryKey(Long id) {
		return emailTemplateMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(EmailTemplate record) {
		return emailTemplateMapper.insertSelective(record);
	}

	@Override
	public EmailTemplate selectByPrimaryKey(Long id) {
		return emailTemplateMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(EmailTemplate record) {
		return emailTemplateMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public Long selectPageTotalCount(EmailTemplate record) {
		return emailTemplateMapper.selectPageTotalCount(record);
	}

	@Override
	public List<EmailTemplate> selectPageByObjectForList(EmailTemplate record) {
		return emailTemplateMapper.selectPageByObjectForList(record);
	}

	@Override
	public EmailTemplate selectByFunCode(String functionCode) {
		return emailTemplateMapper.selectByFunCode(functionCode);
	} 
}