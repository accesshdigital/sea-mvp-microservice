package com.invokertech.registration.entity;

import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class Address {
	 private String houseNo;
	 private String locality;
	 private String subLocality1;
	 private String subLocality2;
	 private String state;
	 private int pin;
	 
}
