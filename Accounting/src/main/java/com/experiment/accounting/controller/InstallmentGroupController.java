package com.experiment.accounting.controller;

import com.experiment.accounting.domain.Installment;
import com.experiment.accounting.domain.InstallmentGroup;
import com.experiment.accounting.domain.InstallmentGroupCreation;
import com.experiment.accounting.entity.AccountEntity;
import com.experiment.accounting.entity.InstallmentGroupEntity;
import com.experiment.accounting.exception.BusinessExceptions;
import com.experiment.accounting.mapper.InstallmentGroupMapper;
import com.experiment.accounting.mapper.InstallmentMapper;
import com.experiment.accounting.service.AccountService;
import com.experiment.accounting.service.InstallmentGroupService;
import com.experiment.accounting.service.InstallmentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/installment-groups")
public class InstallmentGroupController {
    private static final Logger logger = LogManager.getLogger(InstallmentGroupController.class);
    private final AccountService accountService;
    private final InstallmentGroupService installmentGroupService;
    private final InstallmentGroupMapper installmentGroupMapper;
    private final InstallmentMapper installmentMapper;
    private final InstallmentService installmentService;

    public InstallmentGroupController(AccountService accountService, InstallmentGroupService installmentGroupService, InstallmentGroupMapper installmentGroupMapper, InstallmentMapper installmentMapper, InstallmentService installmentService) {
        this.accountService = accountService;
        this.installmentGroupService = installmentGroupService;
        this.installmentGroupMapper = installmentGroupMapper;
        this.installmentMapper = installmentMapper;
        this.installmentService = installmentService;
    }

    @PostMapping
    public ResponseEntity createInstallmentGroup(@RequestBody InstallmentGroupCreation installmentGroup){
        AccountEntity accountEntity = accountService.findAccountByUserId(installmentGroup.getUserId());
        InstallmentGroupEntity installmentGroupEntity = installmentGroupMapper.toEntity(installmentGroup);
        installmentService.createInstallments(installmentGroupEntity);
        accountEntity.addInstallmentGroup(installmentGroupEntity);
        accountService.save(accountEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body("CREATED");
    }

    @GetMapping("/{installment-group-id}")
    public ResponseEntity<InstallmentGroup> getInstallmentGroupById(@PathVariable(name="installment-group-id") Long installmentGroupId){
        InstallmentGroupEntity installmentGroupEntity = installmentGroupService.find(installmentGroupId,InstallmentGroupEntity.class).orElseThrow(() -> BusinessExceptions.INSTALLMENT_GROUP_NOT_FOUND_BY_ID);
        return ResponseEntity.status(HttpStatus.OK).body(installmentGroupMapper.toModel(installmentGroupEntity));
    }

    @GetMapping("{installment-group-id}/installments")
    public ResponseEntity<List<Installment>> getInstallmentsByGroupId(@PathVariable(name="installment-group-id") Long installmentGroupId){
        InstallmentGroupEntity installmentGroupEntity = installmentGroupService.find(installmentGroupId,InstallmentGroupEntity.class).orElseThrow(() -> BusinessExceptions.INSTALLMENTS_NOT_FOUND_BY_GROUP_ID);
        return ResponseEntity.status(HttpStatus.OK).body(installmentMapper.toModelList(installmentGroupEntity.getInstallments()).stream().sorted(Comparator.comparing(Installment::getInstallmentNumber)).collect(Collectors.toList()));
    }

    @DeleteMapping("/{installment-group-id}")
    public ResponseEntity cancelInstallmentGroup(@PathVariable(name="installment-group-id")Long installmentGroupId){
        InstallmentGroupEntity installmentGroupEntity = installmentGroupService.find(installmentGroupId,InstallmentGroupEntity.class).orElseThrow(() -> BusinessExceptions.INSTALLMENT_GROUP_NOT_FOUND_BY_ID);
        installmentGroupEntity.getInstallments().forEach(installmentEntity -> installmentEntity.getTransactions().forEach(transactionEntity -> transactionEntity.setCancelDate(new Date())));
        installmentGroupEntity.setDiscontinueDate(new Date());
        installmentGroupService.save(installmentGroupEntity);
        return ResponseEntity.status(HttpStatus.OK).body("DELETED");
    }
}
