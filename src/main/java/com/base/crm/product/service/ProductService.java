package com.base.crm.product.service;

import java.util.List;

import com.base.crm.product.entity.Product;

public interface ProductService {
    int deleteByPrimaryKey(Long productId);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Long productId);

    int updateByPrimaryKeySelective(Product record);

	Long selectPageTotalCount(Product record);

	List<Product> selectPageByObjectForList(Product record);
}