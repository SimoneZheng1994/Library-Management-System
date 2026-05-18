package com.simone.lms.services;

import com.simone.lms.payload.request.PaymentInitiateRequest;
import com.simone.lms.payload.response.PaymentInitiateResponse;

public interface PaymentService {

    PaymentInitiateResponse initiatePayment(PaymentInitiateRequest request);

}
