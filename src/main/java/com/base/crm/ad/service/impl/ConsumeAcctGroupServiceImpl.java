package com.base.crm.ad.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.crm.ad.dao.ConsumeAcctGroupMapper;
import com.base.crm.ad.entity.ConsumeAcctGroup;
import com.base.crm.ad.service.ConsumeAcctGroupService;
import com.base.crm.report.entity.ConsumeAcctGroupReport;

@Service
public class ConsumeAcctGroupServiceImpl implements ConsumeAcctGroupService {
	
	@Autowired
	private ConsumeAcctGroupMapper consumeAcctGroupMapper;

	@Override
	public int deleteByPrimaryKey(Long id) {
		return consumeAcctGroupMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(ConsumeAcctGroup record) {
		return consumeAcctGroupMapper.insertSelective(record);
	}

	@Override
	public ConsumeAcctGroup selectByPrimaryKey(Long id) {
		return consumeAcctGroupMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(ConsumeAcctGroup record) {
		return consumeAcctGroupMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public Long selectPageTotalCount(ConsumeAcctGroup queryObject) {
		return consumeAcctGroupMapper.selectPageTotalCount(queryObject);
	}

	@Override
	public List<ConsumeAcctGroup> selectPageByObjectForList(ConsumeAcctGroup queryObject) {
		return consumeAcctGroupMapper.selectPageByObjectForList(queryObject);
	}

	@Override
	public Long selectConsumeAcctGroupReportPageTotalCount(ConsumeAcctGroupReport queryObject) {
		return consumeAcctGroupMapper.selectConsumeAcctGroupReportPageTotalCount(queryObject);
	}

	@Override
	public List<ConsumeAcctGroupReport> selectConsumeAcctGroupReportPage(ConsumeAcctGroupReport queryObject) {
		return consumeAcctGroupMapper.selectConsumeAcctGroupReportPage(queryObject);
	}

	@Override
	public Long selectConsumeAcctGroupReportPageTotalCountMonth(ConsumeAcctGroupReport queryObject) {
		return consumeAcctGroupMapper.selectConsumeAcctGroupReportPageTotalCountMonth(queryObject);
	}

	@Override
	public List<ConsumeAcctGroupReport> selectConsumeAcctGroupReportPageMonth(ConsumeAcctGroupReport queryObject) {
		return consumeAcctGroupMapper.selectConsumeAcctGroupReportPageMonth(queryObject);
	}

	@Override
	public Map<String, ConsumeAcctGroup> queryGroupAcctRelationData() {
		List<ConsumeAcctGroup> list = consumeAcctGroupMapper.selectPageByObjectForList(new ConsumeAcctGroup());
		Map<String, ConsumeAcctGroup> groupMap = new HashMap<String, ConsumeAcctGroup>();
		for(ConsumeAcctGroup group : list){
			groupMap.put(group.getAcctId().toString(), group);
		}
		
		return groupMap;
	}

	@Override
	public Long selectConsumeAcctGroupReportPageTotalCountSummary(ConsumeAcctGroupReport queryObject) {
		return consumeAcctGroupMapper.selectConsumeAcctGroupReportPageTotalCountSummary(queryObject);
	}

	@Override
	public List<ConsumeAcctGroupReport> selectConsumeAcctGroupReportPageSummary(ConsumeAcctGroupReport queryObject) {
		return consumeAcctGroupMapper.selectConsumeAcctGroupReportPageSummary(queryObject);
	}



}