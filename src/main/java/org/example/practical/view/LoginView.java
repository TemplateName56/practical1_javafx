package org.example.practical.view;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.practical.model.User;
import org.example.practical.storage.DataStore;

public class LoginView {
    private final VBox view = new VBox(10);

    public LoginView(Stage stage) {
        view.setPadding(new Insets(20));

        TextField usernameField = new TextField();
        PasswordField passwordField = new PasswordField();
        Button loginBtn = new Button("Увійти");
        Button guestBtn = new Button("Продовжити як гість");
        Button registerBtn = new Button("Зареєструватися");

        view.getChildren().addAll(
                new Label("Ім’я користувача:"), usernameField,
                new Label("Пароль:"), passwordField,
                loginBtn, registerBtn, guestBtn
        );

        loginBtn.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            if (DataStore.checkCredentials(username, password)) {
                User user = DataStore.findUserByUsername(username);
                if (user.getRole() == User.Role.ADMIN) {
                    stage.setScene(new Scene(new AdminDashboardView(stage).getView()));
                } else {
                    stage.setScene(new Scene(new UserDashboardView(stage, user).getView()));
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Невірні облікові дані").showAndWait();
            }
        });

        guestBtn.setOnAction(e -> {
            String username = "Guest";
            String password = "Guest";
            User user = new User(username, password, User.Role.GUEST);
            stage.setScene(new Scene(new UserDashboardView(stage, user).getView()));
        });

        registerBtn.setOnAction(e -> {
            RegistrationView registrationView = new RegistrationView(stage);
            stage.setScene(new Scene(registrationView.getView()));
        });
    }

    public Parent getView() {
        return view;
    }
}
