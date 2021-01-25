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
public class Condition extends RepresentationModel<Condition> {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private ArrayList<String> identifier;
	private String clinicalStatus;
	private String verificationStatus;
    private String encounterId;
    private String patientId;
    private String severity;
    private String recordedDate;
    @Embedded
    private Stage stage;


	private ArrayList<String> category;
	private ArrayList<String> bodySite;
	@Embedded
	private OnSet onSet;

	@Embedded
	private Abatement abatement;


	@ElementCollection
	private List<Note> note;


    
	
}
