package com.base.crm.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.base.crm.product.entity.ProductAssortList;

@Mapper
public interface ProductAssortListMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(ProductAssortList record);

    ProductAssortList selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProductAssortList record);
    
	Long selectPageTotalCount(ProductAssortList record);

	List<ProductAssortList> selectPageByObjectForList(ProductAssortList record);
}