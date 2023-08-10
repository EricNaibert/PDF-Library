package com.github.ericnaibert.pdflibrary;

import com.github.ericnaibert.pdflibrary.menu.Refresh;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ApplicationUI extends Application {

    public static Group root;
    public static Group root2;
    public static Scene scene;
    public static Scene scene2;
    public static Stage getStage;

    @Override
    public void start(Stage stage) throws IOException {

        String css = String.valueOf(ApplicationUI.class.getResource("style.css"));
        FXMLLoader fxmlLoader = new FXMLLoader(ApplicationUI.class.getResource("lib-menu-view.fxml"));
        FXMLLoader fxmlLoader2 = new FXMLLoader(ApplicationUI.class.getResource("layout-view.fxml"));

        root = new Group();
        root2 = new Group();
        root.getChildren().add(fxmlLoader.load());
        root2.getChildren().add(fxmlLoader2.load());

        Refresh.refreshAndCreateUI();

        scene = new Scene(root, 1000, 700);
        scene2 = new Scene(root2, 1000, 700);
        scene.getStylesheets().add(css);
        scene2.getStylesheets().add(css);

        getStage = stage;
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        String pathToCreate = System.getProperty("user.home");
        try {
            Files.createDirectories(Path.of(pathToCreate + "/Documents/PdfLibrary"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Toolkit.getDefaultToolkit();

        launch(args);

    }

}
