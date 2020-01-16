package com.base.crm.ad.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.crm.ad.dao.ADAcctTypeMapper;
import com.base.crm.ad.entity.ADAcctType;
import com.base.crm.ad.service.ADAcctTypeService;

@Service
public class ADAcctTypeServiceImpl implements ADAcctTypeService {
	
	@Autowired
	private ADAcctTypeMapper adAcctTypeMapper;

	@Override
	public int deleteByPrimaryKey(Long id) {
		return adAcctTypeMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(ADAcctType record) {
		return adAcctTypeMapper.insertSelective(record);
	}

	@Override
	public ADAcctType selectByPrimaryKey(Long id) {
		return adAcctTypeMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(ADAcctType record) {
		return adAcctTypeMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public Long selectPageTotalCount(ADAcctType queryObject) {
		return adAcctTypeMapper.selectPageTotalCount(queryObject);
	}

	@Override
	public List<ADAcctType> selectPageByObjectForList(ADAcctType queryObject) {
		return adAcctTypeMapper.selectPageByObjectForList(queryObject);
	}

	@Override
	public List<Map<String,String>> selectAdAcctIdByWechatNo(String wechatNo) {
		return adAcctTypeMapper.selectAdAcctIdByWechatNo(wechatNo);
	}


}