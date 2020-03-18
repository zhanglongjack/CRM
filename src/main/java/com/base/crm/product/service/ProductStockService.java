package com.base.crm.product.service;

import java.util.List;

import com.base.crm.product.entity.ProductStock;

public interface ProductStockService {
    int deleteByPrimaryKey(Long depositoryId);

    int insertSelective(ProductStock record);

    ProductStock selectByPrimaryKey(Long depositoryId);

    int updateByPrimaryKeySelective(ProductStock record);

	Long selectPageTotalCount(ProductStock record);

	List<ProductStock> selectPageByObjectForList(ProductStock record);
}