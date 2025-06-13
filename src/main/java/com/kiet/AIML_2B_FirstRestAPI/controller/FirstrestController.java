package com.kiet.AIML_2B_FirstRestAPI.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Import the new Volunteer model
import com.kiet.AIML_2B_FirstRestAPI.model.Volunteer;

@RestController
public class FirstrestController {

	@RequestMapping("/hello")
	public String hello() {
		return "Hello Volunteer!"; // Changed message for context
	}

	@RequestMapping("/volunteer") // Changed path from /student to /volunteer
	public Volunteer getVolunteer() {
		// Create a new Volunteer object with id, name, email, and availability
		return new Volunteer(101, "Alice Smith", "alice.smith@example.com", "Weekends");
	}

}