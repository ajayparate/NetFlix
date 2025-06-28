package com.netflix.clone.subscription_service.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.netflix.clone.subscription_service.dto.SubscriptionDTO;
import com.netflix.clone.subscription_service.exception.ResourceNotFoundException;
import com.netflix.clone.subscription_service.model.Plan;
import com.netflix.clone.subscription_service.model.Subscription;
import com.netflix.clone.subscription_service.repository.PlanRepository;
import com.netflix.clone.subscription_service.repository.SubscriptionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionServiceInterface {

    private final SubscriptionRepository subscriptionRepository;
    private final PlanRepository planRepository;
    @Override
    public SubscriptionDTO subscribe(Long userId, Long planId) {
        // TODO Auto-generated method stub
        Plan plan = planRepository.findById(planId)
                .orElseThrow(() -> new ResourceNotFoundException("Plan not found with id: " + planId));

        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(plan.getDuration());

        Subscription subscription = Subscription.builder()
                .userId(String.valueOf(userId))
                .plan(plan)
                .startDate(startDate)
                .endDate(endDate)
                .active(true)
                .build();

        subscription  = subscriptionRepository.save(subscription);
        return toDTO(subscription);
        // throw new UnsupportedOperationException("Unimplemented method 'subscribe'");
    }

    private SubscriptionDTO toDTO(Subscription subscription) {
        return SubscriptionDTO.builder()
                .id(subscription.getId())
                .userId(Long.valueOf(subscription.getUserId()))
                .planId(subscription.getPlan().getId())
                .startDate(subscription.getStartDate())
                .endDate(subscription.getEndDate())
                .active(subscription.getActive())
                .build();
    }
    @Override
    public SubscriptionDTO getSubscription(Long userId) {
        // TODO Auto-generated method stub
        Subscription sub = subscriptionRepository.findByUserId(userId)
                .orElseThrow(()-> new ResourceNotFoundException("Subscription not found for user ID: " + userId));
            return toDTO(sub);
                // throw new UnsupportedOperationException("Unimplemented method 'getSubscription'");
    }


    @Override
    public List<SubscriptionDTO> getAllPlans() {
        // TODO Auto-generated method stub
        return planRepository.findAll().stream()
                .map(plan -> SubscriptionDTO.builder()
                        .id(plan.getId())
                        .name(plan.getName())
                        .price(plan.getPrice())
                        .duration(plan.getDuration())
                        .build())
                .collect(Collectors.toList());
        // throw new UnsupportedOperationException("Unimplemented method 'getAllPlans'");
    }



}
