package com.invokertech.registration.service;

import static com.invokertech.registration.service.ServiceUtils.formatUuid;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.messaging.Message;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Service;

import com.invokertech.registration.entity.Account;
import com.invokertech.registration.query.FindPatientQuery;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PatientQueryService {
    private final QueryGateway queryGateway;
    private final EventStore eventStore;

    public CompletableFuture<Account> findById(String accountId) {
        return this.queryGateway.query(
                new FindPatientQuery(formatUuid(accountId)),
                ResponseTypes.instanceOf(Account.class)
        );
    }

    public List<Object> listEventsForAccount(String accountId) {
        return this.eventStore
                .readEvents(formatUuid(accountId).toString())
                .asStream()
                .map(Message::getPayload)
                .collect(Collectors.toList());
    }
}
