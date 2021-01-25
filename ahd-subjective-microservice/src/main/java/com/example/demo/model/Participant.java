package com.example.demo.model;

import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class Participant {
private String patientResource;
private String patientStatus;
private String practionerResource;
private String practionerStatus;

}
