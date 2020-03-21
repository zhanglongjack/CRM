package com.base.crm.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.base.crm.product.entity.ProductAssort;

@Mapper
public interface ProductAssortMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(ProductAssort record);

    ProductAssort selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProductAssort record);
    
	Long selectPageTotalCount(ProductAssort record);

	List<ProductAssort> selectPageByObjectForList(ProductAssort record);
}