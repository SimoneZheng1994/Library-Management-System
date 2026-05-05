package com.simone.lms.services;

import jakarta.mail.MessagingException;

public interface EmailService {

    void sendEmail (String to, String subject, String Body) throws MessagingException;


}
