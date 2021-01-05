package com.anatoliapark.nursinghome.domain;

import com.anatoliapark.nursinghome.domain.base.BaseConstantModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role extends BaseConstantModel {
    @JsonIgnore
    private List<AuthorityGroup> authorityGroups;
    @JsonIgnore
    private List<UserComponent> userComponents;

}
