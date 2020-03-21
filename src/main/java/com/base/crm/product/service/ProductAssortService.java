package com.base.crm.product.service;

import java.util.List;

import com.base.crm.product.entity.ProductAssort;

public interface ProductAssortService {
    int deleteByPrimaryKey(Long id);

    int insertSelective(ProductAssort record);

    ProductAssort selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProductAssort record);
    
	Long selectPageTotalCount(ProductAssort record);

	List<ProductAssort> selectPageByObjectForList(ProductAssort record);

	List<ProductAssort> selectByObjectForList(ProductAssort record);
}