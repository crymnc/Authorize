package com.experiment.authorize.util.hierarchy;

import com.experiment.authorize.entity.base.BaseConstantEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConstantHierarchy {

    String name;
    String desc;
    Class<? extends BaseConstantEntity> clazz;
    List<ConstantHierarchy> subConstants = new ArrayList<>();
}
