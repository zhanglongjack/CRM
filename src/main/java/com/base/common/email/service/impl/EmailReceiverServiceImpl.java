package com.base.common.email.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.common.email.dao.EmailReceiverMapper;
import com.base.common.email.entity.EmailReceiver;
import com.base.common.email.service.EmailReceiverService;

@Service
public class EmailReceiverServiceImpl implements EmailReceiverService {

	@Autowired
	private EmailReceiverMapper emailRecieverMapper;

	@Override
	public int deleteByPrimaryKey(Long id) {
		return emailRecieverMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(EmailReceiver record) {
		return emailRecieverMapper.insertSelective(record);
	}

	@Override
	public EmailReceiver selectByPrimaryKey(Long id) {
		return emailRecieverMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(EmailReceiver record) {
		return emailRecieverMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public Long selectPageTotalCount(EmailReceiver record) {
		return emailRecieverMapper.selectPageTotalCount(record);
	}

	@Override
	public List<EmailReceiver> selectPageByObjectForList(EmailReceiver record) {
		return emailRecieverMapper.selectPageByObjectForList(record);
	}

	@Override
	public List<String> selectByEmailTemplateId(Long id) {
		return emailRecieverMapper.selectByEmailTemplateId(id);
	}
	
}