package com.base.crm.revisit.record.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.crm.revisit.record.dao.CustRevisitRecordMapper;
import com.base.crm.revisit.record.entity.CustRevisitRecord;
import com.base.crm.revisit.record.service.CustRevisitRecordService;
@Service
public class CustRevisitRecordServiceImpl implements CustRevisitRecordService {

	@Autowired
	private CustRevisitRecordMapper custRevisitRecordMapper;
	@Override
	public int deleteByPrimaryKey(Long returnVisit) {
		return custRevisitRecordMapper.deleteByPrimaryKey(returnVisit);
	}

	@Override
	public int insertSelective(CustRevisitRecord record) {
		return custRevisitRecordMapper.insertSelective(record);
	}

	@Override
	public CustRevisitRecord selectByPrimaryKey(Long returnVisit) {
		return custRevisitRecordMapper.selectByPrimaryKey(returnVisit);
	}

	@Override
	public int updateByPrimaryKeySelective(CustRevisitRecord record) {
		return custRevisitRecordMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public Long selectPageTotalCount(CustRevisitRecord revisit) {
		return custRevisitRecordMapper.selectPageTotalCount(revisit);
	}

	@Override
	public List<CustRevisitRecord> selectPageByObjectForList(CustRevisitRecord revisit) {
		return custRevisitRecordMapper.selectPageByObjectForList(revisit);
	}

}
