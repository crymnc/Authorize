package com.experiment.authorize.domain.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

@Data
public abstract class BaseModel {
    private Long id;
    @JsonIgnore
    private Long createdBy,updatedBy;
    private Date discontinueDate;
    @JsonIgnore
    private Date createdAt,updatedAt;
}
