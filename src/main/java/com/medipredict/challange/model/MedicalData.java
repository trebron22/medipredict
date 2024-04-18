package com.medipredict.challange.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "medical_data")
public class MedicalData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "blood_type", nullable = false)
    private BloodType bloodType;

    @Column(name = "blood_pressure", nullable = false)
    private int bloodPressure;

    @Column(name = "height", nullable = false)
    private int height;

    @Column(name = "weight", nullable = false)
    private int weight;

    @Column(name = "diabates", nullable = false)
    private boolean diabetes;

    @Column(name = "antibiotics_resistance", nullable = false)
    private boolean antibioticsResistance;

    @Column(name = "hypertonic_time_index", precision = 4, scale = 2, nullable = false)
    private BigDecimal hypertonicTimeIndex;

    @JsonBackReference
    @OneToOne(mappedBy="medicalData")
    private Person person;


    public MedicalData() {
    }

    public MedicalData(Long id, BloodType bloodType, int bloodPressure, int height, int weight, boolean diabetes, boolean antibioticsResistance, BigDecimal hypertonicTimeIndex, Person person) {
        this.id = id;
        this.bloodType = bloodType;
        this.bloodPressure = bloodPressure;
        this.height = height;
        this.weight = weight;
        this.diabetes = diabetes;
        this.antibioticsResistance = antibioticsResistance;
        this.hypertonicTimeIndex = hypertonicTimeIndex;
        this.person = person;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BloodType getBloodType() {
        return bloodType;
    }

    public void setBloodType(BloodType bloodType) {
        this.bloodType = bloodType;
    }

    public int getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(int bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public boolean isDiabetes() {
        return diabetes;
    }

    public void setDiabetes(boolean diabetes) {
        this.diabetes = diabetes;
    }

    public boolean isAntibioticsResistance() {
        return antibioticsResistance;
    }

    public void setAntibioticsResistance(boolean antibioticsResistance) {
        this.antibioticsResistance = antibioticsResistance;
    }

    public BigDecimal getHypertonicTimeIndex() {
        return hypertonicTimeIndex;
    }

    public void setHypertonicTimeIndex(BigDecimal hypertonicTimeIndex) {
        this.hypertonicTimeIndex = hypertonicTimeIndex;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

}