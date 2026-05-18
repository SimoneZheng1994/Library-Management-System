package com.simone.lms.services.impl;

import com.simone.lms.exception.SubscriptionException;
import com.simone.lms.mapper.SubscriptionMapper;
import com.simone.lms.model.Subscription;
import com.simone.lms.model.SubscriptionPlan;
import com.simone.lms.model.User;
import com.simone.lms.payload.dto.SubscriptionDTO;
import com.simone.lms.repository.SubscriptionPlanRepository;
import com.simone.lms.repository.SubscriptionRepository;
import com.simone.lms.services.SubscriptionService;
import com.simone.lms.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubscriptionImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionMapper subscriptionMapper;
    private final UserService userService;
    private final SubscriptionPlanRepository subscriptionPlanRepository;

    @Override
    public SubscriptionDTO subscribe(SubscriptionDTO subscriptionDTO) throws Exception {

        User user = userService.getCurrentUser();
        SubscriptionPlan plan = subscriptionPlanRepository.findById(subscriptionDTO.getPlanId())
                .orElseThrow(() -> new SubscriptionException("Plan not found"));

//        Optional<Sub>

        Subscription subscription = subscriptionMapper.toEntity(subscriptionDTO);
        subscription.initializeFromPlan();
        Subscription savedSubscription = subscriptionRepository.save(subscription);

//      create payment(todo)
        
        return subscriptionMapper.toDTO(savedSubscription);
    }

    @Override
    public SubscriptionDTO getUsersActiveSubscription(Long userId) {
        return null;
    }

    @Override
    public SubscriptionDTO cancelSubscription(Long subscriptionId, String reason) {
        return null;
    }

    @Override
    public SubscriptionDTO activeSubscription(Long subscriptionId, Long paymentId) {
        return null;
    }

    @Override
    public List<SubscriptionDTO> getAllSubscriptions(Pageable pageable) {
        return List.of();
    }
}
