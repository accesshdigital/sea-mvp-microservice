package com.invokertech.registration.rest;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.invokertech.registration.entity.Account;
import com.invokertech.registration.service.PatientQueryService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "/registrations")
@AllArgsConstructor
public class PatientQueryController {

    private final PatientQueryService patientQueryService;

    @GetMapping("/{registrationId}")
    public CompletableFuture<Account> findById(@PathVariable("accountId") String accountId) {
        return this.patientQueryService.findById(accountId);
    }

    @GetMapping("/{registrationId}/events")
    public List<Object> listEventsForAccount(@PathVariable(value = "accountId") String accountId) {
        return this.patientQueryService.listEventsForAccount(accountId);
    }
}
