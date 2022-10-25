package com.matchps.challenge.gateway;

import org.springframework.stereotype.Component;

// Process ...
@Component
public class Payment {
	// No code required.
	// Assume this function will charge or refund to the given credit card
	// A negative amount means refund.
    public void process(String uuid, float amount, String creditCard) {
        System.out.format("Payment Processed : user %s amount %d", uuid, amount);
    }
}
