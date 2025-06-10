package org.example.practical.util;

import org.example.practical.model.Flight;

public class PriceCalculator {
    public static double calculatePrice(Flight flight) {
        int total = flight.getSeats().size();
        int available = flight.getAvailableSeats().size();
        double base = 100.0;
        double factor = 2.0; // чим менше місць — тим дорожче
        return base + (total - available) * factor;
    }
}
