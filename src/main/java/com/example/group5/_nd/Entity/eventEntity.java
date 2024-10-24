package com.example.group5._nd.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "org")
public class eventEntity {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name = "event_org_id")
     private Integer eventOrgId;  // Changed from event_org_id to eventOrgId

     @Column(name = "contact")
     private Integer contact;

     // Default constructor
     public eventEntity() {
     }

     // Parameterized constructor
     public eventEntity(Integer eventOrgId, Integer contact) {
          // this.eventOrgId = eventOrgId;
          this.contact = contact;
     }

     // Updated getter and setter names
     public Integer getEventOrgId() {
          return eventOrgId;
     }

     public void setEventOrgId(Integer eventOrgId) {
          this.eventOrgId = eventOrgId;
     }

     public Integer getContact() {
          return contact;
     }

     public void setContact(Integer contact) {
          this.contact = contact;
     }
}