package com.anatoliapark.nursinghome.service.impl;

import com.anatoliapark.nursinghome.model.auth.Role;
import com.anatoliapark.nursinghome.repository.base.ConstantRepository;
import com.anatoliapark.nursinghome.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    ConstantRepository constantRepository;

    public void test(){
        constantRepository.findByName("ADMIN", Role.class);
    }

}
