package com.simone.lms.services.impl;

import com.simone.lms.mapper.SubscriptionPlanMapper;
import com.simone.lms.model.SubscriptionPlan;
import com.simone.lms.model.User;
import com.simone.lms.payload.dto.SubscriptionPlanDTO;
import com.simone.lms.repository.SubscriptionPlanRepository;
import com.simone.lms.services.SubscriptionPlanService;
import com.simone.lms.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubscriptionPlanServiceImpl implements SubscriptionPlanService {

    private final SubscriptionPlanRepository subscriptionPlanRepository;
    private final SubscriptionPlanMapper planMapper;
    private final UserService userService;


    @Override
    public SubscriptionPlanDTO createSubscriptionPlan(SubscriptionPlanDTO planDTO) throws Exception {

        if (subscriptionPlanRepository.existsByPlanCode(planDTO.getPlanCode())) {
            throw new Exception("Plan code already exists");
        }

        SubscriptionPlan plan = planMapper.toEntity(planDTO);
        User currentUser = userService.getCurrentUser();
        plan.setCreatedBy(currentUser.getFullName());
        SubscriptionPlan savedPlan =subscriptionPlanRepository.save(plan);

        return planMapper.toDTO(savedPlan);
    }

    @Override
    public SubscriptionPlanDTO updateSubscriptionPlan(Long planId, SubscriptionPlanDTO planDTO) throws Exception {

        SubscriptionPlan existingPlan = subscriptionPlanRepository.findById(planId)
                .orElseThrow(() -> new Exception("Plan not found")
                );

        planMapper.updateEntity(existingPlan, planDTO);
        User currentUser = userService.getCurrentUser();
        existingPlan.setUpdatedBy(currentUser.getFullName());
        SubscriptionPlan updatedPlan = subscriptionPlanRepository.save(existingPlan);

        return planMapper.toDTO(updatedPlan);

    }

    @Override
    public void deleteSubscriptionPlan(Long planId) throws Exception{

        SubscriptionPlan existingPlan = subscriptionPlanRepository.findById(planId)
                .orElseThrow(() -> new Exception("Plan not found")
                );

        subscriptionPlanRepository.deleteById(planId);

    }

    @Override
    public List<SubscriptionPlanDTO> getAllSubscriptionPlan() {

        List<SubscriptionPlan> planList = subscriptionPlanRepository.findAll();

        return planList.stream().map(
                planMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public SubscriptionPlanDTO getSubscriptionPlan(Long planId) throws  Exception {

        SubscriptionPlan existingPlan = subscriptionPlanRepository.findById(planId)
                .orElseThrow(() -> new Exception("Plan not found")
                );


        return planMapper.toDTO(existingPlan);
    }
}
