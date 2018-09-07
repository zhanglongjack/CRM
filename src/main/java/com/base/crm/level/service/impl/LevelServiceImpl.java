package com.base.crm.level.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.base.crm.level.dao.LevelMapper;
import com.base.crm.level.entity.Level;
import com.base.crm.level.service.LevelService;

@Component
public class LevelServiceImpl implements LevelService{
	
	@Autowired
	private LevelMapper levelMapper;
	
	@Override
	public int deleteByPrimaryKey(Long lId) {
		return levelMapper.deleteByPrimaryKey(lId);
	}

	@Override
	public int insertSelective(Level record) {
		return levelMapper.insertSelective(record);
	}

	@Override
	public Level selectByPrimaryKey(Long lId) {
		return levelMapper.selectByPrimaryKey(lId);
	}

	@Override
	public int updateByPrimaryKeySelective(Level record) {
		return levelMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<Level> selectAllForMap() {
		return levelMapper.selectAll();
	}



}