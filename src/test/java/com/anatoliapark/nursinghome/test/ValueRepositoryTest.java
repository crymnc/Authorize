package com.anatoliapark.nursinghome.test;

import com.anatoliapark.nursinghome.model.Value;
import com.anatoliapark.nursinghome.model.Variable;
import com.anatoliapark.nursinghome.repository.ValueRepository;
import com.anatoliapark.nursinghome.repository.VariableRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-test.properties")
public class ValueRepositoryTest {

    @Autowired
    ValueRepository valueRepository;

    @Autowired
    VariableRepository variableRepository;

    @Test
    public void test(){
        Variable var=new Variable();
        var.setName("Integer");
        var.setType(Integer.class.getName());

        variableRepository.save(var);

        Value val=new Value();
        val.setUserId(1L);
        val.setVariable(variableRepository.findOne(1L));
        val.setValue("2");

        valueRepository.save(val);
        Value value=valueRepository.findOne(1L);


        Object o = value.getValue();
        System.out.println(1);
    }
}
