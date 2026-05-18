package com.simone.lms.controller;


import com.simone.lms.payload.dto.SubscriptionDTO;
import com.simone.lms.payload.response.ApiResponse;
import com.simone.lms.services.SubscriptionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/subscriptions")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @PostMapping("/subscribe")
    public ResponseEntity<?> subscribe(
            @RequestBody SubscriptionDTO subscriptionDTO
    ) throws Exception {
        SubscriptionDTO dto = subscriptionService.subscribe(subscriptionDTO);

        return ResponseEntity.ok(dto);
    }

    @GetMapping("/user/active")
    public ResponseEntity<?> getUsersActiveSubscriptions(@RequestParam(required = false) Long userId) throws Exception {

        SubscriptionDTO dto = subscriptionService.getUsersActiveSubscription(userId);

        return ResponseEntity.ok(dto);
    }

    @GetMapping("/admin")
    public ResponseEntity<?> getAllSubscriptions() {

        int page = 0;
        int size = 10;

        Pageable pageable = PageRequest.of(page, size);
        List<SubscriptionDTO> dtoList = subscriptionService.getAllSubscriptions(pageable);

        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/admin/deactivate-expired")
    public ResponseEntity<?> deactivateAllExpiredSubscriptions() throws Exception {

        subscriptionService.deactivateExpiredSubscriptions();
        ApiResponse response = new ApiResponse("Task done", true);

        return ResponseEntity.ok(response);
    }

    @PostMapping("cancel/{subscriptionId}")
    public ResponseEntity<?> cancelSubscription (
            @PathVariable Long subscriptionId, @RequestParam(required = false) String reason) throws Exception{

        SubscriptionDTO subscriptionDTO = subscriptionService.cancelSubscription(subscriptionId, reason);
        return ResponseEntity.ok(subscriptionDTO);
    }

    @PostMapping("/activate")
    public ResponseEntity<?> activateSubscription (
            @RequestParam Long subscriptionId,
            @RequestParam Long paymentId) throws Exception {

        SubscriptionDTO subscriptionDTO = subscriptionService.activeSubscription(subscriptionId, paymentId);
        return ResponseEntity.ok(subscriptionDTO);
    }

}
