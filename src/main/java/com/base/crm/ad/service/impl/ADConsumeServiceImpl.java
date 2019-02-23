package com.base.crm.ad.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	@Override
	public Map<String,BigDecimal> queryRealConsumeAd(String month) {
		List<ADConsume> consumeList = querySummaryConsumeAmount(month);
		BigDecimal normalConsumeAD = new BigDecimal(0);
		BigDecimal hospitalConsumeAD = new BigDecimal(0);
		if(consumeList.size()>0){
			// 各帐号广告费统计
			for(ADConsume consume : consumeList){
				if(consume.getConsumeAccountType().equals("normal_account")){
					normalConsumeAD = normalConsumeAD.add(consume.getConsumeAmount().divide(new BigDecimal("1.3"),2,BigDecimal.ROUND_HALF_EVEN));
				}else{
					hospitalConsumeAD = hospitalConsumeAD.add(consume.getConsumeAmount().divide(new BigDecimal("1.25"),2,BigDecimal.ROUND_HALF_EVEN));
				}
			}
		}
		
		Map<String,BigDecimal> result = new HashMap<String,BigDecimal>();
		result.put("normal_account", normalConsumeAD);
		result.put("hospital_account", hospitalConsumeAD);
		return result;
	} 
}