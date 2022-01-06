package com.experiment.accounting.domain;

import com.experiment.accounting.domain.base.BaseModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InstallmentGroup extends BaseModel {

    private Long paymentType;
    private Integer numberOfInstallment;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date discontinueDate;
    private String description;
    private BigDecimal totalAmount;
}
