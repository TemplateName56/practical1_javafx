package org.example.practical.view;

import javafx.collections.FXCollections;
import javafx.geometry.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.example.practical.model.Flight;
import org.example.practical.model.Ticket;
import org.example.practical.model.User;
import org.example.practical.storage.DataStore;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class UserDashboardView {
    private final VBox view = new VBox(10);

    public UserDashboardView(Stage stage, User user) {
        view.setPadding(new Insets(20));
        view.setAlignment(Pos.CENTER);
        view.setMinWidth(900);

        Label header = new Label("Вітаємо, " + user.getUsername());
        TextField fromField = new TextField();
        TextField toField = new TextField();
        TextField maxPriceField = new TextField();
        DatePicker datePicker = new DatePicker();

        Button searchBtn = new Button("Пошук рейсів");
        ListView<Flight> flightListView = new ListView<>();
        flightListView.setMaxHeight(200);

        Button myTicketsBtn = new Button("Мої квитки");
        ListView<Ticket> myTicketsView = new ListView<>();
        myTicketsView.setMaxHeight(200);

        HBox searchBox = new HBox(10,
                new Label("Звідки:"), fromField,
                new Label("Куди:"), toField,
                new Label("Дата:"), datePicker,
                new Label("Макс. ціна:"), maxPriceField,
                searchBtn);
        searchBox.setAlignment(Pos.CENTER_LEFT);
        Button backBtn = new Button("Назад");
        backBtn.setOnAction(e -> {
            stage.setScene(new Scene(new LoginView(stage).getView()));
        });
        view.getChildren().addAll(header, searchBox, flightListView, myTicketsBtn, myTicketsView, backBtn);

        searchBtn.setOnAction(e -> {
            String from = fromField.getText();
            String to = toField.getText();
            LocalDate selectedDate = datePicker.getValue();
            String maxPriceStr = maxPriceField.getText().trim();
            double maxPrice = Double.MAX_VALUE;
            try {
                if (!maxPriceStr.isEmpty()) {
                    maxPrice = Double.parseDouble(maxPriceStr);
                }
            } catch (Exception ignored) {}

            // Пошук усіх рейсів
            List<Flight> allFlights = DataStore.flightService.getAllFlights();

            // Фільтрація
            double finalMaxPrice = maxPrice;
            List<Flight> filtered = allFlights.stream()
                    .filter(f -> from.isEmpty() || f.getRoute().getFromCity().equalsIgnoreCase(from))
                    .filter(f -> to.isEmpty() || f.getRoute().getToCity().equalsIgnoreCase(to))
                    .filter(f -> selectedDate == null || f.getDepartureTime().toLocalDate().isEqual(selectedDate))
                    .filter(f -> org.example.practical.util.PriceCalculator.calculatePrice(f) <= finalMaxPrice)
                    .collect(Collectors.toList());

            flightListView.setItems(FXCollections.observableArrayList(filtered));
        });

        myTicketsBtn.setOnAction(e -> {
            if (user.getRole() != User.Role.GUEST) {
                myTicketsView.getItems().clear();
                myTicketsView.getItems().addAll(user.getTickets());
            }
        });

        flightListView.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2 && user.getRole() != User.Role.GUEST) {
                Flight selected = flightListView.getSelectionModel().getSelectedItem();
                if (selected != null) {
                    new SeatBookingDialog(stage, user, selected).showAndWait();
                }
            }
        });
    }

    public Parent getView() {
        return view;
    }
}
