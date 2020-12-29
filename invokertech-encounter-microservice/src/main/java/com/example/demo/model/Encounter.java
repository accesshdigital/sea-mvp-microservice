package com.example.demo.model;

import javax.persistence.*;  
import javax.validation.constraints.NotNull;

import org.springframework.hateoas.RepresentationModel;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;  
import java.util.Set;

@Entity
@Data
public class Encounter extends RepresentationModel<Encounter>{  
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	private ArrayList<String> identifier;
	private String encounterStatus;
	private String encounterClass;
	private String encounterType;
	private String serviceType;
	private String priority;
	private String patientId;
	private String accountId;
	private String episodeOfCareId;
	private Period period;
	private String basedOnServiceRequestId;
	private String appointmentId;
	private String serviceProviderId;
	private String partOf;










 
    

}
