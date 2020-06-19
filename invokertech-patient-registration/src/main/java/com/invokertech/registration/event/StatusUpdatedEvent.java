package com.invokertech.registration.event;

import lombok.Value;

import java.util.UUID;

import com.invokertech.registration.aggregate.StatusType;

@Value
public class StatusUpdatedEvent {

    private UUID id;
    private StatusType status;
}
