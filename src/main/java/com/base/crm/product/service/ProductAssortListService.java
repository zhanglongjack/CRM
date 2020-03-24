package com.base.crm.product.service;

import java.util.List;

import com.base.crm.product.entity.ProductAssortList;

public interface ProductAssortListService {
    int deleteByPrimaryKey(Long id);

    int insertSelective(ProductAssortList record);

    ProductAssortList selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProductAssortList record);
    
	Long selectPageTotalCount(ProductAssortList record);

	List<ProductAssortList> selectPageByObjectForList(ProductAssortList record);

	List<ProductAssortList> selectByObjectForList(ProductAssortList pal);
}