package com.netflix.clone.subscription_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.netflix.clone.subscription_service.model.Plan;

public interface PlanRepository extends JpaRepository<Plan, Long>{

}
