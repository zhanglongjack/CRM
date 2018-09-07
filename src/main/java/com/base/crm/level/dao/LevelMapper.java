package com.base.crm.level.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.base.crm.level.entity.Level;

@Mapper
public interface LevelMapper {
    int deleteByPrimaryKey(Long lId);

    int insertSelective(Level record);

    Level selectByPrimaryKey(Long lId);

    int updateByPrimaryKeySelective(Level record);

	List<Level> selectAll();


}