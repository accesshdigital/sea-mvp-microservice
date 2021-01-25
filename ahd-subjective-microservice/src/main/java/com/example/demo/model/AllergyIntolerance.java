package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.hateoas.RepresentationModel;

import lombok.Data;

@Entity
@Data
public class AllergyIntolerance extends RepresentationModel<AllergyIntolerance>{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private ArrayList<String> identifier;
	private String clinicalStatus;
	private String verificationStatus;
	private ArrayList<String> category;
	private String patientId;
	private String encounterId;
	private String recordedDate;
	private String lastOccurrence;
	@Embedded
	private OnSet onSet;
	@Embedded
	private Reaction reaction;
	@ElementCollection
	private List<Note> note;

}
