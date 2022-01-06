package com.experiment.accounting.entity;

import com.experiment.accounting.entity.base.BaseAuditEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "account")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountEntity extends BaseAuditEntity {

    @OneToMany(
            mappedBy = "account",
            cascade = CascadeType.ALL, fetch = FetchType.EAGER
    )
    private Set<InstallmentGroupEntity> installmentGroups;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "discontinue_date")
    private Date discontinueDate;

    public void addInstallmentGroup(InstallmentGroupEntity installmentGroupEntity){
        if(installmentGroups == null)
            installmentGroups = new HashSet<>();
        installmentGroups.add(installmentGroupEntity);
    }
}
