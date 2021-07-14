package com.experiment.authorize.test.service;

import com.experiment.authorize.entity.base.BaseEntity;
import com.experiment.authorize.repository.base.EntityRepository;
import com.experiment.authorize.service.base.EntityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
public class EntityServiceTest {

    private EntityService entityService;
    @Mock
    private EntityRepository<BaseEntity> entityRepository;

    @BeforeEach
    void setUp(){
        entityService = new EntityService();
    }



}
