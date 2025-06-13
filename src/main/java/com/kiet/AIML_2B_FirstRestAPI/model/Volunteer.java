package com.kiet.AIML_2B_FirstRestAPI.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Volunteer implements Comparable<Volunteer> { // Renamed class
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private String email;      // New field for volunteer's email
	private String availability; // New field for volunteer's availability

	public Volunteer() {
		super();
	}

	public Volunteer(int id, String name, String email, String availability) { // Updated constructor
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.availability = availability;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() { // New getter
		return email;
	}

	public void setEmail(String email) { // New setter
		this.email = email;
	}

	public String getAvailability() { // New getter
		return availability;
	}

	public void setAvailability(String availability) { // New setter
		this.availability = availability;
	}

	@Override
	public String toString() {
		return "Volunteer [id=" + id + ", name=" + name + ", email=" + email + ", availability=" + availability + "]"; // Updated toString
	}

	@Override
	public int compareTo(Volunteer o) {
		return this.id - o.id;
	}

}