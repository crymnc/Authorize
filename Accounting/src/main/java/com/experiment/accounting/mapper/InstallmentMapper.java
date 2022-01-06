package com.experiment.accounting.mapper;

import com.experiment.accounting.domain.Installment;
import com.experiment.accounting.entity.InstallmentEntity;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public class InstallmentMapper {

    public Installment toModel(InstallmentEntity installmentEntity){
        if(installmentEntity == null)
            return null;

        Installment installment = new Installment();
        installment.setId(installmentEntity.getId());
        installment.setInstallmentNumber(installmentEntity.getInstallmentNumber());
        installment.setDueDate(installmentEntity.getDueDate());
        installment.setAmount(installmentEntity.getAmount());
        installment.setCreatedAt(installmentEntity.getCreatedAt());
        installment.setCreatedBy(installment.getCreatedBy());
        return installment;
    }

    public List<Installment> toModelList(Set<InstallmentEntity> installmentEntitySet){
        if(installmentEntitySet == null)
            return null;
        return installmentEntitySet.stream().map(this::toModel).collect(Collectors.toList());
    }
}
