package com.base.crm.salary.entity;

import java.math.BigDecimal;
import com.base.common.util.PageTools;

public class ServerSalary {

	private Long id;
    
    private Long userId;

    private BigDecimal salary;

    private BigDecimal salesPerformance;

    private String salaryMonth;

	// query
//	private String startDate = DateUtils.format(new Date(),"yyyyMM01", Locale.getDefault());
//	private String endDate = DateUtils.format(new Date(), DateFormateType.TIGHT_SHORT_FORMAT, Locale.getDefault());
	
//    @JsonIgnore
    private PageTools pageTools;
    
//    public String getStartDate() {
//		return startDate;
//	}
//
//	public void setStartDate(String startDate) {
//		this.startDate = startDate;
//	}
//
//	public String getEndDate() {
//		return endDate;
//	}
//
//	public void setEndDate(String endDate) {
//		this.endDate = endDate;
//	}

	public PageTools getPageTools() {
		return pageTools;
	}

	public void setPageTools(PageTools pageTools) {
		this.pageTools = pageTools;
	}
	
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public BigDecimal getSalesPerformance() {
        return salesPerformance;
    }

    public void setSalesPerformance(BigDecimal salesPerformance) {
        this.salesPerformance = salesPerformance;
    }

    public String getSalaryMonth() {
        return salaryMonth;
    }

    public void setSalaryMonth(String salaryMonth) {
        this.salaryMonth = salaryMonth;
    }
}