package org.example.practical.service;

import org.example.practical.model.*;

import java.util.*;

public class BookingService {
    private final List<Flight> flights = new ArrayList<>();
    private final List<Booking> bookings = new ArrayList<>();
    private final List<Ticket> tickets = new ArrayList<>();

    public BookingService(List<Flight> flights, List<Booking> bookings, List<Ticket> tickets) {
        this.flights.addAll(flights);
        this.bookings.addAll(bookings);
        this.tickets.addAll(tickets);
    }

    public Booking reserveSeat(User user, Flight flight, Seat seat) {
        if (seat == null) {
            seat = flight.getAvailableSeats().get(0);
        }
        if (seat.isTaken()) return null;

        seat.setTaken(true);
        Ticket ticket = new Ticket(flight, user, seat);
        Booking booking = new Booking(ticket);
        bookings.add(booking);
        user.addTicket(ticket);
        return booking;
    }

    public void expireOldBookings() {
        bookings.removeIf(booking -> {
            boolean expired = booking.isExpired();
            if (expired) {
                booking.getTicket().getSeat().setTaken(false);
            }
            return expired;
        });
    }

    public List<Booking> getAllBookings() {
        return bookings;
    }
}
