package com.anatoliapark.nursinghome.model.base;

import com.anatoliapark.nursinghome.model.auth.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public class BaseEntityAudit extends BaseEntity {

    @Column(name = "created_at", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @JsonIgnore
    private Date createdAt;

    @Column(name = "created_by", nullable = false, updatable = false)
    @JsonIgnore
    private Long createdBy;

    @Column(name = "updated_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @JsonIgnore
    private Date updatedAt;

    @Column(name = "updated_by", nullable = false)
    @JsonIgnore
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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        this.createdBy = user.getId();
        this.createdAt = new Date();

        this.updatedBy = user.getId();
        this.updatedAt = new Date();
    }

    @PreUpdate
    private void setUpdateParameters() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        this.updatedBy = user.getId();
        this.updatedAt = new Date();
    }
}
