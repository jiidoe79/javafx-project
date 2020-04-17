package com.company;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class App extends Application {
    @Override
    public void start(Stage stage) {
        stage.setTitle("Something else");
        stage.initStyle(StageStyle.UTILITY);
        stage.setWidth(640);
        stage.setHeight(480);
        stage.setX(600);
        stage.setY(300);
        stage.show();
    }

    public static void main(String args[]){
        launch(args);
    }
}