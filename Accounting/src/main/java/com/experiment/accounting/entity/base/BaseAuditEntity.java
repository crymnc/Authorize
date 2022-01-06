package com.experiment.accounting.entity.base;

import com.experiment.accounting.utils.AuthUtils;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseAuditEntity extends BaseEntity {

    @Column(name = "created_at", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date createdAt;

    @Column(name = "created_by", nullable = false, updatable = false)
    protected Long createdBy;

    @PrePersist
    private void setCreationParameters() {
        Long userId = AuthUtils.getAuthenticatedUser().getId();
        this.createdBy = Objects.requireNonNullElse(userId, -1L);
        this.createdAt = new Date();
    }

}
