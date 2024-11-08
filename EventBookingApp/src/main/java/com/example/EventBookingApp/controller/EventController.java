package com.example.EventBookingApp.controller;

import com.example.EventBookingApp.model.Event;
import com.example.EventBookingApp.model.App;
import com.example.EventBookingApp.service.EventService;
import com.example.EventBookingApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private UserService userService;

    @GetMapping
    public List<Event> getAllEvents() {
        return eventService.findAllEvents();
    }

    @PostMapping
    public Event createEvent(@RequestBody Event event) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        App organizer = userService.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        return eventService.createEvent(event, organizer);
    }
}
