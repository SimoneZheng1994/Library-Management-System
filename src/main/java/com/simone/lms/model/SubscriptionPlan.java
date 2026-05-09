package com.simone.lms.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubscriptionPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String planCode;

    @Column(nullable = false, length = 100)
    private String name;

    private String description;

    @Column(nullable = false)
    private Integer durationInDays;

    @Column(nullable = false)
    private BigDecimal price;

    private String currency = "EUR";

    @Column(nullable = false)
    @Positive(message = "Max book must be positive")
    private Integer maxBookAllowed;

    @Column(nullable = false)
    @Positive(message = "Max days must be positive")
    private Integer maxDaysPerBook;

    private Integer displayOrder = 0;

    private Boolean isActive = true;

    private Boolean isFeatured = false;

    private String badgeText;

    private String adminNotes;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private String createdBy;

    private String updatedBy;

}
