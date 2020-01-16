package com.base.crm.ad.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.base.crm.ad.entity.ConsumeAcctGroup;
import com.base.crm.report.entity.ConsumeAcctGroupReport;

@Mapper
public interface ConsumeAcctGroupMapper {
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
	
	Long selectConsumeAcctGroupReportPageTotalCountSummary(ConsumeAcctGroupReport queryObject);
	
	List<ConsumeAcctGroupReport> selectConsumeAcctGroupReportPageSummary(ConsumeAcctGroupReport queryObject);
	
	
}