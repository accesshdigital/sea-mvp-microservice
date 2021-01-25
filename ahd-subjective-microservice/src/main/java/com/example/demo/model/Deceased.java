package com.example.demo.model;

import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class Deceased {
	private Boolean deceased=false;
	private String dateTime;

}
