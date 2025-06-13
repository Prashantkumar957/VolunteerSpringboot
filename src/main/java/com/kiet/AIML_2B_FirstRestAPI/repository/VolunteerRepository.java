package com.kiet.AIML_2B_FirstRestAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Import the new Volunteer model
import com.kiet.AIML_2B_FirstRestAPI.model.Volunteer;

@Repository
public interface VolunteerRepository extends JpaRepository<Volunteer, Integer> { // Renamed and changed generic type

}