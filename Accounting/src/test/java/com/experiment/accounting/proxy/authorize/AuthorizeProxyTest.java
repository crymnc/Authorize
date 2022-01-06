package com.experiment.accounting.proxy.authorize;

import com.experiment.accounting.config.WithMockOAuth2Scope;
import com.experiment.accounting.proxy.authorize.model.User;
import com.experiment.accounting.proxy.authorize.model.UserComponentContent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith({SpringExtension.class})
public class AuthorizeProxyTest {

    @MockBean
    private AuthorizeProxy authorizeProxy;

    private Date date = new Date();
    @BeforeEach
    public void setUp() {
        Mockito.doReturn(createTestUser(date)).when(authorizeProxy).getUserByUsername(any());
    }

    @WithMockOAuth2Scope
    @Test
    public void getUserByUsername() {
        User user = authorizeProxy.getUserByUsername("crymnc");
        Assertions.assertEquals(createTestUser(date), user);
    }

    private User createTestUser(Date date) {
        User user = new User();
        user.setName("crymnc");
        user.setId(1L);
        user.setCreatedAt(date);
        user.setCreatedBy(1L);
        user.setActive(true);
        user.setPassword("1234");
        user.setUpdatedAt(date);
        user.setUsername("crymnc");
        user.setUpdatedBy(1L);
        user.addRole(1L);
        user.addRole(2L);

        UserComponentContent test1 = new UserComponentContent();
        test1.setContent("test1");
        test1.setComponentName("test1");
        test1.setComponentId(1L);
        test1.setUserId(1L);
        test1.setCreatedAt(date);
        test1.setCreatedBy(1L);

        UserComponentContent test2 = new UserComponentContent();
        test2.setContent("test2");
        test2.setComponentName("test2");
        test2.setComponentId(2L);
        test2.setUserId(1L);
        test2.setCreatedAt(date);
        test2.setCreatedBy(1L);
        user.addUserComponentContent(test1);
        user.addUserComponentContent(test2);
        return user;
    }
}
