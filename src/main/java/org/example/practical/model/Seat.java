package org.example.practical.model;

import java.io.Serializable;

public class Seat implements Serializable {
    private int number;
    private boolean taken;

    public Seat(int number) {
        this.number = number;
        this.taken = false;
    }

    public int getNumber() { return number; }
    public boolean isTaken() { return taken; }

    public void setTaken(boolean taken) {
        this.taken = taken;
    }

    @Override
    public String toString() {
        return "Місце № " + number + (taken ? " (Taken)" : "");
    }
}
