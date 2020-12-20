package com.anatoliapark.nursinghome.model;

import com.anatoliapark.nursinghome.annotation.EntityMapping;
import com.anatoliapark.nursinghome.entity.auth.UserEntity;
import com.anatoliapark.nursinghome.model.base.BaseModel;
import com.anatoliapark.nursinghome.util.Mapper;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
@EntityMapping(entityClass = UserEntity.class)
public class User extends BaseModel {

    private String name,lastName,username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private boolean isActive = true;
    private Date lastActivationDate;
    private Set<Role> roles;
    private Set<UserComponentContent> userComponentContents;

    public User(UserEntity userEntity) {
        super(userEntity);
        this.setName(userEntity.getName());
        this.setLastName(userEntity.getLastName());
        this.setActive(userEntity.isActive());
        this.setLastActivationDate(userEntity.getLastActivationDate());
        this.setUsername(userEntity.getUsername());
        this.setPassword(userEntity.getPassword());
        this.setRoles(Mapper.getModelSet(userEntity.getRoles()));
        this.setUserComponentContents(Mapper.getModelSet(userEntity.getUserComponentContents()));
    }

    public User(){super();}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Date getLastActivationDate() {
        return lastActivationDate;
    }

    public void setLastActivationDate(Date lastActivationDate) {
        this.lastActivationDate = lastActivationDate;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<UserComponentContent> getUserComponentContents() {
        return userComponentContents;
    }

    public void setUserComponentContents(Set<UserComponentContent> userComponentContents) {
        this.userComponentContents = userComponentContents;
    }

    @JsonIgnore
    public List<SimpleGrantedAuthority> getAuthority() {
        return getRoles().stream().map(role -> new SimpleGrantedAuthority("ROLE_"+role.getName())).collect(Collectors.toList());
    }
}
