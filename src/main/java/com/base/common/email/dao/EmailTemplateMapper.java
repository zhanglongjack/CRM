package com.base.common.email.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.base.common.email.entity.EmailTemplate;

@Mapper
public interface EmailTemplateMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(EmailTemplate record);

    EmailTemplate selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(EmailTemplate record);

	Long selectPageTotalCount(EmailTemplate record);

	List<EmailTemplate> selectPageByObjectForList(EmailTemplate record);

	EmailTemplate selectByFunCode(String functionCode);
}