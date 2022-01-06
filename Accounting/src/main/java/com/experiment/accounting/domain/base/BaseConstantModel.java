package com.experiment.accounting.domain.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseConstantModel extends BaseModel{
    private String name, dsc;
    private Date discontinueDate;
}
