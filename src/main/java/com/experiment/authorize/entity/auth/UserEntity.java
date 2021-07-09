package com.experiment.authorize.entity.auth;

import com.experiment.authorize.entity.UserComponentContentEntity;
import com.experiment.authorize.entity.base.BaseEntityAudit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Entity(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
public class UserEntity extends BaseEntityAudit {

    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "username")
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

    @Autowired
    @Transient
    private PasswordEncoder passwordEncoder;

    public void addRole(RoleEntity roleEntity){
        if(this.getRoles() == null)
            this.setRoles(new HashSet<>());
        this.getRoles().add(roleEntity);
    }

    public void addUserComponentContent(UserComponentContentEntity userComponentContentEntity){
        if(this.getUserComponentContents() == null)
            this.setUserComponentContents(new HashSet<>());
        this.getUserComponentContents().add(userComponentContentEntity);
    }

    public List<SimpleGrantedAuthority> getAuthority() {
        return getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
