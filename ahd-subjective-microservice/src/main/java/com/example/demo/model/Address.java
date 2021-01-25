package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Embeddable;

import lombok.Data;
@Data
@Embeddable
public class Address {
	private String use;
	private String type;
	private String text;
	private ArrayList<String> line;
	private String city;
	private String district;
	private String state;
	private String postalCode;
	private String country;
	private Period period;


}
