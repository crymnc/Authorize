package com.anatoliapark.nursinghome.domain;

import com.anatoliapark.nursinghome.domain.base.BaseConstantModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WebPageComponent extends BaseConstantModel {
    private WebPageComponentType webPageComponentType;
    private Set<AuthorityOption> authorityOptions;
}
