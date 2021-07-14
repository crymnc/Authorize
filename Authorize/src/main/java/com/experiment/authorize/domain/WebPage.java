package com.experiment.authorize.domain;

import com.experiment.authorize.domain.base.BaseConstantModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WebPage extends BaseConstantModel {
    private List<WebPageComponent> webPageComponents;
    private List<AuthorityOption> authorityOptions;
}
