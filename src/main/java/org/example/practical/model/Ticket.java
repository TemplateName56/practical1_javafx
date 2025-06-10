package org.example.practical.model;

import org.example.practical.util.PriceCalculator;

import java.io.Serializable;

public class Ticket implements Serializable {
    public enum Status { RESERVED, PAID }

    private Flight flight;
    private User passenger;
    private Seat seat;
    private Status status;

    public Ticket() {}

    public Ticket(Flight flight, User passenger, Seat seat) {
        this.flight = flight;
        this.passenger = passenger;
        this.seat = seat;
        this.status = Status.RESERVED;
    }

    public Flight getFlight() { return flight; }
    public User getPassenger() { return passenger; }
    public Seat getSeat() { return seat; }
    public Status getStatus() { return status; }

    public void setStatus(Status status) {
        this.status = status;
    }

    public double getPrice() {
        return PriceCalculator.calculatePrice(flight);
    }

    @Override
    public String toString() {
        return "Рейс: " + flight + " | Місце: " + seat + " | Статус: " + (status == Status.RESERVED ? "Резерв" : "Оплачено");
    }
}
