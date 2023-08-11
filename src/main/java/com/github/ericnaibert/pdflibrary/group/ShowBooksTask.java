package com.github.ericnaibert.pdflibrary.group;

import com.github.ericnaibert.pdflibrary.menu.Refresh;
import com.github.ericnaibert.pdflibrary.storage.CoversPathStorage;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Objects;

public class ShowBooksTask extends Task<Void> {

    public static ScrollPane scrollPane;

    protected static FlowPane flowPane;

    protected static File[] booksList;

    public static Void showBooks() {

        Paint paint = Color.rgb(90, 90, 90);
        BackgroundFill backgroundFill = new BackgroundFill(paint, new CornerRadii(0), new Insets(0));

        CoversPathStorage.getBooksLocation();
        String userHomePath = System.getProperty("user.home");
        File[] coversPathArray = new File(userHomePath + "/Documents/PdfLibrary/" + Refresh.getFolderName()).listFiles();
        booksList = CoversPathStorage.getBooksPaths();

        scrollPane = new ScrollPane();
        scrollPane.setPrefHeight(600);
        scrollPane.setPrefWidth(950);
        scrollPane.setLayoutX(20);
        scrollPane.setLayoutY(80);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        flowPane = new FlowPane();
        flowPane.setHgap(15);
        flowPane.setVgap(15);
        flowPane.setAlignment(Pos.CENTER);
        flowPane.setPrefWidth(945);
        flowPane.setBackground(new Background(backgroundFill));

        for(int i = 0; i < booksList.length; i++) {

            Button button = new Button();
            button.setPrefHeight(200);
            button.setPrefWidth(160);
            RunnableThread.passButtonAndIndex(button, i);

            if(booksList.length != Objects.requireNonNull(coversPathArray).length-1 || coversPathArray.length == 0) {

                try {
                    System.out.println("path: " + booksList[i].getAbsolutePath());
                    PDDocument document = Loader.loadPDF(booksList[i]);

                    PDFRenderer renderer = new PDFRenderer(document);

                    BufferedImage image = renderer.renderImage(0);

                    ImageIO.write(image, "PNG", new File(userHomePath + "/Documents/PdfLibrary/" +
                            Refresh.getFolderName() + "/cover" + i + ".png"));

                    System.out.println("Image Created");

                    document.close();

                } catch (Exception e) {
                    System.out.println("Exception: " + e);
                }
            }


            Image getImage = new Image(userHomePath + "/Documents/PdfLibrary/" + Refresh.getFolderName() +"/cover" + i + ".png");
            button.setBackground(new Background(new BackgroundImage(getImage, BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(50, 50, true,
                    true, false, true))));

            flowPane.getChildren().add(button);


        }

        scrollPane.setContent(flowPane);

        return null;
    }

    @Override
    protected Void call() {

        return ShowBooksTask.showBooks();
    }
}
