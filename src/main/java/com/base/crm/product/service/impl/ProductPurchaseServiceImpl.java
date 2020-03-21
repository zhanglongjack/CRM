package com.base.crm.product.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.base.common.util.DateUtils;
import com.base.crm.product.dao.ProductPurchaseMapper;
import com.base.crm.product.entity.ProductPurchase;
import com.base.crm.product.entity.ProductStock;
import com.base.crm.product.service.ProductPurchaseService;
import com.base.crm.product.service.ProductStockService;
@Service
public class ProductPurchaseServiceImpl implements ProductPurchaseService {

	@Autowired
	private ProductPurchaseMapper productPurchaseMapper;
	@Autowired
	private ProductStockService productStockService;

	@Override
	public int deleteByPrimaryKey(Long purchaseId) {
		return productPurchaseMapper.deleteByPrimaryKey(purchaseId);
	}

	@Override
	public int insertSelective(ProductPurchase record) {
		return productPurchaseMapper.insertSelective(record);
	}

	@Override
	public ProductPurchase selectByPrimaryKey(Long purchaseId) {
		return productPurchaseMapper.selectByPrimaryKey(purchaseId);
	}

	@Override
	public int updateByPrimaryKeySelective(ProductPurchase record) {
		return productPurchaseMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public Long selectPageTotalCount(ProductPurchase record) {
		return productPurchaseMapper.selectPageTotalCount(record);
	}

	@Override
	public List<ProductPurchase> selectPageByObjectForList(ProductPurchase record) {
		return productPurchaseMapper.selectPageByObjectForList(record);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public int doUpdate(ProductPurchase editData) {
		if(editData.getOldStatus().equals("2")){
			editData.setStatus("2");
		}else if(editData.getStatus().equals("2") && editData.getOldStatus().equals("1")){
			ProductStock stock = new ProductStock();
			stock.setDepositoryId(editData.getDepositoryId());
			stock.setProductId(editData.getProductId());
			stock.setStockNum(editData.getPurchaseNum());
			stock.setUpdatedDate(DateUtils.dateToStrLong(new Date()));
			productStockService.updateStockNum(stock);
		}
		return updateByPrimaryKeySelective(editData);
	}
}