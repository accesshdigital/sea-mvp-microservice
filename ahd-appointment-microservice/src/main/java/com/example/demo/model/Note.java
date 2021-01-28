package com.example.demo.model;

import javax.persistence.Embeddable;

import lombok.Data;
@Data
@Embeddable
public class Note {
	
	private String authorReferenceId;
	private String text;
	private String DateTime;
	
}
