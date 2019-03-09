package com.anatoliapark.nursinghome.model.usertypes;

import com.anatoliapark.nursinghome.model.GuestHealthSituation;
import com.anatoliapark.nursinghome.model.base.BaseEntityAudit;
import com.anatoliapark.nursinghome.model.base.User;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name="guest")
public class Guest extends User {

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "birth_place")
    private String birthPlace;

    @Column(name = "description")
    @Length(max = 300)
    private String description;

    @Column(name = "education")
    @Length(max = 100)
    private String education;

    @Column(name = "job")
    @Length(max = 100)
    private String job;

    @JoinTable(name = "blood_type", joinColumns = @JoinColumn(name = "blood_type_id"))
    private String bloodType;

    @ManyToMany
    @JoinTable(
            name = "guest_situation",
            joinColumns = @JoinColumn(
                    name = "guest_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "situation_id", referencedColumnName = "id"))
    private Collection<GuestHealthSituation> healthSituations;

    @ManyToMany(mappedBy = "guests")
    private Collection<Client> clients;


    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public Collection<GuestHealthSituation> getHealthSituations() {
        return healthSituations;
    }

    public void setHealthSituations(Collection<GuestHealthSituation> healthSituations) {
        this.healthSituations = healthSituations;
    }

    public Collection<Client> getClients() {
        return clients;
    }

    public void setClients(Collection<Client> clients) {
        this.clients = clients;
    }
}
