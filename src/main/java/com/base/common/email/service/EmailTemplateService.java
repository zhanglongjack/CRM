package com.base.common.email.service;

import java.util.List;

import com.base.common.email.entity.EmailTemplate;

public interface EmailTemplateService {
    int deleteByPrimaryKey(Long id);

    int insertSelective(EmailTemplate record);

    EmailTemplate selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(EmailTemplate record);

	Long selectPageTotalCount(EmailTemplate record);

	List<EmailTemplate> selectPageByObjectForList(EmailTemplate record);

	EmailTemplate selectByFunCode(String functionCode);
}