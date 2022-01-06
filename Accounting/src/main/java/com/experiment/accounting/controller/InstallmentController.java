package com.experiment.accounting.controller;

import com.experiment.accounting.domain.Transaction;
import com.experiment.accounting.entity.InstallmentEntity;
import com.experiment.accounting.exception.BusinessExceptions;
import com.experiment.accounting.mapper.TransactionMapper;
import com.experiment.accounting.service.InstallmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/installments")
public class InstallmentController {

    private final InstallmentService installmentService;
    private final TransactionMapper transactionMapper;

    public InstallmentController(InstallmentService installmentService,TransactionMapper transactionMapper){
        this.installmentService = installmentService;
        this.transactionMapper = transactionMapper;
    }

    @GetMapping("/{installment-id}/transactions")
    public ResponseEntity<List<Transaction>> getTransactionsByInstallmentId(@PathVariable(name="installment-id") Long installmentId){
        InstallmentEntity installmentEntity = installmentService.find(installmentId, InstallmentEntity.class).orElseThrow(() -> BusinessExceptions.INSTALLMENT_NOT_FOUND_BY_ID);
        return ResponseEntity.status(HttpStatus.OK).body(transactionMapper.toModelList(installmentEntity.getTransactions()));
    }
}
