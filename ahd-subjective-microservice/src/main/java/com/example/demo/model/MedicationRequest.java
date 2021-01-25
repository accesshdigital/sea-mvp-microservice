package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.hateoas.RepresentationModel;

import lombok.Data;

@Entity
@Data
public class MedicationRequest extends RepresentationModel<MedicationRequest> {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private ArrayList<String> identifier;
	private String status;
	private String statusReason;
	private String intent;
	private String priority;
	private ArrayList<String> category;
	private String patientId;
	private String encounterId;
	private String authoredOn;
	private String requesterId;
	private String performerId;
	private String performerType;
	private String recorder;
    private String reasonCode;
    private String groupIdentifier;
    private String supportingInformationId;
    @ElementCollection
	private List<Note> note;

	
}
