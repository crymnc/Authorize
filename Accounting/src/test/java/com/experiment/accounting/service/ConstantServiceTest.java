package com.experiment.accounting.service;

import com.experiment.accounting.domain.base.BaseConstantModel;
import com.experiment.accounting.entity.PaymentTypeEntity;
import com.experiment.accounting.exception.BusinessException;
import com.experiment.accounting.mapper.ConstantMapper;
import com.experiment.accounting.repository.base.EntityRepository;
import com.experiment.accounting.service.base.EntityService;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.data.domain.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ConstantServiceTest {

    @InjectMocks
    private ConstantService constantService;

    @Mock
    private EntityRepository entityRepository;

    private ConstantMapper mapper;


    @BeforeEach
    public void setUp(){
        mapper = new ConstantMapper();
        constantService = new ConstantService(mapper,new CaffeineCacheManager(),entityRepository);
    }

    @Test
    public void canFind(){
        Mockito.when(entityRepository.findOne(any(Example.class))).thenReturn(any());
        constantService.find("paymentType", PaymentTypeEntity.class);
        verify(entityRepository).findOne(any(Example.class));
    }
    @Test
    public void shouldFindAllAndNoneFieldCannotBeNullExceptDscDate(){
        List<PaymentTypeEntity> paymentTypeEntityList = createTestList();
        Mockito.when(entityRepository.findAll(any(Example.class))).thenReturn(paymentTypeEntityList);
        List<BaseConstantModel> paymentModels = constantService.findAllByConstantName("payment-type");
        verify(entityRepository).findAll(any(Example.class));
        paymentModels.forEach(paymentModel -> {
            Assertions.assertNotNull(paymentModel.getId());
            Assertions.assertNotNull(paymentModel.getName());
            Assertions.assertNotNull(paymentModel.getDsc());
            Assertions.assertNotNull(paymentModel.getCreatedAt());
            Assertions.assertNotNull(paymentModel.getCreatedBy());
        });
    }

    @Test
    public void shouldFindAllAndNoneFieldCannotBeNull(){
        List<PaymentTypeEntity> paymentTypeEntityList = createTestListWithDiscontinueDate();
        Mockito.when(entityRepository.findAll(any(Example.class))).thenReturn(paymentTypeEntityList);
        List<BaseConstantModel> paymentModels = constantService.findAllByConstantName("payment-type");
        verify(entityRepository).findAll(any(Example.class));
        paymentModels.forEach(paymentModel -> {
            Assertions.assertNotNull(paymentModel.getId());
            Assertions.assertNotNull(paymentModel.getName());
            Assertions.assertNotNull(paymentModel.getDsc());
            Assertions.assertNotNull(paymentModel.getCreatedAt());
            Assertions.assertNotNull(paymentModel.getCreatedBy());
            Assertions.assertNotNull(paymentModel.getDiscontinueDate());
        });
    }

    @Test
    public void shouldDeleteByConstantNameAndId(){
        constantService.deleteByConstantNameAndId("payment-type",1L);
    }

    @Test
    public void shouldThrowErrorIfIdNotNullWhenSaveByConstantName(){
        BaseConstantModel paymentTypeModel = new BaseConstantModel();
        paymentTypeModel.setId(1L);
        paymentTypeModel.setName("test1");
        paymentTypeModel.setDsc("test2");
        paymentTypeModel.setCreatedBy(1L);
        paymentTypeModel.setCreatedAt(new Date());
        AssertionsForClassTypes.assertThatThrownBy(() -> constantService.saveByConstantName("payment-type",paymentTypeModel))
                .isInstanceOf(BusinessException.class)
                .hasMessageContaining("If id field is not null, try to update payment-type");
    }

    @Test
    public void shouldSaveByConstantName(){
        PaymentTypeEntity paymentTypeEntity = new PaymentTypeEntity();
        paymentTypeEntity.setId(1L);
        paymentTypeEntity.setName("test1");
        paymentTypeEntity.setDsc("test1");
        paymentTypeEntity.setCreatedBy(1L);
        paymentTypeEntity.setCreatedAt(new Date());
        BaseConstantModel paymentTypeModel = mapper.toModel(paymentTypeEntity);
        paymentTypeModel.setId(null);
        ConstantService spyConstantService = Mockito.spy(constantService);
        Mockito.doReturn(paymentTypeEntity).when(((EntityService)spyConstantService)).save(any());
        PaymentTypeEntity savedPaymentTypeEntity = spyConstantService.saveByConstantName("payment-type",paymentTypeModel);
        verify(spyConstantService).saveByConstantName("payment-type",paymentTypeModel);
        Assertions.assertEquals(savedPaymentTypeEntity,paymentTypeEntity);
    }

    @Test
    public void shouldUpdateByConstantName(){
        PaymentTypeEntity paymentTypeEntity = new PaymentTypeEntity();
        paymentTypeEntity.setId(1L);
        paymentTypeEntity.setName("test1");
        paymentTypeEntity.setDsc("test1");
        paymentTypeEntity.setCreatedBy(1L);
        paymentTypeEntity.setCreatedAt(new Date());
        BaseConstantModel paymentTypeModel = mapper.toModel(paymentTypeEntity);
        ConstantService spyConstantService = Mockito.spy(constantService);
        Mockito.doReturn(paymentTypeEntity).when(((EntityService)spyConstantService)).save(any());
        PaymentTypeEntity savedPaymentTypeEntity = spyConstantService.updateByConstantName("payment-type",paymentTypeModel);
        verify(spyConstantService).updateByConstantName("payment-type",paymentTypeModel);
        Assertions.assertEquals(savedPaymentTypeEntity,paymentTypeEntity);
    }

    @Test
    public void shouldThrowErrorIfIdNullWhenUpdateByConstantName(){
        BaseConstantModel paymentTypeModel = new BaseConstantModel();
        paymentTypeModel.setName("test1");
        paymentTypeModel.setDsc("test2");
        paymentTypeModel.setCreatedBy(1L);
        paymentTypeModel.setCreatedAt(new Date());
        AssertionsForClassTypes.assertThatThrownBy(() -> constantService.updateByConstantName("payment-type",paymentTypeModel))
                .isInstanceOf(BusinessException.class)
                .hasMessageContaining("If id field is null, try to create payment-type");
    }

    private List<PaymentTypeEntity> createTestList(){
        List<PaymentTypeEntity> paymentTypeEntityList = new ArrayList<>();
        PaymentTypeEntity paymentTypeEntity1 = new PaymentTypeEntity();
        paymentTypeEntity1.setId(1L);
        paymentTypeEntity1.setName("Test 1");
        paymentTypeEntity1.setDsc("Test 1");
        paymentTypeEntity1.setCreatedAt(new Date());
        paymentTypeEntity1.setCreatedBy(1L);
        PaymentTypeEntity paymentTypeEntity2 = new PaymentTypeEntity();
        paymentTypeEntity2.setId(2L);
        paymentTypeEntity2.setName("Test 2");
        paymentTypeEntity2.setDsc("Test 2");
        paymentTypeEntity2.setCreatedAt(new Date());
        paymentTypeEntity2.setCreatedBy(1L);

        paymentTypeEntityList.add(paymentTypeEntity1);
        paymentTypeEntityList.add(paymentTypeEntity2);
        return paymentTypeEntityList;
    }

    private List<PaymentTypeEntity> createTestListWithDiscontinueDate(){
        return createTestList().stream().peek(paymentTypeEntity -> paymentTypeEntity.setDiscontinueDate(new Date())).collect(Collectors.toList());
    }


}
