package com.base.crm.customer.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.crm.customer.dao.CustInfoMapper;
import com.base.crm.customer.entity.CustInfo;
import com.base.crm.customer.service.CustInfoService;

@Service
public class CustInfoServiceImpl implements CustInfoService {

	@Autowired
	private CustInfoMapper custInfoMapper;
	@Override
	public int deleteByPrimaryKey(Long custId) {
		return custInfoMapper.deleteByPrimaryKey(custId);
	}

	@Override
	public int insertSelective(CustInfo record) {
		return custInfoMapper.insertSelective(record);
	}

	@Override
	public CustInfo selectByPrimaryKey(Long custId) {
		return custInfoMapper.selectByPrimaryKey(custId);
	}

	@Override
	public int updateByPrimaryKeySelective(CustInfo record) {
		return custInfoMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<CustInfo> selectByObjectForList(CustInfo ci) {
		return custInfoMapper.selectByObjectForList(ci);
	}

	@Override
	public Long selectPageTotalCount(CustInfo ci) {
		return custInfoMapper.selectPageTotalCount(ci);
	}

	@Override
	public CustInfo selectByPrimaryWechatNo(String checkWechatNo) {
		return custInfoMapper.selectByPrimaryWechatNo(checkWechatNo);
	}

	@Override
	public Map<String, Integer> selectCustCountByMonth(String month,Long userId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("month", month);
		params.put("userId", userId);
		return custInfoMapper.selectCustCountByMonth(params);
	}

	@Override
	public int updateCustOrderStatus() {
		return custInfoMapper.updateCustOrderStatus();
	}

	@Override
	public List<Map<String, Object>> queryAddCustCountBy(String month, String serveWechatNo) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("month", month);
		params.put("serveWechatNo", serveWechatNo);
		return custInfoMapper.queryAddCustCountBy(params);
	}

	@Override
	public BigDecimal queryServerSalePerformanBy(String month, String serveWechatNo) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("month", month);
		params.put("serveWechatNo", serveWechatNo);
		return custInfoMapper.queryServerSalePerformanBy(params);
	}
 
}