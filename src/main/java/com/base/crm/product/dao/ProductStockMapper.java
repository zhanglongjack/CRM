package com.base.crm.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.base.crm.product.entity.ProductStock;

@Mapper
public interface ProductStockMapper {
    int deleteByPrimaryKey(Long depositoryId);

    int insertSelective(ProductStock record);

    ProductStock selectByPrimaryKey(Long depositoryId);

    int updateByPrimaryKeySelective(ProductStock record);

	Long selectPageTotalCount(ProductStock record);

	List<ProductStock> selectPageByObjectForList(ProductStock record);
}