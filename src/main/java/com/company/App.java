package com.company;

import com.company.util.FileHandler;
import com.company.util.searchEngine;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;

public class App extends Application {
    public String toRgbString(Color c) {
        return "rgb("
                + (c.getRed() * 255)
                + "," + (c.getGreen() * 255)
                + "," + (c.getBlue() * 255)
                + ")";
    }
    public static void errorBox(String errormessage, String titlemessage) {
            Alert errorbox = new Alert(Alert.AlertType.INFORMATION);
            errorbox.setTitle(titlemessage);
            errorbox.setHeaderText(null);
            errorbox.setContentText(errormessage);
            errorbox.showAndWait();
    }
    @Override
    public void start(Stage stage) throws FileNotFoundException {
        //Set the Stage in place
        AtomicReference<String> lastFile = new AtomicReference<>("");
        Locale locale = Locale.getDefault();
        ResourceBundle labels = ResourceBundle.getBundle("ui", locale);
        stage.setTitle(labels.getString("title"));
        stage.initStyle(StageStyle.UTILITY);
        stage.setX(600);
        stage.setY(300);
        stage.show();
        var ref = new Object() {
            int request = 0;
        };
        String resourcePath = "C:/Users/Juha/Desktop/javakurssi/javafx-project/src/main/resources/";
        //Set Menus and Menuitems
        Menu fileMenu = new Menu(labels.getString("fileMenu"));
        MenuItem fileItemNew = new MenuItem(labels.getString("fileItemNew"));
        fileItemNew.setAccelerator(new KeyCodeCombination(KeyCode.N, KeyCombination.SHORTCUT_DOWN));
        MenuItem fileItemOpen = new MenuItem(labels.getString("fileItemOpen"));
        fileItemOpen.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCombination.SHORTCUT_DOWN));
        MenuItem fileItemSave = new MenuItem(labels.getString("fileItemSave"));
        fileItemSave.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.SHORTCUT_DOWN));
        fileItemSave.setDisable(true);
        MenuItem fileItemSaveAs = new MenuItem(labels.getString("fileItemSaveAs"));
        fileItemSaveAs.setAccelerator(new KeyCodeCombination(KeyCode.A, KeyCombination.SHORTCUT_DOWN));
        MenuItem fileItemEmpty = new MenuItem("---");
        MenuItem fileItemExit = new MenuItem(labels.getString("fileItemExit"));
        List<MenuItem> fileitems = List.of(fileItemNew, fileItemOpen, fileItemSave, fileItemSaveAs, fileItemEmpty, fileItemExit);
        fileMenu.getItems().addAll(fileitems);
        Menu editMenu = new Menu(labels.getString("editMenu"));
        MenuItem editItemCut = new MenuItem(labels.getString("editItemCut"));
        editItemCut.setAccelerator(new KeyCodeCombination(KeyCode.X, KeyCombination.SHORTCUT_DOWN));
        MenuItem editItemCopy = new MenuItem(labels.getString("editItemCopy"));
        editItemCopy.setAccelerator(new KeyCodeCombination(KeyCode.C, KeyCombination.SHORTCUT_DOWN));
        MenuItem editItemPaste = new MenuItem(labels.getString("editItemPaste"));
        editItemPaste.setAccelerator(new KeyCodeCombination(KeyCode.V, KeyCombination.SHORTCUT_DOWN));
        List<MenuItem> edititems = List.of(editItemCut, editItemCopy, editItemPaste);
        editMenu.getItems().addAll(edititems);
        Menu runMenu = new Menu(labels.getString("runMenu"));
        MenuItem runItemCNR = new Menu(labels.getString("runItemCNR"));
        runMenu.getItems().add(runItemCNR);
        Menu aboutMenu = new Menu(labels.getString("aboutMenu"));
        MenuItem aboutItemAbout = new MenuItem (labels.getString("aboutItemAbout"));
        aboutMenu.getItems().add(aboutItemAbout);
        MenuBar mbar = new MenuBar();
        mbar.getMenus().addAll(fileMenu);
        mbar.getMenus().add(editMenu);
        mbar.getMenus().add(runMenu);
        mbar.getMenus().add(aboutMenu);
        //Add toolbar items
        FileInputStream input = new FileInputStream(resourcePath + "images/fontstyle.png");
        Image image = new Image(input);
        ImageView fontIcon = new ImageView(image);
        input = new FileInputStream(resourcePath + "images/fontsize.png");
        image = new Image(input);
        ImageView fontsizeIcon = new ImageView(image);
        input = new FileInputStream(resourcePath + "images/fontcolor.png");
        image = new Image(input);
        ImageView fontcolorIcon = new ImageView(image);
        ColorPicker colorPicker = new ColorPicker();
        colorPicker.setValue(Color.BLACK);
        ComboBox fontSelector = new ComboBox(FXCollections.observableArrayList(Font.getFamilies()));
        String fontSizes[] = {"10", "12", "14", "16", "18", "20", "24", "28", "32", "40"};
        ComboBox fontSizeSelector = new ComboBox(FXCollections.observableArrayList(fontSizes));
        TextField searchField = new TextField();
        input = new FileInputStream(resourcePath + "images/search.png");
        image = new Image(input);
        ImageView searchIcon = new ImageView(image);
        Button searchPrevButton = new Button("<");
        Label matchesCounter = new Label (" 0 / 0 ");
        matchesCounter.setTextAlignment(TextAlignment.CENTER);
        Button searchNextButton = new Button(">");
        searchNextButton.setDisable(true);
        searchPrevButton.setDisable(true);
        //Define layout and scene
        HBox toolBar = new HBox(fontIcon, fontSelector, fontsizeIcon, fontSizeSelector, fontcolorIcon, colorPicker, searchIcon, searchField, searchPrevButton, matchesCounter, searchNextButton);
        VBox topPanel = new VBox(mbar, toolBar);
        TextArea textPanel = new TextArea();
        BorderPane bpane = new BorderPane();
        bpane.setTop(topPanel);
        bpane.setCenter(textPanel);
        Scene scene = new Scene(bpane, 960, 640);
        stage.setScene(scene);
        //Define actions and functions
        colorPicker.setOnAction((EventHandler) t -> textPanel.setStyle("-fx-text-fill: " + toRgbString(colorPicker.getValue()) + "; -fx-font-family: " + (fontSelector.getValue()) + "; -fx-font-size: " + (fontSizeSelector.getValue()) + " px;"));
        fontSelector.setOnAction((EventHandler) t -> textPanel.setStyle("-fx-text-fill: " + toRgbString(colorPicker.getValue()) + "; -fx-font-family: " + (fontSelector.getValue()) + "; -fx-font-size: " + (fontSizeSelector.getValue()) + " px;"));
        fontSizeSelector.setOnAction((EventHandler) t -> textPanel.setStyle("-fx-text-fill: " + toRgbString(colorPicker.getValue()) + "; -fx-font-family: " + (fontSelector.getValue()) + "; -fx-font-size: " + (fontSizeSelector.getValue()) + " px;"));
        searchNextButton.setOnAction(e -> {
            searchEngine search = new searchEngine(searchField, textPanel);
            if (search.getMatches() > 0 && ref.request < search.getMatches()) {
                ref.request++;
                search.searchNext(searchField, textPanel, ref.request);
                matchesCounter.setText(" " + (ref.request + 1) + " / "+ search.getMatches() + " ");
                if (ref.request > 0) {
                    searchPrevButton.setDisable(false);
                }
            }
            if (ref.request == search.getMatches() - 1) {
                searchNextButton.setDisable(true);
            }
        });
        searchField.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                searchPrevButton.setDisable(true);
                searchNextButton.setDisable(true);
                matchesCounter.setText(" 0 / 0 ");
                if (searchField.getText() != null && !searchField.getText().isEmpty()) {
                    searchEngine search = new searchEngine(searchField, textPanel);
                    ref.request = 0;
                    if (search.getMatches() > 0) {
                        matchesCounter.setText(" 1 / "+ search.getMatches() + " ");
                        search.searchNext(searchField, textPanel, ref.request);
                        if (search.getMatches() > 1) {
                            searchNextButton.setDisable(false);
                        }
                    } else {
                        App.errorBox(labels.getString("noMatchesText"), labels.getString("noMatchesTitle"));
                        searchPrevButton.setDisable(true);
                        searchNextButton.setDisable(true);
                    }
                } else {
                    App.errorBox(labels.getString("noSearchItem"), labels.getString("noMatchesTitle"));
                }
            }
        });
        searchPrevButton.setOnAction(e -> {
            searchEngine search = new searchEngine(searchField, textPanel);
            if (search.getMatches() > 0) {
                ref.request--;
                search.searchPrev(searchField, textPanel, ref.request);
                searchPrevButton.setDisable(false);
                searchNextButton.setDisable(false);
                matchesCounter.setText(" " + (ref.request + 1) + " / "+ search.getMatches() + " ");
            } else {
                searchPrevButton.setDisable(true);
            }
            if (ref.request == 0) {
                searchPrevButton.setDisable(true);
            }
        });
        FileChooser fileChooser = new FileChooser();
        textPanel.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.TAB) {
                int x = textPanel.getCaretPosition();
                textPanel.replaceText(x-1, x, "    ");
            }
        });
        fileItemNew.setOnAction(e -> {
            Alert newAlert = new Alert(Alert.AlertType.CONFIRMATION);
            newAlert.setTitle(labels.getString("newAlertTitle"));
            newAlert.setHeaderText(null);
            newAlert.setContentText(labels.getString("newAlertContent"));
            Optional<ButtonType> result = newAlert.showAndWait();
            if (result.get() == ButtonType.OK){
                textPanel.setText("");
            }
        });
        fileItemOpen.setOnAction(e -> {
            try {
                File file = new File(String.valueOf(fileChooser.showOpenDialog(stage)));
                FileHandler fh = new FileHandler(file.getAbsolutePath());
                String content = fh.open();
                textPanel.setText(content);
                fileChooser.setInitialDirectory(new File(file.getParent()));
                lastFile.set(fh.getFilePath());
                fileItemSave.setDisable(false);
            } catch(NullPointerException npe) {}
        });
        fileItemSave.setOnAction(e -> {
            try {
                Alert saveAlert = new Alert(Alert.AlertType.CONFIRMATION);
                saveAlert.setTitle(labels.getString("saveAlertTitle"));
                saveAlert.setHeaderText(null);
                saveAlert.setContentText(labels.getString("saveAlertContent"));
                Optional<ButtonType> result = saveAlert.showAndWait();
                if (result.get() == ButtonType.OK){
                    File file = new File(lastFile.get());
                    FileHandler fh = new FileHandler(file.getAbsolutePath());
                    String content = textPanel.getText();
                    fh.save(content);
                }
            } catch(RuntimeException npe) {}
        });
        fileItemSaveAs.setOnAction(e -> {
            try {
                File file = new File(String.valueOf(fileChooser.showSaveDialog(stage)));
                FileHandler fh = new FileHandler(file.getAbsolutePath());
                String content = textPanel.getText();
                fh.save(content);
                fileChooser.setInitialDirectory(new File(file.getParent()));
                lastFile.set(fh.getFilePath());
                fileItemSave.setDisable(false);
            } catch(NullPointerException npe) {}
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
    }

    public static void main(String args[]){
        launch(args);
    }
}