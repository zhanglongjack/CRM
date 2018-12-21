package com.base.crm.ad.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.crm.ad.dao.ADConsumeMapper;
import com.base.crm.ad.entity.ADConsume;
import com.base.crm.ad.service.ADConsumeService;

@Service
public class ADConsumeServiceImpl implements ADConsumeService {
	
	@Autowired
	private ADConsumeMapper consumeMapper;
	
	@Override
	public int deleteByPrimaryKey(Long id) {
		return consumeMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(ADConsume record) {
		return consumeMapper.insertSelective(record);
	}

	@Override
	public ADConsume selectByPrimaryKey(Long id) {
		return consumeMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(ADConsume record) {
		return consumeMapper.updateByPrimaryKeySelective(record);
	}
 
	@Override
	public Long selectPageTotalCount(ADConsume queryObject) {
		return consumeMapper.selectPageTotalCount(queryObject);
	}

	@Override
	public List<ADConsume> selectPageByObjectForList(ADConsume queryObject) {
		return consumeMapper.selectPageByObjectForList(queryObject);
	} 
}