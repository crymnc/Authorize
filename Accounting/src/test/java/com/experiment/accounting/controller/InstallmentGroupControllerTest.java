package com.experiment.accounting.controller;

import com.experiment.accounting.domain.Installment;
import com.experiment.accounting.domain.InstallmentGroup;
import com.experiment.accounting.domain.InstallmentGroupCreation;
import com.experiment.accounting.entity.AccountEntity;
import com.experiment.accounting.entity.InstallmentEntity;
import com.experiment.accounting.entity.InstallmentGroupEntity;
import com.experiment.accounting.exception.BusinessException;
import com.experiment.accounting.exception.BusinessExceptions;
import com.experiment.accounting.mapper.InstallmentGroupMapper;
import com.experiment.accounting.mapper.InstallmentMapper;
import com.experiment.accounting.service.AccountService;
import com.experiment.accounting.service.InstallmentGroupService;
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
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(controllers = InstallmentGroupController.class)
public class InstallmentGroupControllerTest {
    @MockBean
    private AccountService accountService;
    @MockBean
    private InstallmentGroupService installmentGroupService;
    @MockBean
    private InstallmentGroupMapper installmentGroupMapper;
    @MockBean
    private InstallmentMapper installmentMapper;
    @MockBean
    private InstallmentService installmentService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void init() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
    }

    @Test
    public void shouldCreateInstallmentGroup() throws Exception{
        Mockito.when(accountService.findAccountByUserId(any())).thenReturn(getTestAccountEntity());
        Mockito.when(installmentGroupMapper.toEntity(any())).thenReturn(getTestInstallmentGroupEntity());
        Mockito.doNothing().when(installmentService).createInstallments(any());
        Mockito.when(accountService.save(any())).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/installment-groups")
                        .with(user("admin").roles("ADMIN"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(getTestInstallmentGroupCreation()))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(content().string("CREATED"));
    }

    @Test
    public void shouldGetInstallmentGroups() throws Exception{
        Mockito.when(installmentGroupService.find(any(),eq(InstallmentGroupEntity.class))).thenReturn(Optional.of(getTestInstallmentGroupEntity()));
        Mockito.when(installmentGroupMapper.toModel(any())).thenReturn(getTestInstallmentGroup());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/installment-groups/1")
                        .with(user("admin").roles("ADMIN"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(getTestInstallmentGroup())));
    }

    @Test
    public void shouldThrowErrorWhenGetInstallmentGroupsIfInstGroupNotFound() throws Exception{
        Mockito.when(installmentGroupService.find(any(),eq(InstallmentGroupEntity.class))).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/installment-groups/1")
                        .with(user("admin").roles("ADMIN"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is5xxServerError())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof BusinessException))
                .andExpect(result -> assertEquals(result.getResolvedException().getMessage(), BusinessExceptions.INSTALLMENT_GROUP_NOT_FOUND_BY_ID.getMessage()));
    }

    @Test
    public void shouldGetInstallmentsByGroupId() throws Exception{
        List<Installment> installments = getTestInstallments();
        InstallmentGroupEntity installmentGroupEntity = getTestInstallmentGroupEntity();
        installmentGroupEntity.setInstallments(getTestInstallmentEntities());
        Mockito.when(installmentGroupService.find(any(),eq(InstallmentGroupEntity.class))).thenReturn(Optional.of(installmentGroupEntity));
        Mockito.when(installmentMapper.toModelList(any())).thenReturn(installments);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/installment-groups/1/installments")
                        .with(user("admin").roles("ADMIN"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(installments)));
    }

    @Test
    public void shouldThrowExceptionWhenGetInstallmentsByGroupIdIfInstGroupNotFound() throws Exception{
        Mockito.when(installmentGroupService.find(any(),eq(InstallmentGroupEntity.class))).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/installment-groups/1/installments")
                        .with(user("admin").roles("ADMIN"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is5xxServerError())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof BusinessException))
                .andExpect(result -> assertEquals(result.getResolvedException().getMessage(), BusinessExceptions.INSTALLMENTS_NOT_FOUND_BY_GROUP_ID.getMessage()));
    }


    private AccountEntity getTestAccountEntity(){
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setUserId(1L);
        accountEntity.setCreatedBy(1L);
        accountEntity.setCreatedAt(new Date());
        return accountEntity;
    }

    private InstallmentGroupCreation getTestInstallmentGroupCreation(){
        InstallmentGroupCreation installmentGroupCreation = new InstallmentGroupCreation();
        installmentGroupCreation.setNumberOfInstallment(6);
        installmentGroupCreation.setDescription("Test");
        installmentGroupCreation.setPaymentType(1L);
        installmentGroupCreation.setTotalAmount(new BigDecimal(1000));
        installmentGroupCreation.setUserId(1L);
        return installmentGroupCreation;
    }

    private InstallmentGroupEntity getTestInstallmentGroupEntity(){
        InstallmentGroupEntity installmentGroupEntity = new InstallmentGroupEntity();
        installmentGroupEntity.setId(1L);
        installmentGroupEntity.setNumberOfInstallment(6);
        installmentGroupEntity.setDescription("Test");
        installmentGroupEntity.setTotalAmount(new BigDecimal(1000));
        return installmentGroupEntity;
    }

    private InstallmentGroup getTestInstallmentGroup(){
        InstallmentGroup installmentGroup = new InstallmentGroup();
        installmentGroup.setId(1L);
        installmentGroup.setNumberOfInstallment(6);
        installmentGroup.setDescription("Test");
        installmentGroup.setTotalAmount(new BigDecimal(1000));
        return installmentGroup;
    }

    private List<Installment> getTestInstallments(){
        Installment installment1 = new Installment();
        installment1.setInstallmentNumber(1);
        installment1.setDueDate(new Date());
        installment1.setAmount(new BigDecimal(100));

        Installment installment2 = new Installment();
        installment2.setInstallmentNumber(2);
        installment2.setDueDate(new Date());
        installment2.setAmount(new BigDecimal(100));

        return List.of(installment1, installment2);
    }

    private Set<InstallmentEntity> getTestInstallmentEntities(){
        InstallmentEntity installment1 = new InstallmentEntity();
        installment1.setInstallmentNumber(1);
        installment1.setDueDate(new Date());
        installment1.setAmount(new BigDecimal(100));

        InstallmentEntity installment2 = new InstallmentEntity();
        installment2.setInstallmentNumber(2);
        installment2.setDueDate(new Date());
        installment2.setAmount(new BigDecimal(100));

        return Set.of(installment1, installment2);
    }
}
