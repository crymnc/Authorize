package com.experiment.authorize.controller.rest;

import com.experiment.authorize.annotation.RestApiController;
import com.experiment.authorize.domain.base.BaseConstantModel;
import com.experiment.authorize.mapper.ConstantMapper;
import com.experiment.authorize.service.ConstantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestApiController
@RequestMapping("/api/constants")
public class ConstantController {

    @Autowired
    ConstantService constantService;

    @Autowired
    ConstantMapper constantMapper;

    @GetMapping("/{constant}")
    public List findAllConstantsByName(@PathVariable(name = "constant") String constantName) {
        return constantService.findAllByConstantName(constantName);
    }

    @DeleteMapping("/{constant}/{id}")
    public void deleteById(@PathVariable(name = "constant") String constantName,@PathVariable Long id){
        constantService.deleteByConstantNameAndId(constantName,id);
    }

    @PostMapping("/{constant}")
    public String saveConstant(@PathVariable(name = "constant") String constantName, @RequestBody BaseConstantModel constantModel){
        constantService.saveByConstantName(constantName,constantModel);
        return "Success";
    }

    @PutMapping("/{constant}")
    public String updateConstant(@PathVariable(name = "constant") String constantName, @RequestBody BaseConstantModel constantModel){
        constantService.updateByConstantName(constantName,constantModel);
        return "Success";
    }

    @PutMapping("/{subname}/{subid}/to/{mainname}/{mainid}")
    public String addSubConstantToMainConstant(@PathVariable(name = "subname") String subConstantName,
                                               @PathVariable(name = "subid") Long subConstantId,
                                               @PathVariable(name = "mainname") String mainConstantName,
                                               @PathVariable(name = "mainid") Long mainConstantId){
        constantService.addSubConstantToMain(mainConstantName,mainConstantId,subConstantName,subConstantId);
        return "Success";
    }

    @DeleteMapping("/{subname}/{subid}/from/{mainname}/{mainid}")
    public String removeSubConstantFromMainConstant(@PathVariable(name = "subname") String subConstantName,
                                          @PathVariable(name = "subid") Long subConstantId,
                                          @PathVariable(name = "mainname") String mainConstantName,
                                          @PathVariable(name = "mainid") Long mainConstantId){
        constantService.removeSubConstantFromMain(mainConstantName,mainConstantId,subConstantName,subConstantId);
        return "Success";
    }

    @GetMapping("{mainname}/{mainid}/{subname}")
    public List getSubConstants(@PathVariable(name = "subname") String subConstantName,
                               @PathVariable(name = "mainname") String mainConstantName,
                               @PathVariable(name = "mainid") Long mainConstantId) {
        return constantService.getAllSubConstantName(mainConstantName,mainConstantId,subConstantName);
    }

}
