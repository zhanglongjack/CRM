package com.base.crm.customer.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.base.crm.customer.entity.CustInfo;

@Mapper
public interface CustInfoMapper {
    int deleteByPrimaryKey(Long custId);

    int insertSelective(CustInfo record);

    CustInfo selectByPrimaryKey(Long custId);

    int updateByPrimaryKeySelective(CustInfo record);

	List<CustInfo> selectByObjectForList(CustInfo ci);

	Long selectPageTotalCount(CustInfo ci);

	CustInfo selectByPrimaryWechatNo(String checkWechatNo);

	Map<String, Integer> selectCustCountByMonth(String month);
}