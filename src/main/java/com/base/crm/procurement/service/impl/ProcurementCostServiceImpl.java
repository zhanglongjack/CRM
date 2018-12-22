package com.base.crm.procurement.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.crm.procurement.dao.ProcurementCostsMapper;
import com.base.crm.procurement.entity.ProcurementCosts;
import com.base.crm.procurement.service.ProcurementCostService;

@Service
public class ProcurementCostServiceImpl implements ProcurementCostService {

	@Autowired
	private ProcurementCostsMapper procurementCostsMapper;
	
	@Override
	public int deleteByPrimaryKey(Long id) {
		return procurementCostsMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(ProcurementCosts record) {
		return procurementCostsMapper.insertSelective(record);
	}

	@Override
	public ProcurementCosts selectByPrimaryKey(Long id) {
		return procurementCostsMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(ProcurementCosts record) {
		return procurementCostsMapper.updateByPrimaryKeySelective(record);
	}
	@Override
	public List<ProcurementCosts> selectBySelective(ProcurementCosts queryObject) {
		return procurementCostsMapper.selectBySelective(queryObject);
	}

	@Override
	public Long selectPageTotalCount(ProcurementCosts queryObject) {
		return procurementCostsMapper.selectPageTotalCount(queryObject);
	}

	@Override
	public List<ProcurementCosts> selectPageByObjectForList(ProcurementCosts queryObject) {
		return procurementCostsMapper.selectPageByObjectForList(queryObject);
	}

	@Override
	public List<String> queryMonthBy(String month) {
		Map<String,String> map = new HashMap<>();
		map.put("month", month);
		return procurementCostsMapper.queryMonthBy(map);
	}

	@Override
	public BigDecimal querySumAmountByMonth(String month) {
		return procurementCostsMapper.querySumAmountByMonth(month);
	}
 

}