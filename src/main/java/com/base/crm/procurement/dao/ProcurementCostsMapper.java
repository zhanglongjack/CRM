package com.base.crm.procurement.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.base.crm.procurement.entity.ProcurementCosts;

@Mapper
public interface ProcurementCostsMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(ProcurementCosts record);

    ProcurementCosts selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProcurementCosts record);

	Long selectPageTotalCount(ProcurementCosts queryObject);

	List<ProcurementCosts> selectPageByObjectForList(ProcurementCosts queryObject);

}