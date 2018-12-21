package com.base.crm.procurement.service;

import java.util.List;

import com.base.crm.procurement.entity.ProcurementCosts;

public interface ProcurementCostService {
    int deleteByPrimaryKey(Long id);

    int insertSelective(ProcurementCosts record);

    ProcurementCosts selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProcurementCosts record);

	Long selectPageTotalCount(ProcurementCosts queryObject);

	List<ProcurementCosts> selectPageByObjectForList(ProcurementCosts queryObject);
 

}