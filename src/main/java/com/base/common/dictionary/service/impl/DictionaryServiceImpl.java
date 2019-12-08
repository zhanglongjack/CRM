package com.base.common.dictionary.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.base.common.dictionary.dao.DictionaryMapper;
import com.base.common.dictionary.entity.Dictionary;
import com.base.common.dictionary.service.DictionaryService;
import com.base.crm.common.constants.CommonConstants;

@Service
public class DictionaryServiceImpl implements DictionaryService {

	@Autowired
	private DictionaryMapper dictionaryMapper;
	@Autowired
	private CommonConstants commonConstants;
	
	@Override
	public int deleteByPrimaryKey(Long id) {
		return dictionaryMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(Dictionary record) {
		int num= dictionaryMapper.insertSelective(record);
		commonConstants.init();
		return num;
	}

	@Override
	public Dictionary selectByPrimaryKey(Long id) {
		return dictionaryMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Dictionary record) {
		return dictionaryMapper.updateByPrimaryKeySelective(record);
	} 
	
	@Override
	public List<Dictionary> selectBySelective(Dictionary record) {
		return dictionaryMapper.selectBySelective(record);
	}

	@Override
	public Long selectPageTotalCount(Dictionary record) {
		return dictionaryMapper.selectPageTotalCount(record);
	}

	@Override
	public List<Dictionary> selectPageByObjectForList(Dictionary record) {
		return dictionaryMapper.selectPageByObjectForList(record);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public int updateByPrimaryKeySelectiveIncludeCache(Dictionary dictionary) {
		int num = updateByPrimaryKeySelective(dictionary);
		commonConstants.init();
		return num;
	}

	@Override
	public Map<String,Object> dictionaryList(String bizCode,boolean cache) {
		Map<String, Object> dictMap = new HashMap<String,Object>();
		if(!cache){
			Map<String,String> map = commonConstants.getDictionarysByKey(bizCode);
			List<Dictionary> list = new ArrayList<Dictionary>();
			for(String key : map.keySet()){
				Dictionary dictionary = new Dictionary();
				dictionary.setCode(key);
				dictionary.setName(map.get(key));
				list.add(dictionary);
			}
			dictMap.put("resultList",list);
		}else{
			Dictionary dictionary = new Dictionary();
			dictionary.setBizCode(bizCode);
			List<Dictionary> list = selectBySelective(dictionary);
			dictMap.put("resultList",list);
		}
		return dictMap;
	}

}