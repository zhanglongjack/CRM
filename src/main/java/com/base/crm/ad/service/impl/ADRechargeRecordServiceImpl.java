package com.base.crm.ad.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.crm.ad.dao.ADRechargeRecordMapper;
import com.base.crm.ad.entity.ADRechargeRecord;
import com.base.crm.ad.service.ADRechargeRecordService;

@Service
public class ADRechargeRecordServiceImpl implements ADRechargeRecordService {

	@Autowired
	private ADRechargeRecordMapper rechargeMapper;
	@Override
	public int deleteByPrimaryKey(Long id) {
		return rechargeMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(ADRechargeRecord record) {
		return rechargeMapper.insertSelective(record);
	}

	@Override
	public ADRechargeRecord selectByPrimaryKey(Long id) {
		return rechargeMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(ADRechargeRecord record) {
		return rechargeMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public Long selectPageTotalCount(ADRechargeRecord queryObject) {
		return rechargeMapper.selectPageTotalCount(queryObject);
	}

	@Override
	public List<ADRechargeRecord> selectPageByObjectForList(ADRechargeRecord queryObject) {
		return rechargeMapper.selectPageByObjectForList(queryObject);
	} 

}