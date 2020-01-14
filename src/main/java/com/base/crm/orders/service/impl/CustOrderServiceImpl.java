package com.base.crm.orders.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.base.crm.customer.service.CustInfoService;
import com.base.crm.orders.dao.CustOrderMapper;
import com.base.crm.orders.entity.CustOrder;
import com.base.crm.orders.entity.OrderSalesRateReport;
import com.base.crm.orders.service.CustOrderService;
import com.base.crm.report.entity.SummaryReport;
@Service
public class CustOrderServiceImpl implements CustOrderService {

	@Autowired
	private CustOrderMapper custOrderMapper;
	@Autowired 
	private CustInfoService custInfoService;
	
	@Override
	public int deleteByPrimaryKey(Long orderNo) {
		return custOrderMapper.deleteByPrimaryKey(orderNo);
	}

	@Override
	public int insert(CustOrder record) {
		return custOrderMapper.insert(record);
	}

	@Override
	public int insertSelective(CustOrder record) {
		return custOrderMapper.insertSelective(record);
	}

	@Override
	public CustOrder selectByPrimaryKey(Long orderNo) {
		return custOrderMapper.selectByPrimaryKey(orderNo);
	}

	@Override
	public int updateByPrimaryKeySelective(CustOrder record) {
		return custOrderMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(CustOrder record) {
		return custOrderMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<CustOrder> selectByObjectForList(CustOrder order) {
		return custOrderMapper.selectByObjectForList(order);
	}

	@Override
	public Long selectPageTotalCount(CustOrder order) {
		return custOrderMapper.selectPageTotalCount(order);
	}

	@Override
	public List<CustOrder> selectPageByObjectForList(CustOrder order) {
		return custOrderMapper.selectPageByObjectForList(order);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void batchUpdateOrders(List<CustOrder> orderList) {
        for(CustOrder record : orderList){
        	custOrderMapper.updateByPrimaryKeySelective(record);
        }
	}

	@Override
	public SummaryReport querySumAmountByMonth(String month) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("month", month);
		
		return custOrderMapper.querySumAmountByMonth(params);
	}

//	@Override
//	public Map<String, Integer> selectOrderCountByMonth(String month,Long userId) {
//		Map<String, Object> params = new HashMap<String, Object>();
//		params.put("month", month);
//		params.put("userId", userId);
//		return custOrderMapper.selectOrderCountByMonth(params);
//	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void doUpdateBuyStatus() {
		custOrderMapper.updateBuyStatusByErrBuyStatus();
		custInfoService.updateCustOrderStatus();
	}

	@Override
	public Map<String, Double> selectOrderSummaryBy(Map<String, Object> params) {
		return custOrderMapper.selectOrderSummaryBy(params);
	}

	@Override
	public List<SummaryReport> selectDailyKPIOrderSummaryPageBy(CustOrder queryObject) {
		return custOrderMapper.selectDailyKPIOrderSummaryPageBy(queryObject);
	}

	@Override
	public long selectDailyKPIOrderSummaryPageCountBy(CustOrder queryObject) {
		return custOrderMapper.selectDailyKPIOrderSummaryPageCountBy(queryObject);
	}

	@Override
	public List<SummaryReport> selectDailyKPIOrderSummaryPageByMonth(CustOrder queryObject) {
		return custOrderMapper.selectDailyKPIOrderSummaryPageByMonth(queryObject);
	}

	@Override
	public Long selectDailyKPIOrderSummaryPageCountByMonth(CustOrder queryObject) {
		return custOrderMapper.selectDailyKPIOrderSummaryPageCountByMonth(queryObject);
	}

	@Override
	public List<OrderSalesRateReport> selectOrderSalesRateReportPageByMonth(OrderSalesRateReport queryObject) {
		return custOrderMapper.selectOrderSalesRateReportPageByMonth(queryObject);
	}

	@Override
	public Long selectOrderSalesRateReportPageCountByMonth(OrderSalesRateReport queryObject) {
		return custOrderMapper.selectOrderSalesRateReportPageCountByMonth(queryObject);
	}

	@Override
	public List<OrderSalesRateReport> selectSalesRateReportPageByMonth(OrderSalesRateReport queryObject) {
		return custOrderMapper.selectSalesRateReportPageByMonth(queryObject);
	}

	@Override
	public Long selectSalesRateReportPageCountByMonth(OrderSalesRateReport queryObject) {
		return custOrderMapper.selectSalesRateReportPageCountByMonth(queryObject);
	}
}
