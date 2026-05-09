package com.simone.lms.payload.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubscriptionPlanDTO {

    private Long id;

    @NotBlank(message = "Plan code is required")
    private String planCode;

    @NotBlank(message = "Name is required")
    private String name;

    private String description;

    @NotNull(message = "Duration is required")
    @Positive(message = "Duration must be positive")
    private Integer durationInDays;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be positive")
    private BigDecimal price;

    private String currency;

    @NotNull(message = "Max books allowed is required")
    @Positive(message = "Max book must be positive")
    private Integer maxBookAllowed;

    @NotNull(message = "Max days per book is required")
    @Positive(message = "Max days must be positive")
    private Integer maxDaysPerBook;

    private Integer displayOrder;

    private Boolean isActive;

    private Boolean isFeatured;

    private String badgeText;

    private String adminNotes;

//    private Double priceInMajorUnits;
//    private Double monthlyEquivalentPrice;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String createdBy;
    private String updatedBy;

}
