package com.experiment.accounting.utils;

import com.experiment.accounting.entity.base.BaseConstantEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConstantReference {

    String name;
    String desc;
    Class<? extends BaseConstantEntity> clazz;
    List<ConstantReference> subConstants = new ArrayList<>();
}
