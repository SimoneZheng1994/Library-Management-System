package com.simone.lms.repository;

import com.simone.lms.model.SubscriptionPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionPlanRepository extends JpaRepository<SubscriptionPlan, Long> {

    Boolean existsByPlanCode(String planCode);


}
