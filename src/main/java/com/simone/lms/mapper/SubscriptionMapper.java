package com.simone.lms.mapper;

import com.simone.lms.exception.SubscriptionException;
import com.simone.lms.model.Subscription;
import com.simone.lms.model.SubscriptionPlan;
import com.simone.lms.model.User;
import com.simone.lms.payload.dto.SubscriptionDTO;
import com.simone.lms.repository.SubscriptionPlanRepository;
import com.simone.lms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SubscriptionMapper {

    private final UserRepository userRepository;
    private final SubscriptionPlanRepository planRepository;

    /**
     * Converts a Subscription entity to DTO
     */

    public SubscriptionDTO toDTO(Subscription subscription) {
        if (subscription == null) {
            return null;
        }

        SubscriptionDTO dto = new SubscriptionDTO();
        dto.setId(subscription.getId());

        // User information
        if (subscription.getUser() != null) {
            dto.setUserId(subscription.getUser().getId());
            dto.setUserName(subscription.getUser().getFullName());
            dto.setUserEmail(subscription.getUser().getEmail());
        }

        // Plan information
        if (subscription.getSubscriptionPlan() != null) {
            dto.setPlanId(subscription.getSubscriptionPlan().getId());
        }

        dto.setPlanName(subscription.getPlanName());
        dto.setPlanCode(subscription.getPlanCode());
        dto.setPrice(subscription.getPrice());

        dto.setStartDate(subscription.getStartDate());
        dto.setEndDate(subscription.getEndDate());
        dto.setIsActive(subscription.getIsActive());
        dto.setMaxBookAllowed(subscription.getMaxBookAllowed());
        dto.setMaxDaysPerBook(subscription.getMaxDaysPerBook());
        dto.setAutoRenew(subscription.getAutoRenew());
        dto.setCancelledAt(subscription.getCancelledAt());
        dto.setCancellationReason(subscription.getCancellationReason());
        dto.setNotes(subscription.getNotes());
        dto.setCreatedAt(subscription.getCreatedAt());
        dto.setUpdatedAt(subscription.getUpdatedAt());

        // Calculated Fields
        dto.setDaysRemaining(subscription.getDaysRemaining());
        dto.setIsValid(subscription.isValid());
        dto.setIsExpired(subscription.isExpired());

        return dto;
    }

    /**
     * Convert DTO to Entity
     */

    public Subscription toEntity(SubscriptionDTO dto) throws SubscriptionException {

        if (dto == null) {
            return null;
        }

        Subscription subscription = new Subscription();
        subscription.setId(dto.getId());

        // Map user
        if (dto.getUserId() != null) {
            User user = userRepository.findById(dto.getUserId())
                    .orElseThrow(() -> new SubscriptionException("User not found with ID " + dto.getId()));
            subscription.setUser(user);
        }

        // Map plan
        if(dto.getPlanId() != null) {
            SubscriptionPlan plan = planRepository.findById(dto.getPlanId())
                    .orElseThrow(() -> new SubscriptionException("Subscription plan not found with ID " + dto.getPlanId()));
            subscription.setSubscriptionPlan(plan);
        }

        subscription.setPlanName(dto.getPlanName());
        subscription.setPlanCode(dto.getPlanCode());
        subscription.setPrice(dto.getPrice());
        subscription.setStartDate(dto.getStartDate());
        subscription.setEndDate(dto.getEndDate());
        subscription.setIsActive(dto.getIsActive() != null ? dto.getIsActive() : true);
        subscription.setMaxBookAllowed(dto.getMaxBookAllowed());
        subscription.setMaxDaysPerBook(dto.getMaxDaysPerBook());
        subscription.setAutoRenew(dto.getAutoRenew() != null ? dto.getAutoRenew() : false);
        subscription.setCancelledAt(dto.getCancelledAt());
        subscription.setCancellationReason(dto.getCancellationReason());
        subscription.setNotes(dto.getNotes());

        return subscription;
    }

    /**
     * Convert a list of subscriptions to DTOs
     */

    public List<SubscriptionDTO> toDTOList(List<Subscription> subscriptionList) {
        if (subscriptionList == null) {
            return null;
        }

        return subscriptionList.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
