package com.base.crm.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.base.crm.product.entity.Product;

@Mapper
public interface ProductMapper {
    int deleteByPrimaryKey(Long productId);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Long productId);

    int updateByPrimaryKeySelective(Product record);

	Long selectPageTotalCount(Product record);

	List<Product> selectPageByObjectForList(Product record);
}