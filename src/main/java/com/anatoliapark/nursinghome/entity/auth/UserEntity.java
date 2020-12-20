package com.anatoliapark.nursinghome.entity.auth;

import com.anatoliapark.nursinghome.annotation.ModelMapping;
import com.anatoliapark.nursinghome.entity.UserComponentContentEntity;
import com.anatoliapark.nursinghome.entity.base.BaseEntityAudit;
import com.anatoliapark.nursinghome.model.User;
import com.anatoliapark.nursinghome.util.Mapper;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;


@Entity(name = "user")
@ModelMapping(modelClass = User.class)
@DynamicUpdate //may causes performance issues
public class UserEntity extends BaseEntityAudit {

    @Column(name = "name")
    @NotEmpty(message = "{user.name.NotEmpty}")
    private String name;

    @Column(name = "last_name")
    @NotEmpty(message = "{user.lastname.NotEmpty}")
    private String lastName;

    @Column(name = "username")
    @NotEmpty(message = "{user.username.NotEmpty}")
    @Length(min = 5, max = 15, message = "{user.username.Length}")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "active")
    private boolean active = true;

    @Column(name = "last_activation_date")
    private Date lastActivationDate;

    @ManyToMany(cascade = {CascadeType.REFRESH,CascadeType.DETACH,CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}
    )
    private Set<RoleEntity> roles;

    @OneToMany(
            mappedBy = "user",
            orphanRemoval = true,
            cascade=CascadeType.ALL, fetch = FetchType.EAGER
    )
    private Set<UserComponentContentEntity> userComponentContents;

    public UserEntity(User user) {
        super(user);
        this.setUsername(user.getUsername());
        this.setPassword(user.getPassword());
        this.setActive(user.getActive());
        this.setName(user.getName());
        this.setLastName(user.getLastName());
        this.setLastActivationDate(user.getLastActivationDate());
        Set<RoleEntity> roleEntities = Mapper.getEntitySet(user.getRoles());
        for(RoleEntity roleEntity:roleEntities){
            roleEntity.addUser(this);
        }
        this.setRoles(roleEntities);
        Set<UserComponentContentEntity> userComponentContentEntities = Mapper.getEntitySet(user.getUserComponentContents());
        for(UserComponentContentEntity userComponentContentEntity:userComponentContentEntities){
            userComponentContentEntity.setUser(this);
        }
        this.setUserComponentContents(userComponentContentEntities);
    }

    public UserEntity(){}

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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getLastActivationDate() {
        return lastActivationDate;
    }

    public void setLastActivationDate(Date lastActivationDate) {
        this.lastActivationDate = lastActivationDate;
    }

    public Set<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
    }

    public Set<UserComponentContentEntity> getUserComponentContents() {
        return userComponentContents;
    }

    public void setUserComponentContents(Set<UserComponentContentEntity> componentContentEntities){this.userComponentContents = componentContentEntities;}
}
