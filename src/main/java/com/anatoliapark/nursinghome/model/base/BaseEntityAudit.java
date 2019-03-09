package com.anatoliapark.nursinghome.model.base;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public class BaseEntityAudit extends BaseEntity {

    @Transient
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    @Column(name = "created_at", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "created_by", nullable = false, updatable = false)
    private Long createdBy;

    @Column(name = "updated_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @Column(name = "updated_by", nullable = false)
    private Long updatedBy;

    public Date getCreatedAt() {
        return createdAt;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }


    @PrePersist
    private void setCreationParameters() {
        //User user = (User) authentication.getPrincipal();
        this.createdBy = 1L;
        this.createdAt = new Date();

        this.updatedBy = 1L;
        this.updatedAt = new Date();
    }

    @PreUpdate
    private void setUpdateParameters() {
        //User user = (User) authentication.getPrincipal();
        this.updatedBy = 1L;
        this.updatedAt = new Date();
    }
}
