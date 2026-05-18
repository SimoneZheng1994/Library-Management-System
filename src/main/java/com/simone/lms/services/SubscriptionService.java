package com.simone.lms.services;

import com.simone.lms.payload.dto.SubscriptionDTO;

import java.awt.print.Pageable;
import java.util.List;

public interface SubscriptionService {

    SubscriptionDTO subscribe(SubscriptionDTO subscriptionDTO) throws Exception;
    SubscriptionDTO getUsersActiveSubscription(Long userId);
    SubscriptionDTO cancelSubscription(Long subscriptionId, String reason);
    SubscriptionDTO activeSubscription(Long subscriptionId, Long paymentId);
    List<SubscriptionDTO> getAllSubscriptions(Pageable pageable);

}
