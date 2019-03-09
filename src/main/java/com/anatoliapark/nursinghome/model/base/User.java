package com.anatoliapark.nursinghome.model.base;

import com.anatoliapark.nursinghome.model.Role;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

import static javax.persistence.InheritanceType.JOINED;


@Entity
@Inheritance(strategy = JOINED)
@Table(name = "USER")
public class User extends BaseEntityAudit {


    @Column(name = "name")
    @NotEmpty(message = "{user.name.NotEmpty}")
    private String name;

    @Column(name = "last_name")
    @NotEmpty(message = "{user.lastname.NotEmpty}")
    private String lastName;

    @Column(name = "identifier_number", nullable = false, unique = true)
    @Length(min = 11, max = 11, message = "{user.identifiernumber.Length}")
    @NotEmpty(message = "{user.identifiernumber.NotEmpty}")
    private String identifierNumber;

    @Column(name = "registration_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date registrationDate;

    @Column(name = "username")
    @NotEmpty(message = "{user.username.NotEmpty}")
    @Length(min = 5, max = 15, message = "{user.username.Length}")
    private String username;

    @Column(name = "password")
    @NotEmpty(message = "{user.password.NotEmpty}")
    @Length(min = 5, max = 60, message = "{user.password.Length}")
    private String password;

    @Column(name = "active", nullable = false)
    private boolean active = true;

    @Column(name = "last_activation_date")
    private Date lastActivationDate;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    @NotEmpty
    private Collection<Role> roles;


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

    public String getIdentifierNumber() {
        return identifierNumber;
    }

    public void setIdentifierNumber(String identifierNumber) {
        this.identifierNumber = identifierNumber;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
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

    public boolean getActive() {
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

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }
}
