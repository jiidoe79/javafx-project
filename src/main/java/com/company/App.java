package com.company;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) {
        stage.setTitle("Something else");
        stage.show();
    }
    public static void main(String args[]) {
        launch(args);
    }
}