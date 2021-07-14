package com.experiment.authorize.domain;

import com.experiment.authorize.domain.base.BaseConstantModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserComponent extends BaseConstantModel {
    @JsonIgnore
    private List<UserComponentContent> contents;

}
