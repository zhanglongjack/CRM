package com.base.crm.product.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.crm.product.dao.ProductPurchaseMapper;
import com.base.crm.product.entity.ProductPurchase;
import com.base.crm.product.service.ProductPurchaseService;
@Service
public class ProductPurchaseServiceImpl implements ProductPurchaseService {

	@Autowired
	private ProductPurchaseMapper productPurchaseMapper;

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
}