package com.example.EventBookingApp.service;

import com.example.EventBookingApp.model.Booking;
import com.example.EventBookingApp.model.Event;
import com.example.EventBookingApp.model.App;
import com.example.EventBookingApp.repository.BookingRepository;
import com.example.EventBookingApp.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private EventRepository eventRepository;

    public Booking createBooking(App user, Event event) {
        if (event.getAvailableSeats() > 0) {
            event.setAvailableSeats(event.getAvailableSeats() - 1);
            Booking booking = new Booking();
            booking.setUser(user);
            booking.setEvent(event);
            eventRepository.save(event);
            return bookingRepository.save(booking);
        }
        throw new RuntimeException("Nessun posto disponibile per questo evento");
    }

    public List<Booking> findBookingsByUser(App user) {
        return bookingRepository.findAll().stream().filter(booking -> booking.getUser().equals(user)).toList();
    }

    public void cancelBooking(Long bookingId, App user) {
        Optional<Booking> booking = bookingRepository.findById(bookingId);
        if (booking.isPresent() && booking.get().getUser().equals(user)) {
            Event event = booking.get().getEvent();
            event.setAvailableSeats(event.getAvailableSeats() + 1);
            eventRepository.save(event);
            bookingRepository.delete(booking.get());
        } else {
            throw new RuntimeException("Prenotazione non trovata o l'utente non è il proprietario");
        }
    }
}
