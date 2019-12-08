package com.base.crm.ad.service;

import java.util.List;

import com.base.crm.ad.entity.ConsumeAcctGroup;
import com.base.crm.report.entity.ConsumeAcctGroupReport;

public interface ConsumeAcctGroupService {
    int deleteByPrimaryKey(Long id);

    int insertSelective(ConsumeAcctGroup record);

    ConsumeAcctGroup selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ConsumeAcctGroup record);
    
	Long selectPageTotalCount(ConsumeAcctGroup queryObject);

	List<ConsumeAcctGroup> selectPageByObjectForList(ConsumeAcctGroup queryObject);
	
	Long selectConsumeAcctGroupReportPageTotalCount(ConsumeAcctGroupReport queryObject);
	
	List<ConsumeAcctGroupReport> selectConsumeAcctGroupReportPage(ConsumeAcctGroupReport queryObject);
	
	Long selectConsumeAcctGroupReportPageTotalCountMonth(ConsumeAcctGroupReport queryObject);
	
	List<ConsumeAcctGroupReport> selectConsumeAcctGroupReportPageMonth(ConsumeAcctGroupReport queryObject);
	
	
	
}