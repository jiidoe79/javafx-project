package com.company;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class App extends Application {
    @Override
    public void start(Stage stage) {
        stage.setTitle("Something else");
        stage.initStyle(StageStyle.UTILITY);
        //stage.setWidth(640);
        //stage.setHeight(480);
        stage.setX(600);
        stage.setY(300);
        stage.show();
        Button worldButton = new Button ("World");
        //worldButton.setTranslateY( 400 - worldButton.getHeight());
        //worldButton.setTranslateX( 600 - worldButton.getWidth());
        Group group = new Group(new Button("Hello"), worldButton);
        Scene scene = new Scene(group, 640, 480);
        stage.setScene(scene);
        worldButton.setTranslateY( 480 - worldButton.getHeight());
        worldButton.setTranslateX( 640 - worldButton.getWidth());
    }

    public static void main(String args[]){
        launch(args);
    }
}