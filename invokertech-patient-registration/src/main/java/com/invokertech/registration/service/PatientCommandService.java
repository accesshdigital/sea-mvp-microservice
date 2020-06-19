package com.invokertech.registration.service;

import static com.invokertech.registration.service.ServiceUtils.formatUuid;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.invokertech.registration.aggregate.RegistrationType;
import com.invokertech.registration.command.CreatePatientCommand;
import com.invokertech.registration.command.UpdatePatientCommand;
import com.invokertech.registration.command.UpdatePatientLocalIdCommand;
import com.invokertech.registration.command.UpdateRegistrationTypeCommand;
import com.invokertech.registration.command.UpdateStatusCommand;
import com.invokertech.registration.entity.Account;
import com.invokertech.registration.exception.DuplicateDataException;
import com.invokertech.registration.exception.ResponseError;
import com.invokertech.registration.repository.AccountRepository;
import com.invokertech.registration.rest.dto.PatientCreationDTO;
import com.invokertech.registration.rest.dto.PatientLocalIdDTO;
import com.invokertech.registration.rest.dto.PatientUpdateDTO;
import com.invokertech.registration.rest.dto.RegistrationTypeDTO;
import com.invokertech.registration.rest.dto.StatusDTO;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PatientCommandService {
	@Autowired
	private AccountRepository accountRepository;

	private final CommandGateway commandGateway;
	private static AtomicInteger atomicInteger = new AtomicInteger(200);

	public CompletableFuture<Account> createAccount(PatientCreationDTO creationDTO) {
		
		
		if(accountRepository.existsByEmail(creationDTO.getEmail())) throw new IllegalArgumentException(" User Already Exist with same email id");
		
		
			
	
		String prefix = "TEMP";
		if (creationDTO.getRegistrationType() == (RegistrationType.WALKIN))
			prefix = "RTWO-";
		return this.commandGateway
				.send(new CreatePatientCommand(
						
						UUID.randomUUID(), 
						prefix + atomicInteger.incrementAndGet(),
						creationDTO.getUhid(),
						creationDTO.getAlternateUniqueIdentificationNumberType(),
						creationDTO.getAlternateUniqueIdentificationNumber(),
						creationDTO.getPatientName(),
						creationDTO.getStatus(),
						creationDTO.getRegistrationType(),
						creationDTO.getEmail(),
						creationDTO.getDob(),
						creationDTO.getBirthOrder(),
						creationDTO.getParity(),
						creationDTO.getGravida(),
						creationDTO.getIdentityUnknownIndicator(),
						creationDTO.getCauseOfDeathKnownIndicator(),
						creationDTO.getPatientAddressType(),
						//creationDTO.getPatientAddress(),
						creationDTO.getHouseNo(),
						creationDTO.getLocality(),
						creationDTO.getSubLocality1(),
						creationDTO.getSubLocality2(),
						creationDTO.getState(),
						creationDTO.getPin(),
						creationDTO.getPatientLandlineNumber(),
		        		creationDTO.getPatientMobileNumber(),
		        		creationDTO.getPatientClass(),
		        		creationDTO.getPregnancyIndicator(),
		        		creationDTO.getDurationOfPregnancy(),
		        		creationDTO.getInsuredCardID(),
		        		creationDTO.getInsuredPolicyID(),
		        		creationDTO.getSecondaryHealthInsurancePolicyID(),
		        		creationDTO.getSecondaryHealthInsurancePolicyIndicator()

		     

				));
	}

	public CompletableFuture<Account> updateAccount(String accountId, PatientUpdateDTO updateDTO) {
		return this.commandGateway.send(new UpdatePatientCommand(
				formatUuid(accountId),
				updateDTO.getUhid(),
				updateDTO.getAlternateUniqueIdentificationNumberType(),
				updateDTO.getAlternateUniqueIdentificationNumber(),
				updateDTO.getPatientName(),
				updateDTO.getStatus(),
				updateDTO.getRegistrationType(),
				updateDTO.getEmail(),
				updateDTO.getDob(),
				updateDTO.getBirthOrder(),
				updateDTO.getParity(),
				updateDTO.getGravida(),
				updateDTO.getIdentityUnknownIndicator(),
				updateDTO.getCauseOfDeathKnownIndicator(),
				updateDTO.getPatientAddressType(),
				//creationDTO.getPatientAddress(),
				updateDTO.getHouseNo(),
				updateDTO.getLocality(),
				updateDTO.getSubLocality1(),
				updateDTO.getSubLocality2(),
				updateDTO.getState(),
				updateDTO.getPin(),
				updateDTO.getPatientLandlineNumber(),
				updateDTO.getPatientMobileNumber(),
				updateDTO.getPatientClass(),
        		updateDTO.getPregnancyIndicator(),
        		updateDTO.getDurationOfPregnancy(),
        		updateDTO.getInsuredCardID(),
        		updateDTO.getInsuredPolicyID(),
        		updateDTO.getSecondaryHealthInsurancePolicyID(),
        		updateDTO.getSecondaryHealthInsurancePolicyIndicator()

		));
	}

	public CompletableFuture<String> updateStatus(String accountId, StatusDTO statusDTO) {
		return this.commandGateway.send(new UpdateStatusCommand(formatUuid(accountId), statusDTO.getStatus()

		));
	}

	public CompletableFuture<String> updatePatientLocalId(String accountId, PatientLocalIdDTO patientLocalIdDTO) {
		return this.commandGateway.send(new UpdatePatientLocalIdCommand(formatUuid(accountId), patientLocalIdDTO.getPatientLocalId()

		));
	}

	public CompletableFuture<String> updateRegistrationType(String accountId, RegistrationTypeDTO registrationTypeDTO) {

		return this.commandGateway
				.send(new UpdateRegistrationTypeCommand(formatUuid(accountId), registrationTypeDTO.getRegistrationType()

				));
	}
}
