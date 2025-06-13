package com.kiet.AIML_2B_FirstRestAPI.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping; // Added for PUT
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController; // Uncommented and active

// Import the new Volunteer model
import com.kiet.AIML_2B_FirstRestAPI.model.Volunteer;
// Import the new Volunteer service
import com.kiet.AIML_2B_FirstRestAPI.service.VolunteerService;

@RestController // This must be active for it to be a REST controller
@RequestMapping("/api") // Optional: Added a base path for REST API for better separation
public class VolunteerController { // Renamed from StudentController
	private VolunteerService service; // Changed to VolunteerService

	public VolunteerController(VolunteerService service) { // Changed constructor parameter
		super();
		this.service = service;
	}

	@GetMapping("/volunteers") // Changed path from /students to /volunteers
	public List<Volunteer> getAllVolunteers() { // Changed method name and return type
		return service.retrieveAllVolunteers(); // Changed service method call
	}

	// http://localhost:9090/api/volunteers/101
	// Path Variable
	@GetMapping("/volunteers/{id}") // Changed path
	public Volunteer getVolunteer(@PathVariable int id) { // Changed method name and return type
		return service.retrieveVolunteerById(id); // Changed service method call
	}

	@PostMapping("/volunteers") // Changed path
	public Volunteer storeVolunteer(@RequestBody Volunteer volunteer) { // Changed parameter and return type
		return service.saveVolunteer(volunteer); // Changed service method call
	}

	@PutMapping("/volunteers/{id}") // NEW: For updating a volunteer
	public Volunteer updateVolunteer(@PathVariable int id, @RequestBody Volunteer volunteer) {
		volunteer.setId(id); // Ensure the ID from the path is used
		return service.saveVolunteer(volunteer); // Re-use save, as it handles both create and update
	}

	@DeleteMapping("/volunteers/{id}") // Changed path
	public void removeVolunteer(@PathVariable int id) { // Changed method name
		service.remove(id); // Changed service method call
	}

	// NEW: Endpoint to get available volunteers
	@GetMapping("/volunteers/available")
	public List<Volunteer> getAvailableVolunteers() {
		return service.retrieveAvailableVolunteers(); // New service method call
	}
}