package com.experiment.accounting.entity;

import com.experiment.accounting.entity.base.BaseAuditEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity(name="transaction")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionEntity extends BaseAuditEntity {

    @ManyToOne
    @JoinColumn(name = "installment_id", referencedColumnName = "id")
    private InstallmentEntity installment;

    @Column(name="amount")
    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "transaction_type_id", referencedColumnName = "id")
    private TransactionTypeEntity transactionType;

    @Column(name="transaction_date")
    private Date transactionDate;

    @ManyToOne
    @JoinColumn(name = "payment_type_id", referencedColumnName = "id")
    private PaymentTypeEntity paymentType;

    @Column(name="cancel_date")
    private Date cancelDate;

    @Column(name="description")
    private String description;
}
