package com.base.crm.level.entity;

import java.math.BigDecimal;

public class Level {
    private Integer lId;

    private String lName;

    private BigDecimal lDiscount;

    public Integer getlId() {
        return lId;
    }

    public void setlId(Integer lId) {
        this.lId = lId;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public BigDecimal getlDiscount() {
        return lDiscount;
    }

    public void setlDiscount(BigDecimal lDiscount) {
        this.lDiscount = lDiscount;
    }
}