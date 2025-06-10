package org.example.practical.model;

import java.io.Serializable;
import java.util.*;

public class User implements Serializable  {
    private String username;
    private String password;
    private Role role;
    private List<Ticket> tickets = new ArrayList<>();

    public User() {}

    public enum Role {
        USER, ADMIN, GUEST
    }

    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public Role getRole() { return role; }
    public List<Ticket> getTickets() { return tickets; }

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }
}
