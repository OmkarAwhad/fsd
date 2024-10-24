package com.example.group5._nd.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.group5._nd.Entity.eventEntity;
import com.example.group5._nd.Repository.EventRepository;

import jakarta.persistence.Table;

@RestController
@Table(name = "org")  // Change this to match your actual table name
public class eventController {

     @Autowired
     private final EventRepository eventRepository;

     public eventController(EventRepository eventRepository) {
          this.eventRepository = eventRepository;
     }

    // Test Endpoint
     @GetMapping("/test")
     public String getTestName() {
          return "Organiser Controller Working!";
     }

    // Get All Organisers
     @GetMapping("/organisers")
     public List<eventEntity> getAllOrganisers() {
          try {
               return eventRepository.findAll();
          } catch (Exception e) {
               e.printStackTrace();
               return new ArrayList<>();
          }
     }

    // Get Organiser by ID
     @GetMapping("/organisers/{id}")  // Changed from /organiser/{id} to /organisers/{id}
     public ResponseEntity<eventEntity> getOrganiserById(@PathVariable Integer id) {
          Optional<eventEntity> organiser = eventRepository.findById(id);
          
          if (organiser.isPresent()) {
               return ResponseEntity.ok(organiser.get());
          } else {
               return ResponseEntity.notFound().build();
          }
     }

    // Add New Organiser
     @PostMapping("/api/organisers")
     public ResponseEntity<eventEntity> addOrganiser(@RequestBody eventEntity organiser) {
     try {
          eventEntity savedOrganiser = eventRepository.save(organiser);
          return ResponseEntity.ok(savedOrganiser);
     } catch (Exception e) {
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
     }
     }

    // Update Existing Organiser
     @PutMapping("/organiser/{id}")
     public String updateOrganiserById(@PathVariable("id") Integer id, @RequestBody eventEntity organiser) {
          Optional<eventEntity> existingOrganiser = eventRepository.findById(id);
          if (existingOrganiser.isPresent()) {
            //   organiser.setEventOrganiserId(id); // Set the ID to ensure the correct record is updated
               eventRepository.save(organiser);
               return "Organiser with ID " + id + " updated successfully.";
          } else {
               eventRepository.save(organiser);
               return "Organiser with ID " + id + " not found. A new organiser has been created.";
          }
     }

    // Delete Organiser by ID
     @DeleteMapping("/organiser/{id}")
     public String deleteOrganiserById(@PathVariable("id") Integer id) {
          eventRepository.deleteById(id);
          return "Organiser with ID " + id + " deleted.";
     }
}
