package org.example.practical.service;

import org.example.practical.model.*;
import org.example.practical.util.PriceCalculator;

import java.time.LocalDateTime;
import java.util.*;

public class FlightService {
    private final List<Flight> flights = new ArrayList<>();
    private final List<FlightRoute> routes = new ArrayList<>();

    public FlightService(List<Flight> flights, List<FlightRoute> routes) {
        this.flights.addAll(flights);
        this.routes.addAll(routes);
    }

    public Flight createFlight(FlightRoute route, LocalDateTime dateTime, Airplane airplane) {
        Flight flight = new Flight(route, dateTime, airplane);
        flights.add(flight);
        return flight;
    }

    public List<Flight> getAllFlights() {
        return flights;
    }
}
