package com.base.crm.revisit.record.service;

import java.util.List;

import com.base.crm.revisit.record.entity.CustRevisitRecord;
public interface CustRevisitRecordService {
    int deleteByPrimaryKey(Long returnVisit);

    int insertSelective(CustRevisitRecord record);

    CustRevisitRecord selectByPrimaryKey(Long returnVisit);

    int updateByPrimaryKeySelective(CustRevisitRecord record);

	Long selectPageTotalCount(CustRevisitRecord revisit);

	List<CustRevisitRecord> selectPageByObjectForList(CustRevisitRecord revisit);
}