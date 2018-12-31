package com.base.crm.orders.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.base.crm.orders.entity.CustOrder;
import com.base.crm.report.entity.SummaryReport;
@Mapper
public interface CustOrderMapper {
    int deleteByPrimaryKey(Long orderNo);

    int insert(CustOrder record);

    int insertSelective(CustOrder record);

    CustOrder selectByPrimaryKey(Long orderNo);

    int updateByPrimaryKeySelective(CustOrder record);

    int updateByPrimaryKey(CustOrder record);

	List<CustOrder> selectByObjectForList(CustOrder order);

	Long selectPageTotalCount(CustOrder order);

	List<CustOrder> selectPageByObjectForList(CustOrder order);

	SummaryReport querySumAmountByMonth(String month);

	Map<String, Integer> selectOrderCountByMonth(String month);

	void updateBuyStatusByErrBuyStatus();

}