package com.base.common.dictionary.service;

import java.util.List;
import java.util.Map;

import com.base.common.dictionary.entity.Dictionary;

public interface DictionaryService {
    int deleteByPrimaryKey(Long id);

    int insertSelective(Dictionary record);

    Dictionary selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Dictionary record);

	List<Dictionary> selectBySelective(Dictionary record);
	
	Long selectPageTotalCount(Dictionary record);

	List<Dictionary> selectPageByObjectForList(Dictionary record);

	void updateByPrimaryKeySelectiveIncludeCache(Dictionary dictionary);

	Map<String, Object> dictionaryList(String bizCode, boolean cache);

}