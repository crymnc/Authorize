package com.experiment.authorize.domain;

import com.experiment.authorize.domain.base.BaseConstantModel;
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
