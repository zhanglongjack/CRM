package com.base.crm.serve.wechat.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.base.crm.serve.wechat.entity.ServeWechat;

@Mapper
public interface ServeWechatMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(ServeWechat record);

    ServeWechat selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ServeWechat record);

	List<ServeWechat> selectPageByObjectForList(ServeWechat serveWechat);

	Long selectPageTotalCount(ServeWechat serveWechat);

	List<ServeWechat> selectAll();

	ServeWechat selectByPrimaryWechatNo(String wechatNo);

}