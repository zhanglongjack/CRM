package com.base.crm.level.service;

import java.util.List;

import com.base.crm.level.entity.Level;

public interface LevelService {
    int deleteByPrimaryKey(Long lId);

    int insertSelective(Level record);

    Level selectByPrimaryKey(Long lId);

    int updateByPrimaryKeySelective(Level record);

	List<Level> selectAllForMap();


}