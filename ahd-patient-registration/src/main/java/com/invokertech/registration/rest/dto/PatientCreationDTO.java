package com.invokertech.registration.rest.dto;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.invokertech.registration.aggregate.RegistrationType;
import com.invokertech.registration.aggregate.StatusType;

import lombok.Value;

@Value
public class PatientCreationDTO {
	@Size(max = 50, message = "Paient uhid must be max 50 characters")
	private String uhid;
	@Size(max = 1, message = "Paient AUID Type must be max 1 characters")
	 private String alternateUniqueIdentificationNumberType;
		@Size(max = 50, message = "Paient AUID No must be max 50 characters")
        private String alternateUniqueIdentificationNumber;
	@Size(min = 3, max = 30, message = "Paient name must be between 3 and 200 characters")
	private String patientName;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate dob;
	private StatusType status;
	private RegistrationType registrationType;
	
	
	@Size(max = 254, message = "Paient birthOrder must be max 254 character length")
	@Email(message = "Email should be valid")
	private String email;
	
	@Max(value = 1, message = "Paient birthOrder must be max 1 character length")
	private int birthOrder;
	@Max(value = 2, message = "Paient parity must be max 2 character length")
	private int parity;
	
	@Max(value = 2, message = "Paient gravida must be max 2 character length")
	private int gravida;
	
	@Max(value=1, message = "Paient identityUnknownIndicator must be max 1 character length")

	private int identityUnknownIndicator;
	@Max(value = 1, message = "Paient causeOfDeathKnownIndicator must be max 1 character length")
	private int causeOfDeathKnownIndicator;
	
	@Size(max = 1, message = "Paient addressType must be max 1 character length")
	private String patientAddressType;
	
	@Size(max = 60, message = "Paient house no must be max 60 character length")
	private String houseNo;
	
	@Size(max = 60, message = "Paient locality must be max 60 character length")
	private String locality;
	
	@Size(max = 60, message = "Paient locality1 must be max 60 character length")
	private String subLocality1;
	
	@Size(max = 60, message = "Paient sublocality2 must be max 60 character length")
	private String subLocality2;
	
	@Size(max = 10, message = "Paient State 10 must be max 60 character length")
	private String state;
	
	@Size(min=6 , max = 6, message = "PIN must be  6 character length")
	private String pin;
	
	@Size(max = 8, message = "Paient landline no  must be max 8 character length")
	private String patientLandlineNumber;
	
	@Size(max = 10, message = "Paient mobile  no  must be max 10 character length")
	private String patientMobileNumber;
	
	@Max(value = 2, message = "Paient class  must be max 2 character length")
	private int patientClass;
	
	@Max(value = 1, message = "PregnancyIndicator must be max 1 character length")
	private int pregnancyIndicator;
	
	@Max(value = 2, message = "DurationOfPregnancy must be max 2 character length")
	private int durationOfPregnancy;
	
	@Size(max = 50, message = "InsurenceCardId must be max 50 character length")
	private String insuredCardID;
	
	@Size(max = 50, message = "InsuredPolicyID must be max 50 character length")
	private String insuredPolicyID;
	
	@Max(value = 1, message = "SecondaryHealthInsurancePolicyIndicator must be max 1 character length")
    private int secondaryHealthInsurancePolicyIndicator;
	
	@Size(max = 50, message = "SecondaryHealthInsurancePolicyID must be max 50 character length")
	private String secondaryHealthInsurancePolicyID;

}
