package com.base.crm.product.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.crm.product.dao.ProductMapper;
import com.base.crm.product.entity.Product;
import com.base.crm.product.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService  {
	
	@Autowired
	private ProductMapper productMapper;
	
	@Override
	public int deleteByPrimaryKey(Long productId) {
		return productMapper.deleteByPrimaryKey(productId);
	}

	@Override
	public int insertSelective(Product record) {
		return productMapper.insertSelective(record);
	}

	@Override
	public Product selectByPrimaryKey(Long productId) {
		return productMapper.selectByPrimaryKey(productId);
	}

	@Override
	public int updateByPrimaryKeySelective(Product record) {
		return productMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public Long selectPageTotalCount(Product record) {
		return productMapper.selectPageTotalCount(record);
	}

	@Override
	public List<Product> selectPageByObjectForList(Product record) {
		return productMapper.selectPageByObjectForList(record);
	}

	@Override
	public List<Product> selectByObjectForList(Product record) {
		if(record!=null){
			record.setPageTools(null);
		}
		return productMapper.selectPageByObjectForList(record);
	} 
}