package com.netflix.clone.subscription_service.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Subscription {

    private Long id;

    private String userId; // User ID from the user service

    @ManyToOne
    @JoinColumn(name = "plan_id", nullable = false)
    private Plan plan; // Foreign key to Plan entity

    private LocalDate startDate; // Subscription start date

    private LocalDate endDate; // Subscription end date

    private Boolean active; // Subscription status (active/inactive)

}
