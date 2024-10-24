package com.example.group5._nd.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.group5._nd.Entity.eventEntity;

public interface EventRepository extends JpaRepository<eventEntity, Integer> {
     List<eventEntity> findByEventOrgIdGreaterThan(Integer id);

    @Query(value = "SELECT * FROM org", nativeQuery = true)
     List<eventEntity> findAllNative();
}
