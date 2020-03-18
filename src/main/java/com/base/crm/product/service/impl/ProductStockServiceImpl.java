package com.base.crm.product.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.crm.product.dao.ProductStockMapper;
import com.base.crm.product.entity.ProductStock;
import com.base.crm.product.service.ProductStockService;
@Service
public class ProductStockServiceImpl implements ProductStockService {

	@Autowired
	private ProductStockMapper productStockMapper;
	@Override
	public int deleteByPrimaryKey(Long depositoryId) {
		return productStockMapper.deleteByPrimaryKey(depositoryId);
	}

	@Override
	public int insertSelective(ProductStock record) {
		return productStockMapper.insertSelective(record);
	}

	@Override
	public ProductStock selectByPrimaryKey(Long depositoryId) {
		return productStockMapper.selectByPrimaryKey(depositoryId);
	}

	@Override
	public int updateByPrimaryKeySelective(ProductStock record) {
		return productStockMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public Long selectPageTotalCount(ProductStock record) {
		return productStockMapper.selectPageTotalCount(record);
	}

	@Override
	public List<ProductStock> selectPageByObjectForList(ProductStock record) {
		return productStockMapper.selectPageByObjectForList(record);
	}

}