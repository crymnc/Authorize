package com.experiment.accounting.mapper;

import com.experiment.accounting.domain.InstallmentGroup;
import com.experiment.accounting.domain.InstallmentGroupCreation;
import com.experiment.accounting.entity.AccountEntity;
import com.experiment.accounting.entity.InstallmentGroupEntity;
import com.experiment.accounting.entity.PaymentTypeEntity;
import com.experiment.accounting.service.AccountService;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public class InstallmentGroupMapper {

    @Autowired
    AccountService accountService;

    public InstallmentGroup toModel(InstallmentGroupEntity installmentGroupEntity){
        if(installmentGroupEntity == null)
            return null;

        InstallmentGroup installmentGroup = new InstallmentGroup();
        installmentGroup.setId(installmentGroupEntity.getId());
        installmentGroup.setNumberOfInstallment(installmentGroupEntity.getNumberOfInstallment());
        installmentGroup.setDiscontinueDate(installmentGroupEntity.getDiscontinueDate());
        installmentGroup.setPaymentType(installmentGroupEntity.getPaymentType().getId());
        installmentGroup.setCreatedAt(installmentGroupEntity.getCreatedAt());
        installmentGroup.setCreatedBy(installmentGroupEntity.getCreatedBy());
        installmentGroup.setDescription(installmentGroupEntity.getDescription());
        installmentGroup.setTotalAmount(installmentGroupEntity.getTotalAmount());
        return installmentGroup;
    }

    public InstallmentGroupEntity toEntity(InstallmentGroupCreation installmentGroupCreation){
        if(installmentGroupCreation == null)
            return null;

        InstallmentGroupEntity installmentGroupEntity = new InstallmentGroupEntity();
        installmentGroupEntity.setNumberOfInstallment(installmentGroupCreation.getNumberOfInstallment());
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setId(accountService.findAccountByUserId(installmentGroupCreation.getUserId()).getId());
        installmentGroupEntity.setAccount(accountEntity);
        installmentGroupEntity.setDiscontinueDate(installmentGroupCreation.getDiscontinueDate());
        PaymentTypeEntity paymentTypeEntity = new PaymentTypeEntity();
        paymentTypeEntity.setId(installmentGroupCreation.getPaymentType());
        installmentGroupEntity.setPaymentType(paymentTypeEntity);
        installmentGroupEntity.setDescription(installmentGroupCreation.getDescription());
        installmentGroupEntity.setTotalAmount(installmentGroupCreation.getTotalAmount());
        return installmentGroupEntity;
    }

    public List<InstallmentGroup> toModelList(Set<InstallmentGroupEntity> installmentGroupEntitySet){
        if(installmentGroupEntitySet == null)
            return null;
        return installmentGroupEntitySet.stream().map(this::toModel).collect(Collectors.toList());
    }
}
