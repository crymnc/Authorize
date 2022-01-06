package com.experiment.accounting.service;

import com.experiment.accounting.entity.AccountEntity;
import com.experiment.accounting.repository.base.EntityRepository;
import com.experiment.accounting.service.base.EntityService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class EntityServiceTest {

    @InjectMocks
    private EntityService entityService;

    @Mock
    private EntityRepository entityRepository;

    @Test
    public void canSave(){
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setUserId(3L);
        accountEntity.setCreatedBy(3L);
        accountEntity.setCreatedAt(new Date());
        ArgumentCaptor<AccountEntity> accountArgumentCaptor = ArgumentCaptor.forClass(AccountEntity.class);
        entityService.save(accountEntity);
        verify(entityRepository).save(accountArgumentCaptor.capture());
        verify(entityRepository).save(accountEntity);

        AccountEntity capturedAccountEntity = accountArgumentCaptor.getValue();
        assertThat(capturedAccountEntity).isEqualTo(accountEntity);
    }

    @Test
    public void willReturnNullWhenSaveIfEntityNull(){
        AccountEntity entity = entityService.save(null);
        verify(entityRepository, never()).save(any());
        Assertions.assertNull(entity);
    }

    @Test
    public void canFind(){
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setId(3L);
        entityService.find(accountEntity.getId(),AccountEntity.class);
        verify(entityRepository).findOne(any(Example.class));

    }

    @Test
    public void willReturnEmptyOptionalWhenFindIfEntityIdNull(){
        ExampleMatcher matcher = ExampleMatcher.matchingAll();
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setId(null);
        Optional optional = entityService.find(accountEntity.getId(), AccountEntity.class);
        verify(entityRepository,never()).findOne(Example.of(accountEntity,matcher));
        Assertions.assertEquals(optional, Optional.empty());
    }

    @Test
    public void canFindAll(){
        entityService.findAll(AccountEntity.class);
        verify(entityRepository).findAll(any(Example.class));
    }

    @Test
    public void canDelete(){
        entityService.delete(3L,AccountEntity.class);
        verify(entityRepository).delete(any());
    }
}