package org.example.practical.view;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.practical.model.*;
import org.example.practical.storage.DataStore;

public class RegistrationView {
    private final VBox view = new VBox(10);

    public RegistrationView(Stage stage) {
        view.setPadding(new Insets(20));

        TextField usernameField = new TextField();
        PasswordField passwordField = new PasswordField();
        Button registerBtn = new Button("Зареєструватися");
        Button backBtn = new Button("Назад");

        view.getChildren().addAll(
                new Label("Ім’я користувача:"), usernameField,
                new Label("Пароль:"), passwordField,
                registerBtn, backBtn
        );

        registerBtn.setOnAction(e -> {
            String username = usernameField.getText().trim();
            String password = passwordField.getText().trim();

            if (username.isEmpty() || password.isEmpty()) {
                new Alert(Alert.AlertType.WARNING, "Усі поля обов’язкові.").showAndWait();
                return;
            }

            if (DataStore.findUserByUsername(username) != null) {
                new Alert(Alert.AlertType.ERROR, "Користувач уже існує.").showAndWait();
                return;
            }

            User newUser = new User(username, password, User.Role.USER);
            DataStore.registerUser(newUser);

            new Alert(Alert.AlertType.INFORMATION, "Реєстрація успішна. Тепер увійдіть.").showAndWait();
            stage.setScene(new Scene(new LoginView(stage).getView()));
        });

        backBtn.setOnAction(e -> {
            stage.setScene(new Scene(new LoginView(stage).getView()));
        });
    }

    public Parent getView() {
        return view;
    }
}
