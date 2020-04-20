package com.company;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.util.Locale;
import java.util.ResourceBundle;

public class App extends Application {
    @Override
    public void start(Stage stage) {
        //Set the Stage in place
        Locale locale = Locale.getDefault();
        ResourceBundle labels = ResourceBundle.getBundle("ui", locale);
        stage.setTitle(labels.getString("title"));
        stage.initStyle(StageStyle.UTILITY);
        stage.setX(600);
        stage.setY(300);
        stage.show();
        //Set Menus and Menuitems
        Menu fileMenu = new Menu(labels.getString("fileMenu"));
        MenuItem fileItemNew = new MenuItem(labels.getString("fileItemNew"));
        fileItemNew.setAccelerator(new KeyCodeCombination(KeyCode.N, KeyCombination.SHORTCUT_DOWN));
        MenuItem fileItemOpen = new MenuItem(labels.getString("fileItemOpen"));
        fileItemOpen.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCombination.SHORTCUT_DOWN));
        MenuItem fileItemSave = new MenuItem(labels.getString("fileItemSave"));
        fileItemSave.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.SHORTCUT_DOWN));
        MenuItem fileItemEmpty = new MenuItem("---");
        MenuItem fileItemExit = new MenuItem(labels.getString("fileItemExit"));
        fileMenu.getItems().add(fileItemNew);
        fileMenu.getItems().add(fileItemOpen);
        fileMenu.getItems().add(fileItemSave);
        fileMenu.getItems().add(fileItemEmpty);
        fileMenu.getItems().add(fileItemExit);
        Menu editMenu = new Menu(labels.getString("editMenu"));
        MenuItem editItemCut = new MenuItem(labels.getString("editItemCut"));
        editItemCut.setAccelerator(new KeyCodeCombination(KeyCode.X, KeyCombination.SHORTCUT_DOWN));
        MenuItem editItemCopy = new MenuItem(labels.getString("editItemCopy"));
        editItemCopy.setAccelerator(new KeyCodeCombination(KeyCode.C, KeyCombination.SHORTCUT_DOWN));
        MenuItem editItemPaste = new MenuItem(labels.getString("editItemPaste"));
        editItemPaste.setAccelerator(new KeyCodeCombination(KeyCode.V, KeyCombination.SHORTCUT_DOWN));
        editMenu.getItems().add(editItemCut);
        editMenu.getItems().add(editItemCopy);
        editMenu.getItems().add(editItemPaste);
        Menu runMenu = new Menu(labels.getString("runMenu"));
        MenuItem runItemCNR = new Menu(labels.getString("runItemCNR"));
        runMenu.getItems().add(runItemCNR);
        Menu aboutMenu = new Menu(labels.getString("aboutMenu"));
        MenuItem aboutItemAbout = new MenuItem (labels.getString("aboutItemAbout"));
        aboutMenu.getItems().add(aboutItemAbout);
        MenuBar mbar = new MenuBar();
        mbar.getMenus().add(fileMenu);
        mbar.getMenus().add(editMenu);
        mbar.getMenus().add(runMenu);
        mbar.getMenus().add(aboutMenu);
        //Add toolbar buttons
        Button clearButton = new Button (labels.getString("clearButton"));
        //Define layout and scene
        VBox topPanel = new VBox(mbar, clearButton);
        TextArea textPanel = new TextArea();
        BorderPane bpane = new BorderPane();
        bpane.setTop(topPanel);
        bpane.setCenter(textPanel);
        Scene scene = new Scene(bpane, 640, 480);
        stage.setScene(scene);
        //Define actions and functions
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent content = new ClipboardContent();
        FileChooser fileChooser = new FileChooser();
        fileItemOpen.setOnAction(e -> {
            fileChooser.showOpenDialog(stage);
        });
        fileItemSave.setOnAction(e -> {
            fileChooser.showOpenDialog(stage);
        });
        fileItemExit.setOnAction(e -> {
            System.exit(0);
        });
        editItemCopy.setOnAction(e -> {
            textPanel.copy();
        });
        editItemCut.setOnAction(e -> {
            textPanel.cut();
        });
        editItemPaste.setOnAction(e -> {
            textPanel.paste();
        });
        aboutItemAbout.setOnAction(e -> {
            Alert about = new Alert(Alert.AlertType.INFORMATION);
            about.setTitle(labels.getString("aboutTitle"));
            about.setHeaderText(null);
            about.setContentText(labels.getString("aboutContent"));
            about.showAndWait();
        });
        clearButton.setOnAction(actionEvent ->  {
            textPanel.setText("");
        });
    }

    public static void main(String args[]){
        launch(args);
    }
}