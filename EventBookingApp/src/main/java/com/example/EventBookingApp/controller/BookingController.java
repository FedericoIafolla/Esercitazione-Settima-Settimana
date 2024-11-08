package com.example.EventBookingApp.controller;

import com.example.EventBookingApp.model.Booking;
import com.example.EventBookingApp.model.Event;
import com.example.EventBookingApp.model.App;
import com.example.EventBookingApp.service.BookingService;
import com.example.EventBookingApp.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private EventService eventService;

    @PostMapping("/book")
    public Booking createBooking(@RequestParam Long eventId, @RequestParam Long userId) {
        Event event = eventService.findEventById(eventId).orElseThrow(() -> new RuntimeException("Event not found"));
        App user = new App(); // Retrieve user by ID (You might want to implement this)
        return bookingService.createBooking(user, event);
    }

    @GetMapping("/user/{userId}")
    public List<Booking> getBookingsForUser(@PathVariable Long userId) {
        App user = new App(); // Retrieve user by ID (You might want to implement this)
        return bookingService.findBookingsByUser(user);
    }

    @DeleteMapping("/cancel/{bookingId}")
    public void cancelBooking(@PathVariable Long bookingId, @RequestParam Long userId) {
        App user = new App(); // Retrieve user by ID (You might want to implement this)
        bookingService.cancelBooking(bookingId, user);
    }
}
