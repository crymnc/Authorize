package com.experiment.accounting.entity;

import com.experiment.accounting.entity.base.BaseConstantEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Entity(name="transaction_type")
@Getter
@Setter
@NoArgsConstructor
public class TransactionTypeEntity extends BaseConstantEntity {
}
