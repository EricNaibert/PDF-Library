package com.github.ericnaibert.pdflibrary.menu;

import com.github.ericnaibert.pdflibrary.ApplicationUI;
import com.github.ericnaibert.pdflibrary.group.Loading;
import com.github.ericnaibert.pdflibrary.group.ShowBooksTask;
import com.github.ericnaibert.pdflibrary.storage.PathStorage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Refresh extends ApplicationUI {

    private static HBox hBox;

    private static final List<Node> hBoxNodes = new ArrayList<>();

    private static FlowPane flowPane;

    private static String folderName;

    public static void refreshAndCreateUI() {

        root.getChildren().remove(flowPane);

        Paint paint = Color.rgb(90, 90, 90);
        BackgroundFill backgroundFill = new BackgroundFill(paint, new CornerRadii(0), new Insets(0));

        flowPane = new FlowPane();
        flowPane.setHgap(15);
        flowPane.setVgap(15);
        flowPane.setLayoutX(20);
        flowPane.setLayoutY(100);
        flowPane.setAlignment(Pos.CENTER);
        flowPane.setPrefWidth(945);
        flowPane.setBackground(new Background(backgroundFill));

        if(PathStorage.readPathFromFile().length > 0) {

            File[] libraries = PathStorage.readPathFromFile();
            Button button;

            for (File library : libraries) {

                button = new Button();
                button.setText(library.getName());
                button.setPrefHeight(200);
                button.setPrefWidth(160);
                button.setId("groupButtons");
                button.setOnMouseClicked(event -> {

                    root.getChildren().remove(flowPane);
                    getStage.setScene(scene2);

                    setFolderName(library.getName());

                    try {
                        hBox.getChildren().removeAll(hBoxNodes);
                    } catch (Exception e) {
                        System.out.println("Exception while trying to remove empty hBox nodes:" + e);
                    }

                    refreshLibName();
                    root2.getChildren().add(hBox);

                    File pathToCovers = new File(System.getProperty("user.home") + "/Documents/PdfLibrary/" + Refresh.getFolderName() + "/covers.LIBRARY");

                    try {
                        if(pathToCovers.exists()) {
                            root2.getChildren().removeAll(ShowBooksTask.scrollPane);
                            showBookThread();
                        }

                    } catch(NullPointerException e) {
                        System.out.println("Books Path wasn't created yet. Exception:" + e);
                    }

                    if(!pathToCovers.exists()) {
                        root2.getChildren().removeAll(ShowBooksTask.scrollPane);
                    }

                });

                flowPane.getChildren().add(button);
            }
        }

        root.getChildren().add(flowPane);
    }

    protected static void showBookThread() {
        ShowBooksTask showBooksTask = new ShowBooksTask();
        Thread thread = new Thread(showBooksTask);
        thread.setDaemon(true);
        thread.start();

        showBooksTask.setOnRunning(event -> {
            Loading.loadingScreen();
            Loading.stage.show();
        });

        showBooksTask.setOnSucceeded(event -> {
            Loading.stage.close();
            root2.getChildren().addAll(ShowBooksTask.scrollPane);
        });
    }

    public static void refreshLibName() {

        hBox = new HBox();
        hBox.setPrefHeight(20);
        hBox.setPrefWidth(600);
        hBox.setLayoutY(30);
        hBox.setLayoutX(200);
        hBox.setSpacing(5);
        hBox.setAlignment(Pos.CENTER);

        Label libTitleLabel = new Label();
        libTitleLabel.setText(getFolderName().toUpperCase());
        libTitleLabel.setId("libLabelId");
        hBox.getChildren().add(libTitleLabel);
        hBoxNodes.add(libTitleLabel);

        Button trashButton = new Button();
        trashButton.setPrefWidth(40);
        trashButton.setPrefHeight(40);
        trashButton.setId("trashButton");
        trashButton.setOnMouseClicked(DeleteGroup.eventHandler);
        hBox.getChildren().add(trashButton);
        hBoxNodes.add(trashButton);
    }

    public static void setFolderName(String folderName) {
        Refresh.folderName = folderName;
    }

    public static String getFolderName() {
        return folderName;
    }
}
