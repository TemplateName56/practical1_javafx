package org.example.practical.model;

import java.io.Serializable;

public class Airplane implements Serializable {
    private String model;
    private int seatCount;

    public Airplane() {}

    public Airplane(String model, int seatCount) {
        this.model = model;
        this.seatCount = seatCount;
    }

    public String getModel() { return model; }
    public int getSeatCount() { return seatCount; }

    @Override
    public String toString() {
        return "Літак: " + model;
    }
}
