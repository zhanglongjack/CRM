package com.base.crm.ad.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
	public List<ADConsume> selectBySelective(ADConsume queryObject) {
		return consumeMapper.selectBySelective(queryObject);
	}

	@Override
	public List<ADConsume> selectPageByObjectForList(ADConsume queryObject) {
		return consumeMapper.selectPageByObjectForList(queryObject);
	}
	
	@Override
	public List<ADConsume> querySummaryConsumeAmount(String month) {
		return consumeMapper.querySummaryConsumeAmount(month);
	}
 
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void batchInsert(List<ADConsume> list) {
		for(ADConsume consume : list){
			insertSelective(consume);
		}
	} 
}