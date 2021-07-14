package com.experiment.authorize.domain;

import com.experiment.authorize.domain.base.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorityOption extends BaseModel {
    private Long authorityId;
    private Long webPageComponentId;
    private Long webPageId;
    private Boolean isActive, isVisible;
}
