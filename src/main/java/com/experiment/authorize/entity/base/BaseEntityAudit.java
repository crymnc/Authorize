package com.experiment.authorize.entity.base;

import com.experiment.authorize.entity.auth.UserEntity;
import com.experiment.authorize.service.UserService;
import com.experiment.authorize.util.OAuthUtils;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
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

    @PrePersist
    private void setCreationParameters() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || authentication.getPrincipal().equals("anonymousUser")){
            this.createdBy = -1L;
            this.updatedBy = -1L;
        }
        else{
            UserEntity user = (UserEntity) authentication.getPrincipal();
            this.createdBy = user.getId();
            this.updatedBy = user.getId();
        }

        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    @PreUpdate
    private void setUpdateParameters() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication.getPrincipal().equals("anonymousUser")){
            this.updatedBy = -1L;
        }
        else{
            UserEntity user = (UserEntity) authentication.getPrincipal();
            this.updatedBy = user.getId();
        }
        this.updatedAt = new Date();
    }
}
