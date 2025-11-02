package com.app.service;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import com.app.config.TwilioConfig;
import org.springframework.stereotype.Service;

@Service
public class SmsService {

    private final TwilioConfig twilioConfig;

    public SmsService(TwilioConfig twilioConfig) {
        this.twilioConfig = twilioConfig;
    }


    public String sendSms(String to, String messageBody) {
        try {
            Message message = Message.creator(
                    new PhoneNumber(to),
                    new PhoneNumber(twilioConfig.getTwilioPhoneNumber()),
                    messageBody
            ).create();

            return "SMS Sent Successfully! SID: " + message.getSid();
        } catch (Exception e) {
            return "Failed to send SMS: " + e.getMessage();
        }
    }
}

