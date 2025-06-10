package org.example.practical;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.practical.view.LoginView;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        LoginView loginView = new LoginView(stage);
        stage.setTitle("Авіакаси");
        stage.setScene(new Scene(loginView.getView()));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}