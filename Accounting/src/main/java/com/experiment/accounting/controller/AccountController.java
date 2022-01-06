package com.experiment.accounting.controller;

import com.experiment.accounting.domain.AccountCreation;
import com.experiment.accounting.domain.InstallmentGroup;
import com.experiment.accounting.entity.AccountEntity;
import com.experiment.accounting.exception.BusinessExceptions;
import com.experiment.accounting.mapper.InstallmentGroupMapper;
import com.experiment.accounting.proxy.authorize.AuthorizeProxy;
import com.experiment.accounting.proxy.authorize.model.User;
import com.experiment.accounting.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @Autowired
    InstallmentGroupMapper installmentGroupMapper;

    @Autowired
    AuthorizeProxy authorizeProxy;

    @PostMapping
    public ResponseEntity createAccount(@RequestBody AccountCreation account){
        if(account.getUserId() == null)
            throw BusinessExceptions.USER_ID_CANNOT_BE_NULL;
        User user = authorizeProxy.getUserById(account.getUserId());
        if(user == null)
            throw BusinessExceptions.USER_NOT_FOUND_BY_ID;

        AccountEntity accountEntity = accountService.findAccountByUserId(account.getUserId());
        if(accountEntity != null)
            throw BusinessExceptions.USER_ALREADY_HAS_AN_ACCOUNT;

        accountEntity = new AccountEntity();
        accountEntity.setUserId(account.getUserId());

        accountService.save(accountEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body("CREATED");
    }

    @GetMapping("/{user-id}/installment-groups")
    public ResponseEntity<List<InstallmentGroup>> getInstallmentGroups(@PathVariable(name="user-id") Long userId){
        AccountEntity accountEntity = accountService.findAccountByUserId(userId);
        if(accountEntity == null)
            throw BusinessExceptions.INSTALLMENT_GROUP_NOT_FOUND_BY_USER_ID;
        return ResponseEntity.status(HttpStatus.OK).body(installmentGroupMapper.toModelList(accountEntity.getInstallmentGroups()));
    }
}
