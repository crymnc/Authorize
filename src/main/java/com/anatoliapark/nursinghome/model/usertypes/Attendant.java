package com.anatoliapark.nursinghome.model.usertypes;

import com.anatoliapark.nursinghome.model.UserAddress;
import com.anatoliapark.nursinghome.model.UserEmail;
import com.anatoliapark.nursinghome.model.UserPhone;
import com.anatoliapark.nursinghome.model.base.User;
import org.hibernate.validator.constraints.NotEmpty;


import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "attendant")
public class Attendant extends User {

    @OneToMany(
            orphanRemoval = true
    )
    @JoinColumn(name="user_id", referencedColumnName="id")
    @NotEmpty(message="{user.phone.NotEmpty}")
    private Collection<UserPhone> phones;

    @OneToMany
    @JoinColumn(name="user_id", referencedColumnName="id")
    @NotEmpty(message="{user.email.NotEmpty}")
    private Collection<UserEmail> emails;

    @OneToMany(
            orphanRemoval = true
    )
    @JoinColumn(name="user_id", referencedColumnName="id")
    private Collection<UserAddress> addresses;

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "education")
    private String education;

    @Column(name = "expertise")
    private String expertise;

    public Collection<UserPhone> getPhones() {
        return phones;
    }

    public void setPhones(Collection<UserPhone> phones) {
        this.phones = phones;
    }

    public Collection<UserEmail> getEmails() {
        return emails;
    }

    public void setEmails(Collection<UserEmail> emails) {
        this.emails = emails;
    }

    public Collection<UserAddress> getAddresses() {
        return addresses;
    }

    public void setAddresses(Collection<UserAddress> addresses) {
        this.addresses = addresses;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getExpertise() {
        return expertise;
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }
}
