package com.anatoliapark.nursinghome.model.base;

import com.anatoliapark.nursinghome.entity.base.BaseEntityAudit;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.extern.log4j.Log4j2;

import java.util.Date;

@Log4j2
public abstract class BaseModel {

    private Long id;
    @JsonIgnore
    private Long createdBy,updatedBy;
    @JsonIgnore
    private Date discontinueDate,createdAt,updatedAt;

    public BaseModel(BaseEntityAudit baseEntityAudit){
        this.setUpdatedBy(baseEntityAudit.getUpdatedBy());
        this.setUpdatedAt(baseEntityAudit.getUpdatedAt());
        this.setCreatedBy(baseEntityAudit.getCreatedBy());
        this.setCreatedAt(baseEntityAudit.getCreatedAt());
        this.setDiscontinueDate(baseEntityAudit.getDiscontinueDate());
        this.setId(baseEntityAudit.getId());
    }

    public BaseModel(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getDiscontinueDate() {
        return discontinueDate;
    }

    public void setDiscontinueDate(Date discontinueDate) {
        this.discontinueDate = discontinueDate;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
