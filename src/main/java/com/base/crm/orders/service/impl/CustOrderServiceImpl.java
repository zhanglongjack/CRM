package com.base.crm.orders.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.base.common.util.DateUtils;
import com.base.crm.customer.service.CustInfoService;
import com.base.crm.orders.dao.CustOrderMapper;
import com.base.crm.orders.entity.CustOrder;
import com.base.crm.orders.entity.OrderProductList;
import com.base.crm.orders.entity.OrderSalesRateReport;
import com.base.crm.orders.service.CustOrderService;
import com.base.crm.orders.service.OrderProductListService;
import com.base.crm.product.entity.ProductAssortList;
import com.base.crm.product.entity.ProductStock;
import com.base.crm.product.service.ProductAssortListService;
import com.base.crm.product.service.ProductStockService;
import com.base.crm.report.entity.ExpressReport;
import com.base.crm.report.entity.SummaryReport;
@Service
public class CustOrderServiceImpl implements CustOrderService {

	@Autowired
	private CustOrderMapper custOrderMapper;
	@Autowired 
	private CustInfoService custInfoService;
	@Autowired 
	private ProductAssortListService productAssortListService;
	@Autowired 
	private OrderProductListService orderProductListService;
	@Autowired
	private ProductStockService productStockService;
	
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

	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public int doInsert(CustOrder order) {
		insertSelective(order);
		
		ProductAssortList palParam = new ProductAssortList();
		palParam.setAssortId(order.getAssortId());
		List<ProductAssortList> palList = productAssortListService.selectByObjectForList(palParam);
		
		OrderProductList opl = new OrderProductList();
		for(ProductAssortList pal : palList){
			opl.setDepositoryId(order.getDepositoryId());
			opl.setProductId(pal.getProductId());
			opl.setOrderId(order.getOrderNo());
			opl.setNum(pal.getNum());
			opl.setCreatedDate(DateUtils.dateToStrLong(new Date()));
			orderProductListService.insertSelective(opl);
		}
		
		return 1;
	}

	@Override
	/**
	 * 增加库存或减少库存,<br>
	 * plusOrSubtract如果为正数1则增加库存<br>
	 * plusOrSubtract如果为负数-1则减少库存
	 * @param order
	 * @param plusOrSubtract
	 */
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void batchUpdateProductStock(CustOrder order,int plusOrSubtract) {
		
		OrderProductList palParam = new OrderProductList();
		palParam.setOrderId(order.getOrderNo());
		List<OrderProductList> palList = orderProductListService.selectByObjectForList(palParam);
		
		for(OrderProductList pal : palList){
			ProductStock ps = new ProductStock();
			ps.setDepositoryId(order.getDepositoryId());
			ps.setProductId(pal.getProductId());
			ps.setStockNum(pal.getNum()* plusOrSubtract);
			ps.setUpdatedDate(DateUtils.dateToStrLong(new Date()));
			
			productStockService.updateStockNum(ps);
		}
	}

	@Override
	public List<ExpressReport> selectExpressReportPageBy(ExpressReport queryObject) {
		return custOrderMapper.selectExpressReportPageBy(queryObject);
	}

	@Override
	public long selectExpressReportPageCountBy(ExpressReport queryObject) {
		return custOrderMapper.selectExpressReportPageCountBy(queryObject);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public int doDelete(Long orderNo) {
		orderProductListService.deleteByOrderId(orderNo);
		return custOrderMapper.deleteByPrimaryKey(orderNo);
	}
}
