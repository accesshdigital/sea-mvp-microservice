package com.invokertech.registration.rest;

import static org.springframework.http.HttpStatus.CREATED;

import java.util.concurrent.CompletableFuture;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.invokertech.registration.entity.Account;
import com.invokertech.registration.rest.dto.PatientCreationDTO;
import com.invokertech.registration.rest.dto.PatientUpdateDTO;
import com.invokertech.registration.rest.dto.RegistrationTypeDTO;
import com.invokertech.registration.rest.dto.StatusDTO;
import com.invokertech.registration.service.PatientCommandService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "/registrations")
@AllArgsConstructor
public class PatientCommandController {
    private final PatientCommandService accountCommandService;

    @PostMapping
    @ResponseStatus(value = CREATED)
    public CompletableFuture<Account> createAccount(@Valid @RequestBody PatientCreationDTO creationDTO) {
    	
        return this.accountCommandService.createAccount(creationDTO);
    }
    
    @PutMapping(value = "/{registrationId}")
    public CompletableFuture<Account> updateAccount( @PathVariable(value = "accountId") String accountId,@Valid @RequestBody PatientUpdateDTO updateDTO) {
        return this.accountCommandService.updateAccount(accountId, updateDTO);
    }

   

    @PutMapping(value = "/status/{registrationId}")
    public CompletableFuture<String> updateStatus(@PathVariable(value = "accountId") String accountId,
                                                          @RequestBody StatusDTO statusDTO) {
        return this.accountCommandService.updateStatus(accountId, statusDTO);
    }

    @PutMapping(value = "/registrationtype/{registrationId}")
    public CompletableFuture<String> updateRegistrationType(@PathVariable(value = "accountId") String accountId,
                                                           @RequestBody RegistrationTypeDTO registrationTypeDTO) {
        return this.accountCommandService.updateRegistrationType(accountId, registrationTypeDTO);
    }
}
