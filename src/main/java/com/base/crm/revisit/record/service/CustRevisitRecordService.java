package com.base.crm.revisit.record.service;

import org.apache.ibatis.annotations.Mapper;

import com.base.crm.revisit.record.entity.CustRevisitRecord;
@Mapper
public interface CustRevisitRecordService {
    int deleteByPrimaryKey(Long returnVisit);

    int insert(CustRevisitRecord record);

    int insertSelective(CustRevisitRecord record);

    CustRevisitRecord selectByPrimaryKey(Long returnVisit);

    int updateByPrimaryKeySelective(CustRevisitRecord record);

    int updateByPrimaryKey(CustRevisitRecord record);
}