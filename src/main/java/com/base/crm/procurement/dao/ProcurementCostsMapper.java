package com.base.crm.procurement.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.base.crm.procurement.entity.ProcurementCosts;

@Mapper
public interface ProcurementCostsMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(ProcurementCosts record);

    ProcurementCosts selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProcurementCosts record);
    
    List<ProcurementCosts> selectBySelective(ProcurementCosts record);
    
	Long selectPageTotalCount(ProcurementCosts queryObject);

	List<ProcurementCosts> selectPageByObjectForList(ProcurementCosts queryObject);

	BigDecimal querySumAmountByMonth(String month);

	List<String> queryMonthBy(Map<String, String> map);

}