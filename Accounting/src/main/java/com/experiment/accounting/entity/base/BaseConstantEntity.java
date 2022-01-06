package com.experiment.accounting.entity.base;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public class BaseConstantEntity extends BaseAuditEntity{

    @Column(name="name")
    @NotEmpty(message = "{constantEntity.name.NotEmpty}")
    @Length(max = 30,message = "{constantEntity.name.Length}")
    protected String name;

    @Column(name = "dsc")
    @Length(max = 255, message = "{constantEntity.desc.Length}")
    protected String dsc;

    @Column(name="discontinue_date")
    @Temporal(TemporalType.TIMESTAMP)
    protected Date discontinueDate;

}
