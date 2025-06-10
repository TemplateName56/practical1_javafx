package org.example.practical.view;

import javafx.scene.control.*;
import javafx.scene.control.Dialog;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.example.practical.model.*;
import org.example.practical.storage.DataStore;

public class SeatBookingDialog extends Dialog<Void> {

    public SeatBookingDialog(Stage stage, User user, Flight flight) {
        setTitle("Бронювання місця на рейс");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        ComboBox<Seat> seatCombo = new ComboBox<>();
        seatCombo.getItems().addAll(flight.getAvailableSeats());

        ButtonType reserveBtn = new ButtonType("Забронювати", ButtonBar.ButtonData.OK_DONE);
        getDialogPane().getButtonTypes().addAll(reserveBtn, ButtonType.CANCEL);

        grid.add(new Label("Оберіть місце:"), 0, 0);
        grid.add(seatCombo, 1, 0);

        getDialogPane().setContent(grid);

        setResultConverter(dialogButton -> {
            if (dialogButton == reserveBtn) {
                Seat seat = seatCombo.getValue();

                Booking booking = DataStore.bookingService.reserveSeat(user, flight, seat);
                if (booking == null) {
                    new Alert(Alert.AlertType.ERROR, "Місце вже зайняте!").showAndWait();
                    return null;
                }

                boolean paid = DataStore.ticketService.simulatePayment(user, booking.getTicket());
                if (paid) {
                    new Alert(Alert.AlertType.INFORMATION, "Оплату підтверджено. Квиток придбано.").showAndWait();
                } else {
                    new Alert(Alert.AlertType.INFORMATION, "Оплата не відбулась.").showAndWait();
                }
            }
            return null;
        });
    }
}
