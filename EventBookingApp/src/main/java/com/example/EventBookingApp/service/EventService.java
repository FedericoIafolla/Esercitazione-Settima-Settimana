package com.example.EventBookingApp.service;

import com.example.EventBookingApp.model.Event;
import com.example.EventBookingApp.model.App;
import com.example.EventBookingApp.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public Event createEvent(Event event, App organizer) {
        event.setOrganizer(organizer);
        return eventRepository.save(event);
    }

    public List<Event> findAllEvents() {
        return eventRepository.findAll();
    }

    public Optional<Event> findEventById(Long eventId) {
        return eventRepository.findById(eventId);
    }

    public Event updateEvent(Event event, Long eventId, App organizer) {
        Optional<Event> existingEvent = eventRepository.findById(eventId);
        if (existingEvent.isPresent() && existingEvent.get().getOrganizer().equals(organizer)) {
            existingEvent.get().setTitle(event.getTitle());
            existingEvent.get().setDescription(event.getDescription());
            existingEvent.get().setDate(event.getDate());
            existingEvent.get().setLocation(event.getLocation());
            existingEvent.get().setAvailableSeats(event.getAvailableSeats());
            return eventRepository.save(existingEvent.get());
        }
        throw new RuntimeException("Event not found or user is not the organizer");
    }

    public void deleteEvent(Long eventId, App organizer) {
        Optional<Event> event = eventRepository.findById(eventId);
        if (event.isPresent() && event.get().getOrganizer().equals(organizer)) {
            eventRepository.delete(event.get());
        } else {
            throw new RuntimeException("Event not found or user is not the organizer");
        }
    }
}
