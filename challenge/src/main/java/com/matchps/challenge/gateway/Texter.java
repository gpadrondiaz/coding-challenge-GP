package com.matchps.challenge.gateway;

import org.springframework.stereotype.Component;

@Component
public class Texter {
    public void  sendText(String phone, String message){
        // No code required.
        // Assume this function will send a text message.
        System.out.format("Texter::SMS sent to %s. Msg: %s", phone, message);
    }
}
