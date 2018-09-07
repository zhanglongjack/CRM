package com.base.crm.revisit.record.entity;

import java.util.Date;

public class CustRevisitRecord {
    private Long revisitId;

    private Long custId;

    private String revisitRecordInfo;

    private String revisitResult;

    private String revisitDate;

    private String revisitTime;

    private Date createDate;

    public Long getRevisitId() {
        return revisitId;
    }

    public void setRevisitId(Long revisitId) {
        this.revisitId = revisitId;
    }

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public String getRevisitRecordInfo() {
        return revisitRecordInfo;
    }

    public void setRevisitRecordInfo(String revisitRecordInfo) {
        this.revisitRecordInfo = revisitRecordInfo;
    }

    public String getRevisitResult() {
        return revisitResult;
    }

    public void setRevisitResult(String revisitResult) {
        this.revisitResult = revisitResult;
    }

    public String getRevisitDate() {
        return revisitDate;
    }

    public void setRevisitDate(String revisitDate) {
        this.revisitDate = revisitDate;
    }

    public String getRevisitTime() {
        return revisitTime;
    }

    public void setRevisitTime(String revisitTime) {
        this.revisitTime = revisitTime;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}