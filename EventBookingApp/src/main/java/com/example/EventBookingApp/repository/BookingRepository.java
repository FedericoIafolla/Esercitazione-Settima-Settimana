package com.example.EventBookingApp.repository;

import com.example.EventBookingApp.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {}
