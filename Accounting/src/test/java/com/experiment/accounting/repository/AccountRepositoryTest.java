package com.experiment.accounting.repository;

import com.experiment.accounting.config.WithMockOAuth2Scope;
import com.experiment.accounting.entity.AccountEntity;
import com.experiment.accounting.proxy.authorize.AuthorizeProxy;
import com.experiment.accounting.proxy.authorize.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;

import java.util.Date;

@DataJpaTest
@ComponentScan("com.experiment.accounting")
public class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @MockBean
    private AuthorizeProxy authorizeProxy;

    @BeforeEach
    public void setUp(){
        User user = new User();
        user.setName("crymnc");
        user.setId(1L);
        Mockito.doReturn(user).when(authorizeProxy).getUserByUsername("test");
    }

    @Test
    @WithMockOAuth2Scope(scope = "test")
    public void shouldFindAccountByUserId(){
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setUserId(1L);
        accountEntity.setCreatedBy(1L);
        accountEntity.setCreatedAt(new Date());
        accountRepository.save(accountEntity);

        AccountEntity savedAccountEntity = accountRepository.findByUserId(1L);
        Assertions.assertNotNull(savedAccountEntity.getId());
        Assertions.assertEquals(savedAccountEntity.getUserId(),accountEntity.getUserId());
    }
}
