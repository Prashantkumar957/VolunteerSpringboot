package com.kiet.AIML_2B_FirstRestAPI.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes; // Added import for RedirectAttributes

// Import the new Volunteer model
import com.kiet.AIML_2B_FirstRestAPI.model.Volunteer;
// Import the new Volunteer service
import com.kiet.AIML_2B_FirstRestAPI.service.VolunteerService;

@Controller
public class VolunteerControllerMVC { // Renamed from StudentControllerMVC
	private VolunteerService service; // Changed to VolunteerService

	public VolunteerControllerMVC(VolunteerService service) { // Changed constructor parameter
		super();
		this.service = service;
	}

	// http://localhost:9090/volunteers/update/102
	@GetMapping("/volunteers/update/{id}") // Changed path
	public String getVolunteer(Model model, @PathVariable int id) { // Changed method name and parameter type
		Volunteer vol = service.retrieveVolunteerById(id); // Changed service method call and variable name
		if (vol == null) { // Handle case where volunteer is not found
            // Optionally, redirect to error page or list with an error message
            return "redirect:/volunteers"; // Redirect to list if not found
        }
		model.addAttribute("volunteer", vol); // Changed attribute name to "volunteer"
		return "volunteer"; // Returns volunteer.html
	}

	@GetMapping(path = { "/volunteers", "/" }) // Changed path
	public String getAllVolunteers(Model model) { // Changed method name
		List<Volunteer> vols = service.retrieveAllVolunteers(); // Changed service method call and variable name
		Collections.sort(vols); // Still sorts
		model.addAttribute("volunteers", vols); // Changed attribute name to "volunteers"
		return "index"; // index.html will now display volunteers
	}

	// http://localhost:9090/volunteers/add
	@GetMapping("/volunteers/add") // Changed path
	public String addPage(Model model) {
		model.addAttribute("volunteer", new Volunteer()); // Changed attribute and object type
		return "volunteer"; // Returns volunteer.html
	}

	@PostMapping("/volunteers/save") // Changed path
	public String storeVolunteer(@ModelAttribute Volunteer volunteer, RedirectAttributes redirectAttributes) { // Added RedirectAttributes
		Volunteer savedVolunteer = service.saveVolunteer(volunteer); // Capture saved volunteer to get name/id
		redirectAttributes.addFlashAttribute("message", "Volunteer '" + savedVolunteer.getName() + "' saved successfully!");
		redirectAttributes.addFlashAttribute("messageType", "success");
		return "redirect:/volunteers"; // Redirects to the list page with the message
	}

	// http://localhost:9090/volunteers/delete/106
	@GetMapping("/volunteers/delete/{id}") // Changed path
	public String removeVolunteer(@PathVariable int id, RedirectAttributes redirectAttributes) { // Added RedirectAttributes
		String volunteerName = "Unknown Volunteer"; // Default name for message
		try {
			// Attempt to retrieve name before deleting for a better message
			Volunteer volunteerToDelete = service.retrieveVolunteerById(id);
			if (volunteerToDelete != null) {
				volunteerName = volunteerToDelete.getName();
				service.remove(id);
				redirectAttributes.addFlashAttribute("message", "Volunteer '" + volunteerName + "' deleted successfully!");
				redirectAttributes.addFlashAttribute("messageType", "success");
			} else {
				redirectAttributes.addFlashAttribute("message", "Volunteer with ID " + id + " not found.");
				redirectAttributes.addFlashAttribute("messageType", "warning");
			}
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("message", "Error deleting volunteer '" + volunteerName + "': " + e.getMessage());
			redirectAttributes.addFlashAttribute("messageType", "danger");
		}
		return "redirect:/volunteers"; // Redirects to the list page with the message
	}
}