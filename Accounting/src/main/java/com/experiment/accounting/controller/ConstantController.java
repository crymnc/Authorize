package com.experiment.accounting.controller;

import com.experiment.accounting.domain.base.BaseConstantModel;
import com.experiment.accounting.service.ConstantService;
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
@RestController
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
}
