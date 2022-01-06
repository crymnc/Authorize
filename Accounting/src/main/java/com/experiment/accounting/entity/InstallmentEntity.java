package com.experiment.accounting.entity;

import com.experiment.accounting.entity.base.BaseAuditEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity(name="installment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InstallmentEntity extends BaseAuditEntity {

    @ManyToOne
    @JoinColumn(name = "installment_group_id", referencedColumnName = "id")
    private InstallmentGroupEntity installmentGroup;

    @OneToMany(
            mappedBy = "installment",
            cascade= CascadeType.ALL, fetch = FetchType.EAGER
    )
    private Set<TransactionEntity> transactions;

    @Column(name="amount")
    private BigDecimal amount;

    @Column(name="due_date")
    private Date dueDate;

    @Column(name="installment_number")
    private Integer installmentNumber;

    public void addTransaction(TransactionEntity transactionEntity){
        if(transactions == null)
            transactions = new HashSet<>();
        transactions.add(transactionEntity);
    }

}
