package org.example.practical.view;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.example.practical.model.*;
import org.example.practical.storage.DataStore;

import java.time.LocalDateTime;
import java.util.*;

public class AdminFlightManagementView {
    private final VBox view = new VBox(10);

    public AdminFlightManagementView(Stage stage) {
        view.setPadding(new Insets(20));
        view.setMinWidth(800);

        // Ввід маршруту
        TextField fromField = new TextField();
        TextField toField = new TextField();

        // Ввід літака
        ComboBox<Airplane> planeComboBox = new ComboBox<>(FXCollections.observableArrayList(DataStore.getAirplanes()));

        // Ввід дати
        DatePicker datePicker = new DatePicker();
        Spinner<Integer> hourSpinner = new Spinner<>(0, 23, 12);
        Spinner<Integer> minuteSpinner = new Spinner<>(0, 59, 0);

        Button createFlightBtn = new Button("Створити рейс");

        // Вивід створених рейсів
        ListView<Flight> flightListView = new ListView<>(FXCollections.observableArrayList(DataStore.getFlights()));

        // Вкладення
        HBox routeBox = new HBox(10, new Label("Звідки:"), fromField, new Label("Куди:"), toField);
        HBox timeBox = new HBox(10, new Label("Дата:"), datePicker,
                new Label("Год:"), hourSpinner, new Label("Хв:"), minuteSpinner);
        HBox planeBox = new HBox(10, new Label("Літак:"), planeComboBox);

        createFlightBtn.setOnAction(e -> {
            String from = fromField.getText().trim();
            String to = toField.getText().trim();
            Airplane plane = planeComboBox.getValue();
            if (from.isEmpty() || to.isEmpty() || plane == null || datePicker.getValue() == null) {
                new Alert(Alert.AlertType.WARNING, "Будь ласка, заповніть всі поля.").showAndWait();
                return;
            }

            LocalDateTime dateTime = datePicker.getValue().atTime(hourSpinner.getValue(), minuteSpinner.getValue());

            // Створюємо маршрут
            FlightRoute route = new FlightRoute(from, to);
            if (!DataStore.getRoutes().contains(route)) {
                DataStore.getRoutes().add(route);
            } else {
                FlightRoute finalRoute = route;
                route = DataStore.getRoutes().stream().filter(r -> r.equals(finalRoute)).findFirst().get();
            }

            // Створюємо рейс
            Flight flight = new Flight(route, dateTime, plane);

            // Генеруємо місця
            List<Seat> seats = new ArrayList<>();
            for (int i = 1; i <= plane.getSeatCount(); i++) {
                seats.add(new Seat(i));
            }
            flight.setSeats(seats);

            DataStore.getFlights().add(flight);
            flightListView.getItems().add(flight);

            new Alert(Alert.AlertType.INFORMATION, "Рейс успішно створено").showAndWait();

            // Очищення полів
            fromField.clear();
            toField.clear();
            datePicker.setValue(null);
            hourSpinner.getValueFactory().setValue(12);
            minuteSpinner.getValueFactory().setValue(0);
        });

        Button backBtn = new Button("Назад");
        backBtn.setOnAction(e -> {
            stage.setScene(new Scene(new AdminDashboardView(stage).getView()));
        });

        view.getChildren().addAll(
                new Label("Створення рейсу"),
                routeBox, timeBox, planeBox,
                createFlightBtn, new Label("Список рейсів:"), flightListView,
                backBtn
        );
    }

    public Parent getView() {
        return view;
    }
}
