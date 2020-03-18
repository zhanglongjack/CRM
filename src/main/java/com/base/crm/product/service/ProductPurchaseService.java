package com.base.crm.product.service;

import java.util.List;

import com.base.crm.product.entity.ProductPurchase;

public interface ProductPurchaseService {
    int deleteByPrimaryKey(Long purchaseId);

    int insertSelective(ProductPurchase record);

    ProductPurchase selectByPrimaryKey(Long purchaseId);

    int updateByPrimaryKeySelective(ProductPurchase record);

	Long selectPageTotalCount(ProductPurchase record);

	List<ProductPurchase> selectPageByObjectForList(ProductPurchase record);
}