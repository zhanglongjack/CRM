package com.base.common.email.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.base.common.email.entity.EmailReceiver;

@Mapper
public interface EmailReceiverMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(EmailReceiver record);

    EmailReceiver selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(EmailReceiver record);
    
	Long selectPageTotalCount(EmailReceiver record);

	List<EmailReceiver> selectPageByObjectForList(EmailReceiver record);

	List<String> selectByEmailTemplateId(Long id);
}