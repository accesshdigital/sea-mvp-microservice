package com.example.demo.model;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.hateoas.RepresentationModel;

import lombok.Data;

@Entity
@Data
public class FamilyMemberHistory extends RepresentationModel<FamilyMemberHistory>{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private ArrayList<String> identifier;	
	/*
	 * private String date; private String dateTime ; private String name ; private
	 * String relationship ; private String condition; private Note note;
	 * 
	 * private String reasonReference;
	 */

}
