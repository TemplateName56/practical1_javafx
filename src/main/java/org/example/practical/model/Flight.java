package org.example.practical.model;

import org.example.practical.util.PriceCalculator;
import org.example.practical.util.Utilities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Flight implements Serializable {
    private FlightRoute route;
    private LocalDateTime departureTime;
    private Airplane airplane;
    private List<Seat> seats = new ArrayList<>();

    public Flight() {}

    public Flight(FlightRoute route, LocalDateTime departureTime, Airplane airplane) {
        this.route = route;
        this.departureTime = departureTime;
        this.airplane = airplane;
        for (int i = 1; i <= airplane.getSeatCount(); i++) {
            seats.add(new Seat(i));
        }
    }

    public FlightRoute getRoute() { return route; }
    public LocalDateTime getDepartureTime() { return departureTime; }
    public Airplane getAirplane() { return airplane; }
    public List<Seat> getSeats() { return seats; }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public List<Seat> getAvailableSeats() {
        List<Seat> available = new ArrayList<>();
        for (Seat seat : seats) {
            if (!seat.isTaken()) {
                available.add(seat);
            }
        }
        return available;
    }

    @Override
    public String toString() {
        return "Рейс: " + route + " | Час відльоту: " + Utilities.prettyDate(departureTime) + " | " + airplane + " | Місць: " + getAvailableSeats().toArray().length + " | Ціна: " + PriceCalculator.calculatePrice(this);
    }
}