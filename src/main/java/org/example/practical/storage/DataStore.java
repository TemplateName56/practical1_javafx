package org.example.practical.storage;

import org.example.practical.model.*;
import org.example.practical.service.*;

import java.time.LocalDateTime;
import java.util.*;

public class DataStore {
    private static final List<User> users = new ArrayList<>();
    private static final List<FlightRoute> routes = new ArrayList<>();
    private static final List<Airplane> airplanes = new ArrayList<>();
    private static final List<Flight> flights = new ArrayList<>();
    private static final List<Ticket> tickets = new ArrayList<>();
    private static final List<Booking> bookings = new ArrayList<>();

    public static final FlightService flightService = new FlightService(flights, routes);
    public static final BookingService bookingService = new BookingService(flights, bookings, tickets);
    public static final TicketService ticketService = new TicketService(tickets);
    public static final AdminService adminService = new AdminService(flightService, ticketService);

    static {
        // Створюємо користувачів
        users.add(new User("admin", "admin123", User.Role.ADMIN));
        users.add(new User("john", "123", User.Role.USER));
        users.add(new User("kate", "321", User.Role.USER));

        // Маршрути
        routes.add(new FlightRoute("Kyiv", "Lviv"));
        routes.add(new FlightRoute("Kyiv", "Warsaw"));
        routes.add(new FlightRoute("Lviv", "Berlin"));

        // Літаки
        airplanes.add(new Airplane("Boeing 737", 10));
        airplanes.add(new Airplane("Airbus A320", 12));

        // Створюємо рейси
        flights.add(flightService.createFlight(routes.get(0), LocalDateTime.now().plusDays(1), airplanes.get(0)));
        flights.add(flightService.createFlight(routes.get(0), LocalDateTime.now().plusDays(2), airplanes.get(1)));
        flights.add(flightService.createFlight(routes.get(1), LocalDateTime.now().plusDays(3), airplanes.get(0)));
        flights.add(flightService.createFlight(routes.get(2), LocalDateTime.now().plusDays(4), airplanes.get(1)));
    }

    public static List<User> getUsers() {
        return users;
    }

    public static User findUserByUsername(String username) {
        return users.stream()
                .filter(u -> u.getUsername().equalsIgnoreCase(username))
                .findFirst()
                .orElse(null);
    }

    public static void registerUser(User newUser) {
        if (newUser != null && findUserByUsername(newUser.getUsername()) == null) {
            users.add(newUser);
        }
    }

    public static boolean checkCredentials(String username, String password) {
        User user = findUserByUsername(username);
        return user != null && user.getPassword().equals(password);
    }

    public static List<FlightRoute> getRoutes() {
        return routes;
    }

    public static List<Airplane> getAirplanes() {
        return airplanes;
    }

    public static List<Flight> getFlights() {
        return flights;
    }

    public static List<Booking> getBookings() {
        return bookings;
    }

    public static List<Ticket> getTickets() {
        return tickets;
    }
}
