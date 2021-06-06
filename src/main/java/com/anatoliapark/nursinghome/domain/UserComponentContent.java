package com.anatoliapark.nursinghome.domain;

import com.anatoliapark.nursinghome.domain.base.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserComponentContent extends BaseModel {
    private String content;
    private Long componentId;
    private String componentName;
    private Long userId;
}
