package com.base.crm.revisit.record.dao;

import org.apache.ibatis.annotations.Mapper;

import com.base.crm.revisit.record.entity.CustRevisitRecord;

@Mapper
public interface CustRevisitRecordMapper {
    int deleteByPrimaryKey(Long revisitId);

    int insert(CustRevisitRecord record);

    int insertSelective(CustRevisitRecord record);

    CustRevisitRecord selectByPrimaryKey(Long revisitId);

    int updateByPrimaryKeySelective(CustRevisitRecord record);

    int updateByPrimaryKey(CustRevisitRecord record);
}