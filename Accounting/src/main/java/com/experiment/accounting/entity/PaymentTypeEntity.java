package com.experiment.accounting.entity;

import com.experiment.accounting.entity.base.BaseConstantEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Entity(name="payment_type")
@Getter
@Setter
@NoArgsConstructor
public class PaymentTypeEntity extends BaseConstantEntity {
}
