package com.simone.lms.payload.response;

import com.simone.lms.domain.PaymentGateway;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentInitiateResponse {

    private Long paymentId;
    private PaymentGateway paymentGateway;
    private String transactionId;

    // Razorpay specific fields
//  private String razorPayOrderId;

    private BigDecimal amount;

    //  private String currency;
    private String description;

    // Frontend should redirect user to this URL for payment
    private String checkoutUrl;

    private String message;

    private Boolean success;

}
