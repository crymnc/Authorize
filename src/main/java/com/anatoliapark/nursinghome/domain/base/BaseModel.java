package com.anatoliapark.nursinghome.domain.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

@Data
public abstract class BaseModel {
    private Long id;
    @JsonIgnore
    private Long createdBy,updatedBy;
    @JsonIgnore
    private Date discontinueDate,createdAt,updatedAt;
}
