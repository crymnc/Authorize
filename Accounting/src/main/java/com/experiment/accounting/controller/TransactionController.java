package com.experiment.accounting.controller;

import com.experiment.accounting.domain.TransactionCreation;
import com.experiment.accounting.entity.TransactionEntity;
import com.experiment.accounting.exception.BusinessExceptions;
import com.experiment.accounting.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping
    public ResponseEntity createTransaction(@RequestBody TransactionCreation transactionCreation){
        transactionService.createTransaction(transactionCreation);
        return ResponseEntity.status(HttpStatus.CREATED).body("CREATED");
    }

    @DeleteMapping("/{transaction-id}")
    public ResponseEntity cancelTransaction(@PathVariable(name="transaction-id") Long transactionId){
        TransactionEntity transactionEntity = transactionService.find(transactionId, TransactionEntity.class).orElseThrow(() -> BusinessExceptions.TRANSACTION_NOT_FOUND_BY_ID);
        transactionEntity.setCancelDate(new Date());
        transactionService.save(transactionEntity);
        return ResponseEntity.status(HttpStatus.OK).body("DELETED");
    }
}
