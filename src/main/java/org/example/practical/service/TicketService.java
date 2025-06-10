package org.example.practical.service;

import org.example.practical.model.*;
import org.example.practical.util.PriceCalculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TicketService {
    private final Random random = new Random();
    private final List<Ticket> tickets = new ArrayList<>();

    public TicketService(List<Ticket> tickets) {
        this.tickets.addAll(tickets);
    }

    public boolean simulatePayment(User user, Ticket ticket) {
        boolean paid = random.nextBoolean(); // 50/50 випадкова оплата
        if (paid && ticket.getStatus() == Ticket.Status.RESERVED) {
            ticket.setStatus(Ticket.Status.PAID);
            return true;
        }
        return false;
    }

    public double calculateTotalRevenueForFlight(Flight flight) {
        double sum = 0;
        for (Seat seat : flight.getSeats()) {
            if (seat.isTaken()) {
                sum += PriceCalculator.calculatePrice(flight);
            }
        }
        return sum;
    }
}
