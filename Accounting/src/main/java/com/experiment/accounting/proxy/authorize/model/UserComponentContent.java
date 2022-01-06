package com.experiment.accounting.proxy.authorize.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserComponentContent {
    private Long id;
    private Long createdBy,updatedBy;
    private Date discontinueDate;
    private Date createdAt,updatedAt;
    private String content;
    private Long componentId;
    private String componentName;
    private Long userId;
}


