package com.example.demo.model;

import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class Reaction {
	private String substance;
	private String manifestation;
	private String description;
	private String onsetDateTime;
	private String severity;
}
