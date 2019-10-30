package com.anatoliapark.nursinghome.model.auth;

import com.anatoliapark.nursinghome.model.UserComponent;
import com.anatoliapark.nursinghome.model.base.BaseConstantEntity;
import com.anatoliapark.nursinghome.model.base.BaseEntityAudit;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "user_type")
public class UserType extends BaseConstantEntity {

    @ManyToMany
    @JoinTable(
            name = "usertype_authoritygroup",
            joinColumns = {@JoinColumn(name = "type_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_group_id", referencedColumnName = "id")}
    )
    private Collection<AuthorityGroup> authorityGroups;

    @ManyToMany(mappedBy = "userTypes", fetch = FetchType.LAZY)
    private Collection<User> users;

    @ManyToMany
    @JoinTable(
            name = "usertype_component",
            joinColumns = {@JoinColumn(name = "type_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "component_id", referencedColumnName = "id")}
    )
    private Collection<UserComponent> userComponents;

    public Collection<AuthorityGroup> getAuthorityGroups() {
        return authorityGroups;
    }

    public void setAuthorityGroups(Collection<AuthorityGroup> authorityGroups) {
        this.authorityGroups = authorityGroups;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }

    public Collection<UserComponent> getUserComponents() {
        return userComponents;
    }

    public void setUserComponents(Collection<UserComponent> userComponents) {
        this.userComponents = userComponents;
    }
}
