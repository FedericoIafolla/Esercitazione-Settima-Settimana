package com.example.EventBookingApp.repository;

import com.example.EventBookingApp.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {}
