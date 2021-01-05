package com.anatoliapark.nursinghome.domain;

import com.anatoliapark.nursinghome.domain.base.BaseModel;
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
