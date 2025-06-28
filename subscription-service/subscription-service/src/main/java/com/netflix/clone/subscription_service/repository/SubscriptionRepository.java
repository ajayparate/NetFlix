package com.netflix.clone.subscription_service.repository;

// import java.lang.StackWalker.Option;
import java.util.Optional;
import java.util.concurrent.Flow.Subscription;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long > {
    
    // Additional query methods can be defined here if needed
    // For example, to find subscriptions by user ID or plan ID
    // List<Subscription> findByUserId(Long userId);
    // List<Subscription> findByPlanId(Long planId);
    Optional<Subscription> findByUserIdAndPlanId(Long userId);

}
