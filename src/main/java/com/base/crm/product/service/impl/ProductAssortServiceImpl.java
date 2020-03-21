package com.base.crm.product.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.crm.product.dao.ProductAssortMapper;
import com.base.crm.product.entity.ProductAssort;
import com.base.crm.product.service.ProductAssortService;

@Service
public class ProductAssortServiceImpl implements ProductAssortService {

	@Autowired
	private ProductAssortMapper productAssortMapper;
	
	@Override
	public int deleteByPrimaryKey(Long id) {
		return productAssortMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(ProductAssort record) {
		return productAssortMapper.insertSelective(record);
	}

	@Override
	public ProductAssort selectByPrimaryKey(Long id) {
		return productAssortMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(ProductAssort record) {
		return productAssortMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public Long selectPageTotalCount(ProductAssort record) {
		return productAssortMapper.selectPageTotalCount(record);
	}

	@Override
	public List<ProductAssort> selectPageByObjectForList(ProductAssort record) {
		return productAssortMapper.selectPageByObjectForList(record);
	}

	@Override
	public List<ProductAssort> selectByObjectForList(ProductAssort record) {
		if(record!=null){
			record.setPageTools(null);
		}
		return productAssortMapper.selectPageByObjectForList(record);
	} 
}