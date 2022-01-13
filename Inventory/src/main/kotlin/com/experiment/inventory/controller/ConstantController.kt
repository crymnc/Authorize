package com.experiment.inventory.controller

import com.experiment.inventory.domain.base.BaseConstantModel
import com.experiment.inventory.entity.base.BaseConstantEntity
import com.experiment.inventory.service.ConstantService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/constants")
class ConstantController<T> @Autowired constructor(var constantService:ConstantService<T> ) where T:BaseConstantEntity{

    @GetMapping("/{constant}")
    fun findAllConstantsByName(@PathVariable(name = "constant") constantName: String): ResponseEntity<List<*>?>? {
        return ResponseEntity.status(HttpStatus.OK).body(constantService.findAllByConstantName(constantName))
    }

    @DeleteMapping("/{constant}/{id}")
    fun deleteById(@PathVariable(name = "constant") constantName: String, @PathVariable id: Long): ResponseEntity<*>? {
        constantService.deleteByConstantNameAndId(constantName, id)
        return ResponseEntity.status(HttpStatus.OK).body("DELETED")
    }

    @PostMapping("/{constant}")
    fun saveConstant(@Valid @PathVariable(name = "constant") constantName: String, @RequestBody constantModel: BaseConstantModel): ResponseEntity<*>? {
        constantService.saveByConstantName(constantName, constantModel)
        return ResponseEntity.status(HttpStatus.CREATED).body("CREATED")
    }

    @PutMapping("/{constant}")
    fun updateConstant(@Valid @PathVariable(name = "constant") constantName: String, @RequestBody constantModel: BaseConstantModel): ResponseEntity<*>? {
        constantService.updateByConstantName(constantName, constantModel)
        return ResponseEntity.status(HttpStatus.OK).body("UPDATED")
    }
}