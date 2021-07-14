package com.experiment.authorize.domain;

import com.experiment.authorize.domain.base.BaseConstantModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorityGroup extends BaseConstantModel {
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Long> authorities;
}
