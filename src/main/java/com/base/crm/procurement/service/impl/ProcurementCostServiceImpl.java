package com.base.crm.procurement.service.impl;

import java.util.List;

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
	public Long selectPageTotalCount(ProcurementCosts queryObject) {
		return procurementCostsMapper.selectPageTotalCount(queryObject);
	}

	@Override
	public List<ProcurementCosts> selectPageByObjectForList(ProcurementCosts queryObject) {
		return procurementCostsMapper.selectPageByObjectForList(queryObject);
	}
 

}