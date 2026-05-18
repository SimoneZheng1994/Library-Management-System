package com.simone.lms.services;

import com.simone.lms.payload.dto.SubscriptionDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SubscriptionService {

    SubscriptionDTO subscribe(SubscriptionDTO subscriptionDTO) throws Exception;
    SubscriptionDTO getUsersActiveSubscription(Long userId) throws Exception;
    SubscriptionDTO cancelSubscription(Long subscriptionId, String reason) throws Exception;
    SubscriptionDTO activeSubscription(Long subscriptionId, Long paymentId) throws Exception;
    List<SubscriptionDTO> getAllSubscriptions(Pageable pageable);
    void deactivateExpiredSubscriptions() throws Exception;

}
