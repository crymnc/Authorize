package com.experiment.accounting.controller;

import com.experiment.accounting.domain.TransactionCreation;
import com.experiment.accounting.entity.TransactionEntity;
import com.experiment.accounting.exception.BusinessException;
import com.experiment.accounting.exception.BusinessExceptions;
import com.experiment.accounting.service.TransactionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(controllers = TransactionController.class)
public class TransactionControllerTest {

    @MockBean
    private TransactionService transactionService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void init() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
    }

    @Test
    public void shouldCreateTransaction() throws Exception{
        TransactionCreation transactionCreation = getTestTransactionCreation();
        Mockito.doNothing().when(transactionService).createTransaction(any());
        mockMvc.perform(MockMvcRequestBuilders.post("/api/transactions")
                        .with(user("admin").roles("ADMIN"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(transactionCreation))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(content().string("CREATED"));
    }

    @Test
    public void shouldCancelTransaction() throws Exception{
        Mockito.when(transactionService.find(any(),eq(TransactionEntity.class))).thenReturn(Optional.of(getTestTransactionEntity()));
        Mockito.when(transactionService.save(any())).thenReturn(null);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/transactions/1")
                        .with(user("admin").roles("ADMIN"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().string("DELETED"));
    }

    @Test
    public void shouldThrowExceptionWhenCancelTransactionIfTransactionNotFound() throws Exception{
        Mockito.when(transactionService.find(any(),eq(TransactionEntity.class))).thenReturn(Optional.empty());
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/transactions/1")
                        .with(user("admin").roles("ADMIN"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is5xxServerError())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof BusinessException))
                .andExpect(result -> assertEquals(result.getResolvedException().getMessage(), BusinessExceptions.TRANSACTION_NOT_FOUND_BY_ID.getMessage()));
    }

    private TransactionCreation getTestTransactionCreation(){
        TransactionCreation transactionCreation = new TransactionCreation();
        transactionCreation.setTransactionType(1L);
        transactionCreation.setAmount(new BigDecimal(100));
        transactionCreation.setTransactionDate(new Date());
        transactionCreation.setDescription("Test");
        transactionCreation.setPaymentType(1L);
        transactionCreation.setInstallmentGroupId(1L);
        return transactionCreation;
    }

    private TransactionEntity getTestTransactionEntity(){
        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setId(1L);
        return transactionEntity;
    }
}
