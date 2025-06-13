package com.kiet.AIML_2B_FirstRestAPI.service;

import java.util.List;
import java.util.stream.Collectors; // Added for stream operations

import org.springframework.stereotype.Service;

// Import the new Volunteer model
import com.kiet.AIML_2B_FirstRestAPI.model.Volunteer;
// Import the new Volunteer repository
import com.kiet.AIML_2B_FirstRestAPI.repository.VolunteerRepository;

@Service
public class VolunteerService { 

	private VolunteerRepository repository;

	public VolunteerService(VolunteerRepository repository) { 
		this.repository = repository;
	}

	public List<Volunteer> retrieveAllVolunteers() { 
		return repository.findAll(); // Uses VolunteerRepository
	}

	public Volunteer retrieveVolunteerById(int id) { 
		
		return repository.findById(id).orElse(null);
	}

	public Volunteer saveVolunteer(Volunteer volunteer) { 
		return repository.save(volunteer); // Uses VolunteerRepository
	}

	public void remove(int id) {
		repository.deleteById(id); // Uses VolunteerRepository
	}

	public List<Volunteer> retrieveAvailableVolunteers() {
		
		return repository.findAll().stream()
				.filter(v -> v.getAvailability() != null && !v.getAvailability().trim().isEmpty())
				.collect(Collectors.toList());
	}
}