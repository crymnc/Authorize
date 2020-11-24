package com.anatoliapark.nursinghome.entity.base;

import com.anatoliapark.nursinghome.entity.auth.UserEntity;
import com.anatoliapark.nursinghome.model.User;
import com.anatoliapark.nursinghome.model.base.BaseModel;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public abstract class BaseEntityAudit extends BaseEntity{

    @Column(name = "created_at", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date createdAt;

    @Column(name = "created_by", nullable = false, updatable = false)
    protected Long createdBy;

    @Column(name = "updated_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date updatedAt;

    @Column(name = "updated_by", nullable = false)
    protected Long updatedBy;

    public BaseEntityAudit(BaseModel model) {
        super(model);
    }

    public BaseEntityAudit(){}

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
        UserEntity user = (UserEntity) authentication.getPrincipal();
        this.updatedBy = user.getId();
        this.updatedAt = new Date();
    }
}
