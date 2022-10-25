package com.matchps.challenge.gateway;

import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class Scheduler {
    // No code required.
	// Assume this function schedule a callback for a duration of time in seconds
    public void schedule(Integer duration, Function callback, String[] args) {
        // No code required.
        // Assume this function schedule a callback for a duration of time in seconds
        System.out.format("Scheduler::Schedule: callback scheduled for %d seconds later with args %s", duration, args);
    }
}
