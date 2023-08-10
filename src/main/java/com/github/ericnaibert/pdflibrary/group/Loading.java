package com.github.ericnaibert.pdflibrary.group;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Loading extends Stage {

    public static Stage stage;

    public static void loadingScreen() {

        stage = new Stage();

        ProgressIndicator progressIndicator = new ProgressIndicator();
        progressIndicator.setLayoutY(1);
        progressIndicator.isIndeterminate();
        progressIndicator.setPrefHeight(50);
        progressIndicator.setPrefWidth(50);
        progressIndicator.setStyle("-fx-progress-color: white;");

        Label progressIndicatorLabel = new Label();
        progressIndicatorLabel.setText("Loading Covers...");
        progressIndicatorLabel.setLayoutY(52);
        progressIndicatorLabel.setPrefWidth(100);
        progressIndicatorLabel.setStyle("-fx-font-size: 17; -fx-text-fill: white;");

        VBox vBox = new VBox();
        vBox.setBackground(Background.EMPTY);

        Scene scene = new Scene(vBox, 100, 100);
        scene.setFill(Color.TRANSPARENT);

        vBox.getChildren().add(progressIndicator);
        vBox.getChildren().add(progressIndicatorLabel);
        vBox.setAlignment(Pos.CENTER);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);

    }

}
