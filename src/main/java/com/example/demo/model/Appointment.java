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
public class Appointment extends RepresentationModel<Appointment> {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
 
	private ArrayList<String> identifier;
	private String status;
	private String serviceCategory;
	private String serviceType;
	private String appointmentType;
	private ArrayList <String> reasonReference;
	private String startDate;
	private String endDate;
	private String created;
	@Embedded
	private Participant participant;

	@ElementCollection
	private List<Note> note;
}




