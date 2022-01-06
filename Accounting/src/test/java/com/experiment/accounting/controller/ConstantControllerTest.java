package com.experiment.accounting.controller;

import com.experiment.accounting.domain.base.BaseConstantModel;
import com.experiment.accounting.entity.PaymentTypeEntity;
import com.experiment.accounting.service.ConstantService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
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

import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(controllers = ConstantController.class)
public class ConstantControllerTest {

    @MockBean
    private ConstantService constantService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void init() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
    }

    @Test
    public void shouldFindAllConstantsByName() throws Exception{
        Mockito.when(constantService.findAllByConstantName(any())).thenReturn(getTestConstantList());
        mockMvc.perform( MockMvcRequestBuilders.get("/api/constants/payment-type")
                    .with(user("admin").roles("ADMIN"))
                    .content(new ObjectMapper().writeValueAsString(getTestConstantList()))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(getTestConstantList())));
    }

    @Test
    public void shouldDeleteById() throws Exception{
        Mockito.doNothing().when(constantService).deleteByConstantNameAndId(any(),any());
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/constants/payment-type/1")
                        .with(user("admin").roles("ADMIN"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().string("DELETED"));
    }

    @Test
    public void shouldSaveConstant() throws Exception{
        Mockito.when(constantService.save(any())).thenReturn(getTestBaseConstantEntity());
        mockMvc.perform(MockMvcRequestBuilders.post("/api/constants/payment-type")
                        .with(user("admin").roles("ADMIN"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(getTestBaseConstantModel()))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(content().string("CREATED"));
    }

    @Test
    public void shouldUpdateConstant() throws Exception{
        Mockito.when(constantService.save(any())).thenReturn(getTestBaseConstantEntity());
        mockMvc.perform(MockMvcRequestBuilders.put("/api/constants/payment-type")
                        .with(user("admin").roles("ADMIN"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(getTestBaseConstantModel()))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().string("UPDATED"));
    }

    private List<BaseConstantModel> getTestConstantList(){
        BaseConstantModel paymentTypeEntity1 = new BaseConstantModel();
        paymentTypeEntity1.setId(1L);
        paymentTypeEntity1.setName("Cash");
        paymentTypeEntity1.setDsc("Cash");
        paymentTypeEntity1.setCreatedAt(new Date());
        paymentTypeEntity1.setCreatedBy(1L);
        BaseConstantModel paymentTypeEntity2 = new BaseConstantModel();
        paymentTypeEntity2.setId(2L);
        paymentTypeEntity2.setName("Credit");
        paymentTypeEntity2.setDsc("Credit");
        paymentTypeEntity2.setCreatedAt(new Date());
        paymentTypeEntity2.setCreatedBy(1L);

        return List.of(paymentTypeEntity1,paymentTypeEntity2);
    }

    private BaseConstantModel getTestBaseConstantModel(){
        BaseConstantModel paymentTypeModel = new BaseConstantModel();
        paymentTypeModel.setId(1L);
        paymentTypeModel.setName("Cash");
        paymentTypeModel.setDsc("Cash");
        paymentTypeModel.setCreatedAt(new Date());
        paymentTypeModel.setCreatedBy(1L);
        return paymentTypeModel;
    }

    private PaymentTypeEntity getTestBaseConstantEntity(){
        PaymentTypeEntity paymentTypeEntity = new PaymentTypeEntity();
        paymentTypeEntity.setId(1L);
        paymentTypeEntity.setName("Cash");
        paymentTypeEntity.setDsc("Cash");
        paymentTypeEntity.setCreatedAt(new Date());
        paymentTypeEntity.setCreatedBy(1L);
        return paymentTypeEntity;
    }
}
