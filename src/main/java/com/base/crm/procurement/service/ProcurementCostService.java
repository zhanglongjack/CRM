package com.base.crm.procurement.service;

import java.math.BigDecimal;
import java.util.List;

import com.base.crm.procurement.entity.ProcurementCosts;

public interface ProcurementCostService {
    int deleteByPrimaryKey(Long id);

    int insertSelective(ProcurementCosts record);

    ProcurementCosts selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProcurementCosts record);

	Long selectPageTotalCount(ProcurementCosts queryObject);

	List<ProcurementCosts> selectPageByObjectForList(ProcurementCosts queryObject);

	List<ProcurementCosts> selectBySelective(ProcurementCosts queryObject);

	BigDecimal querySumAmountByMonth(String month);

	List<String> queryMonthBy(String month);

	int doUpdate(ProcurementCosts editData);
 

}