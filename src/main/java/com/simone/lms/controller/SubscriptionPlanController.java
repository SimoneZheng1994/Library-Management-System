package com.simone.lms.controller;

import com.simone.lms.model.SubscriptionPlan;
import com.simone.lms.payload.dto.SubscriptionPlanDTO;
import com.simone.lms.payload.response.ApiResponse;
import com.simone.lms.services.SubscriptionPlanService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/subscription-plans")
public class SubscriptionPlanController {

    private final SubscriptionPlanService subscriptionPlanService;

    @GetMapping("/{subscriptionId}")
    public ResponseEntity<?> getSubscription(@PathVariable Long subscriptionId) throws Exception {
        SubscriptionPlanDTO dto = subscriptionPlanService.getSubscriptionPlan(subscriptionId);

        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<?> getAllSubscriptionPlans() throws Exception {
        List<SubscriptionPlanDTO> planDTOList = subscriptionPlanService.getAllSubscriptionPlan();
        return ResponseEntity.ok(planDTOList);
    }

    @PostMapping("/admin/create-subscription")
    public ResponseEntity<?> createSubscriptionPlan(@Valid @RequestBody SubscriptionPlanDTO subscriptionPlanDTO) throws Exception {
        SubscriptionPlanDTO planDTO = subscriptionPlanService.createSubscriptionPlan(subscriptionPlanDTO);

        return ResponseEntity.ok(planDTO);

    }

    @PutMapping("/admin/{subscriptionId}")
    public ResponseEntity<?> updateSubscriptionPlan(@Valid @RequestBody SubscriptionPlanDTO subscriptionPlanDTO,
                                                    @PathVariable Long subscriptionId) throws Exception {
        SubscriptionPlanDTO planDTO = subscriptionPlanService.updateSubscriptionPlan(subscriptionId, subscriptionPlanDTO);

        return ResponseEntity.ok(planDTO);

    }

    @DeleteMapping("/admin/{subscriptionId}")
    public ResponseEntity<?> deleteSubscriptionPlan(@PathVariable Long subscriptionId) throws Exception {
        subscriptionPlanService.deleteSubscriptionPlan(subscriptionId);

        ApiResponse response = new ApiResponse("Plan deleted successfully", true);
        return ResponseEntity.ok(response);

    }



}
