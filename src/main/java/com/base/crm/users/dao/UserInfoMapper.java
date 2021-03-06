package com.base.crm.users.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.base.crm.users.entity.UserInfo;

@Mapper
public interface UserInfoMapper {
    int deleteByPrimaryKey(Long uId);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Long uId);

    int updateByPrimaryKeySelective(UserInfo record);

	UserInfo selectByUserPhone(Long phone);

	Long selectPageTotalCount(UserInfo userInfo);

	List<UserInfo> selectPageByObjectForList(UserInfo userInfo);

	List<UserInfo> selectAll();

}