package com.invokertech.registration.event;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

import com.invokertech.registration.aggregate.RegistrationType;
import com.invokertech.registration.aggregate.StatusType;

@Data
@AllArgsConstructor
public class PatientCreatedEvent {
	private UUID accountId;
    private String patientLocalId;

	private String uhid;
	 private String alternateUniqueIdentificationNumberType;
	    private String alternateUniqueIdentificationNumber;
	private String patientName;
	private LocalDate dob;
	//private int patientAge;
	private StatusType status;
	private RegistrationType registrationType;
	private String email;
	private int birthOrder;
	private int parity;
	private int gravida;
	private int identityUnknownIndicator;
	private int causeOfDeathKnownIndicator;
	private String patientAddressType;
	//private String patientAddress;
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

}
