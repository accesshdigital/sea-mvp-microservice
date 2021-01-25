package com.example.demo.model;

import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class Abatement {
	private String abatementDateTime;
	private String abatementAge;
	private String abatementPeriod;
	private String abatementRange;
	private String abatementString;

}
