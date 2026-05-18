package com.simone.lms.payload.request;

import com.simone.lms.domain.PaymentGateway;
import com.simone.lms.domain.PaymentType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentInitiateRequest {

    @NotNull(message = "User ID is mandatory")
    private Long userId;

    private Long bookLoanId;

    @NotNull(message = "Payment type is mandatory")
    private PaymentType paymentType;

    @NotNull(message = "Payment gateway is mandatory")
    private PaymentGateway paymentGateway; // RAZORPAY or STRIPE

    @NotNull(message = "Amount is mandatory")
    private Long amount;

//    @Size(min = 3, max = 3, message = "Currency must be 3 letter code (e.g. USD, EUR)")
//    private String currency = "EUR";

    @Size(max = 500, message = "Description must not exceed 500 characters")
    private String description;

    private Long fineId;
    private Long subscriptionId;

    // Return URLs for payment gateway redirects
    @Size(max = 500, message = "Success URL must not exceed 500 characters")
    private String successURL;

    @Size(max = 500, message = "Cancel URL must not exceed 500 characters")
    private String cancelURL;

}
