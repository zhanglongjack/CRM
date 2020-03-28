package com.base.crm.procurement.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.base.common.util.DateUtils;
import com.base.crm.procurement.dao.ProcurementCostsMapper;
import com.base.crm.procurement.entity.ProcurementCosts;
import com.base.crm.procurement.service.ProcurementCostService;
import com.base.crm.product.entity.ProductStock;
import com.base.crm.product.service.ProductStockService;

@Service
public class ProcurementCostServiceImpl implements ProcurementCostService {

	@Autowired
	private ProcurementCostsMapper procurementCostsMapper;
	@Autowired
	private ProductStockService productStockService;
	
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
 
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public int doUpdate(ProcurementCosts editData) {
		if("2".equals(editData.getOldStatus())){
			editData.setStatus("2");
		}else if("2".equals(editData.getStatus())&& "1".equals(editData.getOldStatus())){
			ProductStock stock = new ProductStock();
			stock.setDepositoryId(editData.getDepositoryId());
			stock.setProductId(editData.getProductId());
			stock.setStockNum(editData.getNum().longValue());
			stock.setUpdatedDate(DateUtils.dateToStrLong(new Date()));
			productStockService.updateStockNum(stock);
		}
		return updateByPrimaryKeySelective(editData);
	}
}