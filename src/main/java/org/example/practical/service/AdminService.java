package org.example.practical.service;


import org.example.practical.model.*;

import java.util.*;

public class AdminService {
    private final FlightService flightService;
    private final TicketService ticketService;

    public AdminService(FlightService flightService, TicketService ticketService) {
        this.flightService = flightService;
        this.ticketService = ticketService;
    }

    public Map<Flight, Integer> getSoldTicketsCountPerFlight() {
        Map<Flight, Integer> map = new HashMap<>();
        for (Flight flight : flightService.getAllFlights()) {
            int sold = 0;
            for (Seat seat : flight.getSeats()) {
                if (seat.isTaken()) {
                    sold++;
                }
            }
            map.put(flight, sold);
        }
        return map;
    }

    public Map<Flight, Double> getRevenuePerFlight() {
        Map<Flight, Double> map = new HashMap<>();
        for (Flight flight : flightService.getAllFlights()) {
            double total = ticketService.calculateTotalRevenueForFlight(flight);
            map.put(flight, total);
        }
        return map;
    }

//    public Map<String, Integer> getLoadByRoute() {
//        Map<String, Integer> map = new HashMap<>();
//        for (Flight flight : flightService.getAllFlights()) {
//            String route = flight.getRoute().toString();
//            int taken = 0;
//            for (Seat seat : flight.getSeats()) {
//                if (seat.isTaken()) taken++;
//            }
//            map.put(route, map.getOrDefault(route, 0) + taken);
//        }
//        return map;
//    }
}
