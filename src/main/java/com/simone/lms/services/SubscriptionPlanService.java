package com.simone.lms.services;


import com.simone.lms.payload.dto.SubscriptionPlanDTO;

import java.util.List;

public interface SubscriptionPlanService {


    public SubscriptionPlanDTO createSubscriptionPlan(SubscriptionPlanDTO planDTO) throws Exception;

    public SubscriptionPlanDTO updateSubscriptionPlan(Long planId, SubscriptionPlanDTO planDTO) throws Exception;

    void deleteSubscriptionPlan(Long planId) throws Exception;

    List<SubscriptionPlanDTO> getAllSubscriptionPlan() throws Exception;

    SubscriptionPlanDTO getSubscriptionPlan(Long planId) throws Exception;

}
