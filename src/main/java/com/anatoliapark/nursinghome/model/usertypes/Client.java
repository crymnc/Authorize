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
            orphanRemoval = true,
            cascade=CascadeType.ALL
    )
    @JoinColumn(name="user_id", referencedColumnName="id",foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private Collection<UserPhone> phones;

    @OneToMany(
            orphanRemoval = true,
            cascade=CascadeType.ALL
    )
    @JoinColumn(name="user_id", referencedColumnName="id",foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private Collection<UserEmail> emails;

    @OneToMany(
            orphanRemoval = true,
            cascade=CascadeType.ALL
    )
    @JoinColumn(name="user_id", referencedColumnName="id",foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private Collection<UserAddress> addresses;

    @Column(name = "affinity")
    @Length(max = 30)
    private String affinity;

    @ManyToMany
    @JoinTable(
            name = "client_guest",
            joinColumns = @JoinColumn(
                    name = "client_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "guest_id", referencedColumnName = "id"))
    private Collection<Guest> guests;

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

    public Collection<Guest> getGuests() {
        return guests;
    }

    public void setGuests(Collection<Guest> guests) {
        this.guests = guests;
    }
}
