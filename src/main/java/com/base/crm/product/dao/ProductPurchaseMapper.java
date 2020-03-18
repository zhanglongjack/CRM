package com.base.crm.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.base.crm.product.entity.ProductPurchase;

@Mapper
public interface ProductPurchaseMapper {
    int deleteByPrimaryKey(Long purchaseId);

    int insertSelective(ProductPurchase record);

    ProductPurchase selectByPrimaryKey(Long purchaseId);

    int updateByPrimaryKeySelective(ProductPurchase record);

	Long selectPageTotalCount(ProductPurchase record);

	List<ProductPurchase> selectPageByObjectForList(ProductPurchase record);
}