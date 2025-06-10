package org.example.practical.model;

import java.time.LocalDateTime;

public class Booking {
    private Ticket ticket;
    private LocalDateTime bookingTime;
    private static final long EXPIRATION_HOURS = 24;

    public Booking(Ticket ticket) {
        this.ticket = ticket;
        this.bookingTime = LocalDateTime.now();
    }

    public Ticket getTicket() { return ticket; }
    public LocalDateTime getBookingTime() { return bookingTime; }

    public boolean isExpired() {
        return bookingTime.plusHours(EXPIRATION_HOURS).isBefore(LocalDateTime.now());
    }
}
