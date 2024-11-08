package com.example.EventBookingApp.controller;

import com.example.EventBookingApp.model.Booking;
import com.example.EventBookingApp.model.Event;
import com.example.EventBookingApp.model.App;
import com.example.EventBookingApp.service.BookingService;
import com.example.EventBookingApp.service.EventService;
import com.example.EventBookingApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private EventService eventService;

    @Autowired
    private UserService userService;

    @PostMapping("/book")
    public Booking createBooking(@RequestParam Long eventId) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        App user = userService.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        Event event = eventService.findEventById(eventId).orElseThrow(() -> new RuntimeException("Event not found"));
        return bookingService.createBooking(user, event);
    }

    @GetMapping("/user")
    public List<Booking> getBookingsForUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        App user = userService.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        return bookingService.findBookingsByUser(user);
    }

    @DeleteMapping("/cancel/{bookingId}")
    public void cancelBooking(@PathVariable Long bookingId) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        App user = userService.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        bookingService.cancelBooking(bookingId, user);
    }
}
