package com.anatoliapark.nursinghome.model;

import com.anatoliapark.nursinghome.model.base.BaseEntityAudit;
import com.anatoliapark.nursinghome.model.base.User;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "role")
public class Role extends BaseEntityAudit {

    @Column(name = "name")
    @NotEmpty(message = "{user.role.NotEmpty}")
    @Length(max = 20, message = "{user.role.Length}")
    private String name;

    @ManyToMany
    @JoinTable(
            name = "role_privilege",
            joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "privilege_id", referencedColumnName = "id")}
    )
    private Collection<Privilege> privileges;

    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Privilege> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(Collection<Privilege> privileges) {
        this.privileges = privileges;
    }


}
