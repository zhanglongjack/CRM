package com.base.crm.product.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.crm.product.dao.ProductAssortListMapper;
import com.base.crm.product.entity.ProductAssortList;
import com.base.crm.product.service.ProductAssortListService;
@Service
public class ProductAssortListServiceImpl implements ProductAssortListService{

	@Autowired
	private ProductAssortListMapper ProductAssortListMapper;

	@Override
	public int deleteByPrimaryKey(Long id) {
		return ProductAssortListMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(ProductAssortList record) {
		return ProductAssortListMapper.insertSelective(record);
	}

	@Override
	public ProductAssortList selectByPrimaryKey(Long id) {
		return ProductAssortListMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(ProductAssortList record) {
		return ProductAssortListMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public Long selectPageTotalCount(ProductAssortList record) {
		return ProductAssortListMapper.selectPageTotalCount(record);
	}

	@Override
	public List<ProductAssortList> selectPageByObjectForList(ProductAssortList record) {
		return ProductAssortListMapper.selectPageByObjectForList(record);
	}

	@Override
	public List<ProductAssortList> selectByObjectForList(ProductAssortList record) {
		if(record!=null){
			record.setPageTools(null);
		}
		return selectPageByObjectForList(record);
	}
}