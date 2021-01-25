package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.hateoas.RepresentationModel;

import lombok.Data;

@Entity
@Data
public class Patient extends RepresentationModel<Patient> {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private ArrayList<String> identifier;
    private String gender;
	private String name;
	private String birthDate;
	private String maritalStatus;
	@ElementCollection    
	private List<Address> address;
    private String organizationId;
	private String preferredCommunicationLanguage;
	private String generalPractitionerId;
	private String managingOrganizationId;
	@Embedded
	private Deceased deceased;
	@ElementCollection
	private List<Note> note;
}
