package com.anatoliapark.nursinghome.controller.rest;

import com.anatoliapark.nursinghome.annotation.RestApiController;
import com.anatoliapark.nursinghome.entity.UserComponentEntity;
import com.anatoliapark.nursinghome.entity.auth.RoleEntity;
import com.anatoliapark.nursinghome.model.UserComponent;
import com.anatoliapark.nursinghome.service.ConstantService;
import com.anatoliapark.nursinghome.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestApiController
@RequestMapping("/api/constants")
public class ModelController {

    @Autowired
    ConstantService constantService;

    private static final HashMap<String, Class> constantMap = new HashMap<>();

    static{
        constantMap.put("role",RoleEntity.class);
        constantMap.put("userComponent", UserComponentEntity.class);
    }

    @GetMapping("/{constant}")
    public List<UserComponent> getRoles(@PathVariable String constant){
        if(constantMap.containsKey(constant)){
            return Mapper.getModelList(constantService.findAll(constantMap.get(constant)));
        }
        return null;
    }
}
