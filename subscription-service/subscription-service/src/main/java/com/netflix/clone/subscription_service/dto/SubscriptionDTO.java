package com.netflix.clone.subscription_service.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubscriptionDTO {

    private Long id;
    private Long userId;
    private Long planId;
    private String planName;
    private Double planPrice;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean active;
}
