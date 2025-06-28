package com.netflix.clone.subscription_service.service;

import java.util.List;

import com.netflix.clone.subscription_service.dto.SubscriptionDTO;

public interface SubscriptionServiceInterface {

    SubscriptionDTO subscribe(Long userId, Long planId);

    SubscriptionDTO getSubscription(Long userId);

    List<SubscriptionDTO> getAllPlans();
}
