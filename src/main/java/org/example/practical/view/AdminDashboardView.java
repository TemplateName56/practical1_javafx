package org.example.practical.view;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.practical.model.Flight;
import org.example.practical.storage.DataStore;

import java.util.Map;

public class AdminDashboardView {
    private final VBox view = new VBox(10);

    public AdminDashboardView(Stage stage) {
        view.setPadding(new Insets(20));

        Label header = new Label("Адміністративна панель");

        TextArea statsArea = new TextArea();
        statsArea.setEditable(false);

        Button refreshBtn = new Button("Оновити статистику");

        refreshBtn.setOnAction(e -> {
            StringBuilder sb = new StringBuilder();

            Map<Flight, Integer> tickets = DataStore.adminService.getSoldTicketsCountPerFlight();
            Map<Flight, Double> revenue = DataStore.adminService.getRevenuePerFlight();

            for (Flight flight : tickets.keySet()) {
                sb.append("Рейс ").append(flight).append("\n");
                sb.append(" - Продано квитків: ").append(tickets.get(flight)).append("\n");
                sb.append(" - Дохід: ").append(revenue.get(flight)).append("\n\n");
            }

            statsArea.setText(sb.toString());
        });
        Button manageFlightsBtn = new Button("Управління рейсами");
        manageFlightsBtn.setOnAction(e -> {
            stage.setScene(new Scene(new AdminFlightManagementView(stage).getView()));
        });

        Button backBtn = new Button("Назад");
        backBtn.setOnAction(e -> {
            stage.setScene(new Scene(new LoginView(stage).getView()));
        });
        view.getChildren().addAll(header, refreshBtn, manageFlightsBtn, statsArea, backBtn);
    }

    public Parent getView() {
        return view;
    }
}
