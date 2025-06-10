package org.example.practical.model;

import java.io.Serializable;

public class FlightRoute implements Serializable {
    private String fromCity;
    private String toCity;

    public FlightRoute() {}

    public FlightRoute(String fromCity, String toCity) {
        this.fromCity = fromCity;
        this.toCity = toCity;
    }

    public String getFromCity() { return fromCity; }
    public String getToCity() { return toCity; }

    @Override
    public String toString() {
        return fromCity + " → " + toCity;
    }

    @Override
    public boolean equals(Object o) {
        return this.fromCity.equals(((FlightRoute) o).fromCity) && this.toCity.equals(((FlightRoute) o).toCity);
    }
}