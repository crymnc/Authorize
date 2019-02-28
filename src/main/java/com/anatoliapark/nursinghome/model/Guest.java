package com.anatoliapark.nursinghome.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;


public class Guest {

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "birth_place")
    private String birthPlace;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinTable(name = "guest_disease", joinColumns = @JoinColumn(name = "disease_id"))
    private Collection<String> diseases;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinTable(name = "guest_surgery", joinColumns = @JoinColumn(name = "surgery_id"))
    private Collection<String> pastSurgicalOperations;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinTable(name = "guest_alergie", joinColumns = @JoinColumn(name = "alergie_id"))
    private Collection<String> alergies;

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

    public Collection<String> getDiseases() {
        return diseases;
    }

    public void setDiseases(Collection<String> diseases) {
        this.diseases = diseases;
    }

    public Collection<String> getPastSurgicalOperations() {
        return pastSurgicalOperations;
    }

    public void setPastSurgicalOperations(Collection<String> pastSurgicalOperations) {
        this.pastSurgicalOperations = pastSurgicalOperations;
    }

    public Collection<String> getAlergies() {
        return alergies;
    }

    public void setAlergies(Collection<String> alergies) {
        this.alergies = alergies;
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
}
