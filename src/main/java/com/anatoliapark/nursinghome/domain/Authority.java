package com.anatoliapark.nursinghome.domain;

import com.anatoliapark.nursinghome.domain.base.BaseConstantModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Authority extends BaseConstantModel {
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<AuthorityOption> authorityOptions;
}
