package com.company;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
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
        Menu fileMenu = new Menu("File");
        MenuItem fileItemNew = new MenuItem("New (Ctrl + N)");
        MenuItem fileItemOpen = new MenuItem("Open (Ctrl + O)");
        MenuItem fileItemSave = new MenuItem("Save (Ctrl + S)");
        MenuItem fileItemEmpty = new MenuItem("---");
        MenuItem fileItemExit = new MenuItem("Exit Program");
        fileMenu.getItems().add(fileItemNew);
        fileMenu.getItems().add(fileItemOpen);
        fileMenu.getItems().add(fileItemSave);
        fileMenu.getItems().add(fileItemEmpty);
        fileMenu.getItems().add(fileItemExit);
        Menu editMenu = new Menu("Edit");
        MenuItem editItemCut = new MenuItem("Cut (Ctrl + X)");
        MenuItem editItemCopy = new MenuItem("Copy (Ctrl + C)");
        MenuItem editItemPaste = new MenuItem("Paste (Ctrl + V)");
        editMenu.getItems().add(editItemCut);
        editMenu.getItems().add(editItemCopy);
        editMenu.getItems().add(editItemPaste);
        Menu runMenu = new Menu("Run");
        MenuItem runItemCNR = new Menu("Compile and Run");
        runMenu.getItems().add(runItemCNR);
        Menu aboutMenu = new Menu("About");
        MenuItem aboutItemAbout = new MenuItem ("About the app");
        aboutMenu.getItems().add(aboutItemAbout);
        MenuBar mbar = new MenuBar();
        mbar.getMenus().add(fileMenu);
        mbar.getMenus().add(editMenu);
        mbar.getMenus().add(runMenu);
        mbar.getMenus().add(aboutMenu);
        Button clearButton = new Button ("Clear");
        VBox topPanel = new VBox(mbar, clearButton);
        TextArea textPanel = new TextArea();
        //worldButton.setTranslateY( 400 - worldButton.getHeight());
        //worldButton.setTranslateX( 600 - worldButton.getWidth());
        BorderPane bpane = new BorderPane();
        bpane.setTop(topPanel);
        bpane.setCenter(textPanel);
        Scene scene = new Scene(bpane, 640, 480);
        stage.setScene(scene);
        fileItemExit.setOnAction(e -> {
            System.exit(0);
        });
        clearButton.setOnAction(actionEvent ->  {
            textPanel.setText("");
        });
    }

    public static void main(String args[]){
        launch(args);
    }
}