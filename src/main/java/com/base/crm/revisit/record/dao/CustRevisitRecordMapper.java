package com.base.crm.revisit.record.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.base.crm.revisit.record.entity.CustRevisitRecord;

@Mapper
public interface CustRevisitRecordMapper {
    int deleteByPrimaryKey(Long revisitId);

    int insertSelective(CustRevisitRecord record);

    CustRevisitRecord selectByPrimaryKey(Long revisitId);

    int updateByPrimaryKeySelective(CustRevisitRecord record);

	Long selectPageTotalCount(CustRevisitRecord revisit);

	List<CustRevisitRecord> selectPageByObjectForList(CustRevisitRecord revisit);
}