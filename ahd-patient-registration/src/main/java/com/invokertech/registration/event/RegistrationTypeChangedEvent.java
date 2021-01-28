package com.invokertech.registration.event;

import java.util.UUID;

import com.invokertech.registration.aggregate.RegistrationType;

import lombok.Value;
@Value
public class RegistrationTypeChangedEvent {
	 private final UUID id;
	    private final RegistrationType registrationType;
}
