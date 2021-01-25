package com.example.demo.model;

import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class OnSet {
	private String onsetAge;
	private String onsetString;
	private Period onsetPeriod;
	private String onsetRange;
}
