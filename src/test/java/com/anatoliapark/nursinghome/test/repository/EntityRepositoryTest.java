package com.anatoliapark.nursinghome.test.repository;


import com.anatoliapark.nursinghome.entity.UserComponentContentEntity;
import com.anatoliapark.nursinghome.entity.auth.RoleEntity;
import com.anatoliapark.nursinghome.entity.base.BaseEntity;
import com.anatoliapark.nursinghome.repository.base.EntityRepository;
import com.anatoliapark.nursinghome.test.utility.factory.RoleEntityMother;
import com.anatoliapark.nursinghome.test.utility.factory.UserComponentContentEntityMother;
import com.anatoliapark.nursinghome.test.utility.factory.UserComponentEntityMother;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.TransactionSystemException;

import javax.validation.ConstraintViolationException;

import static org.junit.Assert.*;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class EntityRepositoryTest {

    @Autowired
    EntityRepository<BaseEntity> entityRepository;

    @Test
    public void shouldSaveRoleEntity(){
        RoleEntity role = entityRepository.save(RoleEntityMother.builder().complete().build());
        assertNotNull(entityRepository.findById(role.getId()).get());
        assertTrue(role.getId()>0);
    }

    @Test
    public void shouldSaveEntityThrowNameException(){
        Exception exception = assertThrows(TransactionSystemException.class,() -> {
            entityRepository.save(new RoleEntity());
        });

        String message = "Validation failed for classes [com.anatoliapark.nursinghome.entity.auth.RoleEntity] during persist time for groups [javax.validation.groups.Default, ]\n" +
                "List of constraint violations:[\n" +
                "\tConstraintViolationImpl{interpolatedMessage='{constantEntity.name.NotEmpty}', propertyPath=name, rootBeanClass=class com.anatoliapark.nursinghome.entity.auth.RoleEntity, messageTemplate='{constantEntity.name.NotEmpty}'}\n" +
                "]";
        assertSame(message,((TransactionSystemException) exception).getRootCause().getLocalizedMessage());
        assertTrue(((TransactionSystemException) exception).getRootCause().getClass().equals(ConstraintViolationException.class));
    }


    @Test
    public void shouldSaveUserComponentContent(){
        UserComponentContentEntity userComponentContentEntity = entityRepository.save(
                UserComponentContentEntityMother.builder()
                .complete()
                .component(entityRepository.save(UserComponentEntityMother.builder().complete().build()))
                .build());
        assertNotNull(userComponentContentEntity.getId());
        assertTrue(userComponentContentEntity.getId()>0);
    }
}
