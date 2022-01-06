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

@Entity(name="installment_group")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InstallmentGroupEntity extends BaseAuditEntity {

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private AccountEntity account;

    @OneToMany(
            mappedBy = "installmentGroup",
            cascade= CascadeType.ALL, fetch = FetchType.EAGER
    )
    @OrderBy("installmentNumber")
    private Set<InstallmentEntity> installments;

    @Column(name="number_of_installment")
    private Integer numberOfInstallment;

    @Column(name="total_amount")
    private BigDecimal totalAmount;

    @ManyToOne
    @JoinColumn(name = "payment_type_id", referencedColumnName = "id")
    private PaymentTypeEntity paymentType;

    @Column(name="discontinue_date")
    private Date discontinueDate;

    @Column(name="description")
    private String description;

    public void addInstallment(InstallmentEntity installmentEntity){
        if(installments == null)
            installments = new HashSet<>();
        installments.add(installmentEntity);
    }
}
