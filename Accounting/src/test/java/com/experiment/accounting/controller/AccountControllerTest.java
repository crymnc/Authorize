package com.experiment.accounting.controller;

import com.experiment.accounting.domain.AccountCreation;
import com.experiment.accounting.entity.AccountEntity;
import com.experiment.accounting.exception.BusinessException;
import com.experiment.accounting.exception.BusinessExceptions;
import com.experiment.accounting.mapper.InstallmentGroupMapper;
import com.experiment.accounting.proxy.authorize.AuthorizeProxy;
import com.experiment.accounting.proxy.authorize.model.User;
import com.experiment.accounting.service.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(controllers = AccountController.class)
public class AccountControllerTest {

    @MockBean
    private AccountService accountService;

    @MockBean
    private InstallmentGroupMapper installmentGroupMapper;

    @MockBean
    private AuthorizeProxy authorizeProxy;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void init() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
    }

    @Test
    public void shouldThrowExceptionWhenCreateAccountIfAccountExists() throws Exception {
        Mockito.when(accountService.findAccountByUserId(any())).thenReturn(getTestAccountEntity());
        Mockito.when(authorizeProxy.getUserById(any())).thenReturn(getTestUser());
        mockMvc.perform(MockMvcRequestBuilders.post("/api/account")
                        .with(user("admin").roles("ADMIN"))
                        .content(new ObjectMapper().writeValueAsString(getTestAccountCreation()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is5xxServerError())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof BusinessException))
                .andExpect(result -> assertEquals(result.getResolvedException().getMessage(),BusinessExceptions.USER_ALREADY_HAS_AN_ACCOUNT.getMessage()));
    }

    @Test
    public void shouldThrowExceptionWhenCreateAccountIfUserIdNotExists() throws Exception {
        Mockito.when(authorizeProxy.getUserById(any())).thenReturn(null);
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/account")
                                .with(user("admin").roles("ADMIN"))
                                .content(new ObjectMapper().writeValueAsString(getTestAccountCreation()))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is5xxServerError())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof BusinessException))
                .andExpect(result -> assertEquals(result.getResolvedException().getMessage(),BusinessExceptions.USER_NOT_FOUND_BY_ID.getMessage()));
    }

    @Test
    public void shouldCreateAccountIfThereIsNoAccountRelatedWithUserId() throws Exception {
        Mockito.when(accountService.findAccountByUserId(any())).thenReturn(null);
        Mockito.when(authorizeProxy.getUserById(any())).thenReturn(getTestUser());
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/account")
                                .with(user("admin").roles("ADMIN"))
                                .content(new ObjectMapper().writeValueAsString(getTestAccountCreation()))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(content().string("CREATED"));
    }

    private User getTestUser(){
        User user = new User();
        user.setId(1L);
        return user;
    }

    private AccountCreation getTestAccountCreation(){
        AccountCreation accountCreation = new AccountCreation();
        accountCreation.setUserId(1L);
        return accountCreation;
    }

    private AccountEntity getTestAccountEntity(){
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setId(1L);
        accountEntity.setUserId(1L);
        accountEntity.setCreatedAt(new Date());
        accountEntity.setCreatedBy(1L);
        return accountEntity;
    }
}
