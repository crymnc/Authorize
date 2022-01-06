package com.experiment.authorize.controller.rest;

import com.experiment.authorize.annotation.RestApiController;
import com.experiment.authorize.domain.base.BaseConstantModel;
import com.experiment.authorize.mapper.ConstantMapper;
import com.experiment.authorize.service.ConstantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestApiController
@RequestMapping("/api/constants")
@Tag(name = "Constant Controller")
@ApiResponses(value={
        @ApiResponse(responseCode = "400", description = "Bad Request", content = {@Content(schema = @Schema(hidden = true))}),
        @ApiResponse(responseCode = "401", description = "Unauthorized user", content = {@Content(schema = @Schema(hidden = true))}),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {@Content(schema = @Schema(hidden = true))})
})
public class ConstantController {

    @Autowired
    ConstantService constantService;

    @Autowired
    ConstantMapper constantMapper;

    @GetMapping("/{constant}")
    @Operation(summary = "Find All Constant By Name")
    @ApiResponse(responseCode = "200", description = "Related Constants returned", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = BaseConstantModel.class))})
    public ResponseEntity<List> findAllConstantsByName(@PathVariable(name = "constant") String constantName) {
        return ResponseEntity.status(HttpStatus.OK).body(constantService.findAllByConstantName(constantName));
    }

    @DeleteMapping("/{constant}/{id}")
    @Operation(summary = "Delete Constant By Name And ID")
    @ApiResponse(responseCode = "200", description = "Constant deleted", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = BaseConstantModel.class))})
    public ResponseEntity deleteById(@PathVariable(name = "constant") String constantName,@PathVariable Long id){
        constantService.deleteByConstantNameAndId(constantName,id);
        return ResponseEntity.status(HttpStatus.OK).body("DELETED");
    }

    @PostMapping("/{constant}")
    @Operation(summary = "Save New Constant By Name")
    @ApiResponse(responseCode = "201", description = "Constant saved", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = BaseConstantModel.class))})
    public ResponseEntity saveConstant(@PathVariable(name = "constant") String constantName, @RequestBody BaseConstantModel constantModel){
        constantService.saveByConstantName(constantName,constantModel);
        return ResponseEntity.status(HttpStatus.CREATED).body("CREATED");
    }

    @PutMapping("/{constant}")
    @Operation(summary = "Update Constant By Name")
    @ApiResponse(responseCode = "200", description = "Related Constants updated", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = BaseConstantModel.class))})
    public ResponseEntity updateConstant(@PathVariable(name = "constant") String constantName, @RequestBody BaseConstantModel constantModel){
        constantService.updateByConstantName(constantName,constantModel);
        return ResponseEntity.status(HttpStatus.OK).body("UPDATED");
    }

    @PutMapping("/{subname}/{subid}/to/{mainname}/{mainid}")
    @Operation(summary = "Create Relation Between Constants By Name And ID")
    @ApiResponse(responseCode = "200", description = "Related Relation added", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = BaseConstantModel.class))})
    public ResponseEntity addSubConstantToMainConstant(@PathVariable(name = "subname") String subConstantName,
                                               @PathVariable(name = "subid") Long subConstantId,
                                               @PathVariable(name = "mainname") String mainConstantName,
                                               @PathVariable(name = "mainid") Long mainConstantId){
        constantService.addSubConstantToMain(mainConstantName,mainConstantId,subConstantName,subConstantId);
        return ResponseEntity.status(HttpStatus.OK).body("CREATED");
    }

    @DeleteMapping("/{subname}/{subid}/from/{mainname}/{mainid}")
    @Operation(summary = "Remove Relation Between Constants By Name And ID")
    @ApiResponse(responseCode = "200", description = "Related Relation removed", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = BaseConstantModel.class))})
    public ResponseEntity removeSubConstantFromMainConstant(@PathVariable(name = "subname") String subConstantName,
                                          @PathVariable(name = "subid") Long subConstantId,
                                          @PathVariable(name = "mainname") String mainConstantName,
                                          @PathVariable(name = "mainid") Long mainConstantId){
        constantService.removeSubConstantFromMain(mainConstantName,mainConstantId,subConstantName,subConstantId);
        return ResponseEntity.status(HttpStatus.OK).body("DELETED");
    }

    @GetMapping("/{mainname}/{mainid}/{subname}")
    @Operation(summary = "Get All Related Constants By Name And ID")
    @ApiResponse(responseCode = "200", description = "Related Constants returned", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = BaseConstantModel.class))})
    public ResponseEntity<List> getSubConstants(@PathVariable(name = "subname") String subConstantName,
                               @PathVariable(name = "mainname") String mainConstantName,
                               @PathVariable(name = "mainid") Long mainConstantId) {
        return ResponseEntity.status(HttpStatus.OK).body(constantService.getSubConstants(mainConstantName,mainConstantId,subConstantName));
    }

    @GetMapping("/sub-of/{mainname}")
    @Operation(summary = "Get All Related Constants Names By Name And ID")
    @ApiResponse(responseCode = "200", description = "Related Constants names returned", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = BaseConstantModel.class))})
    public ResponseEntity<List> getSubConstantsName(@PathVariable(name = "mainname") String mainConstantName) {
        return ResponseEntity.status(HttpStatus.OK).body(constantService.getAllSubConstantName(mainConstantName));
    }

}
