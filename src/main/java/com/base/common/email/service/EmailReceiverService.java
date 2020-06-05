package com.base.common.email.service;

import java.util.List;

import com.base.common.email.entity.EmailReceiver;

public interface EmailReceiverService {
    int deleteByPrimaryKey(Long id);

    int insertSelective(EmailReceiver record);

    EmailReceiver selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(EmailReceiver record);
    
	Long selectPageTotalCount(EmailReceiver record);

	List<EmailReceiver> selectPageByObjectForList(EmailReceiver record);

	List<String> selectByEmailTemplateId(Long id);
}