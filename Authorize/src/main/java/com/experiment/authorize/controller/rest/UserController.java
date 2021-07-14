package com.experiment.authorize.controller.rest;

import com.experiment.authorize.annotation.RestApiController;
import com.experiment.authorize.domain.User;
import com.experiment.authorize.mapper.UserMapper;
import com.experiment.authorize.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*" , methods = {RequestMethod.POST,RequestMethod.GET}, maxAge = 3600)
@RestApiController
@RequestMapping("/api/user")
@Tag(name = "User Controller")
@ApiResponses(value={
        @ApiResponse(responseCode = "400", description = "Bad Request", content = {@Content(schema = @Schema(hidden = true))}),
        @ApiResponse(responseCode = "401", description = "Unauthorized user", content = {@Content(schema = @Schema(hidden = true))}),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {@Content(schema = @Schema(hidden = true))})
})
public class UserController {

    private static final Logger logger = LogManager.getLogger(UserController.class);

    private final UserService userService;

    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping
    @Operation(summary = "Add New User")
    @ApiResponse(responseCode = "201", description = "New Employee added", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = User.class))})
    public ResponseEntity registerNewUser(@RequestBody User user){
        userService.saveUser(userMapper.toEntity(user));
        return ResponseEntity.status(HttpStatus.CREATED).body("CREATED");
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ADMIN') || authentication.principal")
    @Operation(summary = "Update Registered User")
    @ApiResponse(responseCode = "200", description = "Registed User updated", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = User.class))})
    public ResponseEntity updateUser(@RequestBody User user){
        userService.updateUser(user);
        return ResponseEntity.status(HttpStatus.OK).body("UPDATED");
    }

    @DeleteMapping(params = {"username"})
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "Delete Registered User")
    @ApiResponse(responseCode = "200", description = "Registered User deleted", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = User.class))})
    public ResponseEntity deleteUserByUsername(@RequestParam("username") String username){
        userService.deleteUserByUsername(username);
        return ResponseEntity.status(HttpStatus.OK).body("DELETED");
    }

    @DeleteMapping(params = {"id"})
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "Delete Registered User")
    @ApiResponse(responseCode = "200", description = "Registered User deleted", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = User.class))})
    public ResponseEntity deleteUserById(@RequestParam("id") Long id){
        userService.deleteUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body("DELETED");
    }

    @GetMapping
    @Operation(summary = "Get All Registered User")
    @ApiResponse(responseCode = "200", description = "Registered Users returned", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = User.class))})
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userMapper.toDomainList(userService.findAllUsers()));
    }
}
