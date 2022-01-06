package com.experiment.accounting.controller;

import com.experiment.accounting.domain.Transaction;
import com.experiment.accounting.entity.InstallmentEntity;
import com.experiment.accounting.entity.PaymentTypeEntity;
import com.experiment.accounting.exception.BusinessException;
import com.experiment.accounting.exception.BusinessExceptions;
import com.experiment.accounting.mapper.TransactionMapper;
import com.experiment.accounting.service.InstallmentService;
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
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(controllers = InstallmentController.class)
public class InstallmentControllerTest {

    @MockBean
    private InstallmentService installmentService;

    @MockBean
    private TransactionMapper transactionMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void init() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
    }

    @Test
    public void shouldFindTransactionsByInstallmentId() throws Exception{
        List<Transaction> transactions = getTestTransactionList();
        Mockito.when(installmentService.find(any(), eq(InstallmentEntity.class))).thenReturn(Optional.of(getTestInstallmentEntity()));
        Mockito.when(transactionMapper.toModelList(any())).thenReturn(transactions);
        mockMvc.perform( MockMvcRequestBuilders.get("/api/installments/1/transactions")
                        .with(user("admin").roles("ADMIN"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(transactions)));
    }

    @Test
    public void shouldThrowExceptionWhenFindTransactionsByInstallmentIdIfInstallmentNotExists() throws Exception{
        Mockito.when(installmentService.find(any(), eq(InstallmentEntity.class))).thenReturn(Optional.empty());
        mockMvc.perform( MockMvcRequestBuilders.get("/api/installments/1/transactions")
                        .with(user("admin").roles("ADMIN"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is5xxServerError())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof BusinessException))
                .andExpect(result -> assertEquals(result.getResolvedException().getMessage(), BusinessExceptions.INSTALLMENT_NOT_FOUND_BY_ID.getMessage()));
    }


    private List<Transaction> getTestTransactionList(){
        Date date = new Date();
        Transaction transaction1 = new Transaction();
        transaction1.setId(1L);
        transaction1.setTransactionType(1L);
        transaction1.setTransactionDate(date);
        transaction1.setDescription("Test 1");
        transaction1.setPaymentType(1L);
        transaction1.setAmount(new BigDecimal(150));
        transaction1.setCreatedBy(1L);
        transaction1.setCreatedAt(date);

        Transaction transaction2 = new Transaction();
        transaction2.setId(2L);
        transaction2.setTransactionType(1L);
        transaction2.setTransactionDate(date);
        transaction2.setDescription("Test 2");
        transaction2.setPaymentType(1L);
        transaction2.setAmount(new BigDecimal(100));
        transaction2.setCreatedBy(1L);
        transaction2.setCreatedAt(date);


        return List.of(transaction1,transaction2);
    }

    private InstallmentEntity getTestInstallmentEntity(){
        InstallmentEntity installmentEntity = new InstallmentEntity();
        installmentEntity.setId(1L);
        installmentEntity.setInstallmentNumber(1);
        installmentEntity.setDueDate(new Date());
        installmentEntity.setAmount(new BigDecimal(100));
        installmentEntity.setCreatedAt(new Date());
        installmentEntity.setCreatedBy(1L);
        return installmentEntity;
    }
}
