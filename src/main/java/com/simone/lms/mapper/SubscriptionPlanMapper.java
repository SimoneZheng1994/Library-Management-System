package com.simone.lms.mapper;

import com.simone.lms.model.SubscriptionPlan;
import com.simone.lms.payload.dto.SubscriptionPlanDTO;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionPlanMapper {

    public SubscriptionPlanDTO toDTO(SubscriptionPlan plan) {

        if (plan == null) {
            return null;
        }

        SubscriptionPlanDTO dto = new SubscriptionPlanDTO();
        dto.setId(plan.getId());
        dto.setPlanCode(plan.getPlanCode());
        dto.setName(plan.getName());
        dto.setDescription(plan.getDescription());
        dto.setDurationInDays(plan.getDurationInDays());
        dto.setPrice(plan.getPrice());
        dto.setCurrency(plan.getCurrency());
        dto.setMaxBookAllowed(plan.getMaxBookAllowed());
        dto.setMaxDaysPerBook(plan.getMaxDaysPerBook());
        dto.setDisplayOrder(plan.getDisplayOrder());
        dto.setIsActive(plan.getIsActive());
        dto.setBadgeText(plan.getBadgeText());
        dto.setAdminNotes(plan.getAdminNotes());
        dto.setCreatedAt(plan.getCreatedAt());
        dto.setUpdatedAt(plan.getUpdatedAt());
        dto.setCreatedBy(plan.getCreatedBy());
        dto.setUpdatedBy(plan.getUpdatedBy());

        return dto;
    }

    public SubscriptionPlan toEntity(SubscriptionPlanDTO dto) {

        if (dto == null) {
            return null;
        }

        SubscriptionPlan plan = new SubscriptionPlan();

        plan.setId(dto.getId());
        plan.setPlanCode(dto.getPlanCode());
        plan.setName(dto.getName());
        plan.setDescription(dto.getDescription());
        plan.setDurationInDays(dto.getDurationInDays());
        plan.setPrice(dto.getPrice());
        plan.setCurrency(dto.getCurrency() != null ? dto.getCurrency() : "EUR");
        plan.setMaxBookAllowed(dto.getMaxBookAllowed());
        plan.setMaxDaysPerBook(dto.getMaxDaysPerBook());
        plan.setDisplayOrder(dto.getDisplayOrder() != null ? dto.getDisplayOrder() : 0);
        plan.setIsActive(dto.getIsActive() != null ? dto.getIsActive() : true);
        plan.setIsFeatured(dto.getIsFeatured() != null ? dto.getIsFeatured() : false);
        plan.setBadgeText(dto.getBadgeText());
        plan.setAdminNotes(dto.getAdminNotes());
        plan.setCreatedAt(dto.getCreatedAt());
        plan.setUpdatedAt(dto.getUpdatedAt());

        return plan;

    }

    public void updateEntity(SubscriptionPlan plan, SubscriptionPlanDTO dto) {


        if (plan == null || dto == null) {
            return;
        }

        if (dto.getName() == null) {
            plan.setName(dto.getName());
        }

        if (dto.getDescription() == null) {
            plan.setDescription(dto.getDescription());
        }

        if (dto.getDurationInDays() != null) {
            plan.setDurationInDays(dto.getDurationInDays());
        }

        if (dto.getPrice() != null) {
            plan.setPrice(dto.getPrice());
        }

        if (dto.getCurrency() != null) {
            plan.setCurrency(dto.getCurrency());
        }

        if (dto.getMaxBookAllowed() != null) {
            plan.setMaxBookAllowed(dto.getMaxBookAllowed());
        }

        if (dto.getMaxDaysPerBook() != null) {
            plan.setMaxDaysPerBook(dto.getMaxDaysPerBook());
        }

        if (dto.getDisplayOrder() != null) {
            plan.setDisplayOrder(dto.getDisplayOrder());
        }

        if (dto.getDisplayOrder() != null) {
            plan.setDisplayOrder(dto.getDisplayOrder());
        }

        if (dto.getIsActive() != null) {
            plan.setIsActive(dto.getIsActive());
        }

        if (dto.getIsFeatured() != null) {
            plan.setIsFeatured(dto.getIsFeatured());
        }

        if (dto.getBadgeText() != null) {
            plan.setBadgeText(dto.getBadgeText());
        }

        if (dto.getAdminNotes() != null) {
            plan.setAdminNotes(dto.getAdminNotes());
        }

        if (dto.getUpdatedBy() != null) {
            plan.setUpdatedBy(dto.getUpdatedBy());
        }
    }


}
