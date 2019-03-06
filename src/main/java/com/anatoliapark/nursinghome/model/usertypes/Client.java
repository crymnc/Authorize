package com.anatoliapark.nursinghome.model.usertypes;

import com.anatoliapark.nursinghome.model.UserAddress;
import com.anatoliapark.nursinghome.model.UserEmail;
import com.anatoliapark.nursinghome.model.UserPhone;
import com.anatoliapark.nursinghome.model.base.User;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "client")
public class Client extends User {

    @OneToMany(
            orphanRemoval = true
    )
    @JoinColumn(name="user_id", referencedColumnName="id")
    private Collection<UserPhone> phones;

    @OneToMany(
            orphanRemoval = true
    )
    @JoinColumn(name="user_id", referencedColumnName="id")
    private Collection<UserEmail> emails;

    @OneToMany(
            orphanRemoval = true
    )
    @JoinColumn(name="user_id", referencedColumnName="id")
    private Collection<UserAddress> addresses;

    @Column(name = "affinity")
    @Length(max = 30)
    private String affinity;


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

    public String getAffinity() {
        return affinity;
    }

    public void setAffinity(String affinity) {
        this.affinity = affinity;
    }
}
