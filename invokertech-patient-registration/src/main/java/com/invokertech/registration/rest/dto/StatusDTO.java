package com.invokertech.registration.rest.dto;

import com.invokertech.registration.aggregate.StatusType;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StatusDTO {
    private StatusType status;

}
