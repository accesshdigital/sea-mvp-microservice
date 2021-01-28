package com.invokertech.registration.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.invokertech.registration.aggregate.RegistrationType;
import com.invokertech.registration.aggregate.StatusType;
import com.opencsv.bean.CsvBindByName;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Account {
    @Id
    private UUID id;
    private String alternateUniqueIdentificationNumberType;
    private String alternateUniqueIdentificationNumber;
    private String patientLocalId;
	private String uhid;
	private String patientName;
	private LocalDate dob;
	private StatusType status;
	private RegistrationType registrationType;
	private String email;
	private int birthOrder;
	private int parity;
	private int gravida;
	private int identityUnknownIndicator;
	private int causeOfDeathKnownIndicator;
	
	private String patientAddressType;
	 private String houseNo;
	 private String locality;
	 private String subLocality1;
	 private String subLocality2;
	 private String state;
	 private String pin;
	private String patientLandlineNumber;
	private String patientMobileNumber;
	private int patientClass;
	private int pregnancyIndicator;
	private int durationOfPregnancy;
	private String insuredCardID;
	private String insuredPolicyID;
	private int secondaryHealthInsurancePolicyIndicator;
	private String secondaryHealthInsurancePolicyID;
	//@Embedded
    //Address address;


}
