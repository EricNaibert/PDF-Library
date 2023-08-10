package com.github.ericnaibert.pdflibrary.group;

import com.github.ericnaibert.pdflibrary.ApplicationUI;
import com.github.ericnaibert.pdflibrary.menu.Refresh;
import com.github.ericnaibert.pdflibrary.storage.CoversPathStorage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;

public class HelloController {

    public static File fileDirectory;

    public static File[] directories;

    @FXML
    protected Button button;

    @FXML
    protected Button backToMenu;

    @FXML
    protected void onClick() {

        final DirectoryChooser directoryChooser = new DirectoryChooser();

        directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));

        fileDirectory = directoryChooser.showDialog(Stage.getWindows().get(0));

        CoversPathStorage.storeCoverLocation(fileDirectory.getAbsolutePath());

        directories = fileDirectory.listFiles();

        if(fileDirectory.exists()) {

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
                ApplicationUI.root2.getChildren().addAll(ShowBooksTask.scrollPane);
            });

        }
    }

    @FXML
    protected void onClickBack() {
        ApplicationUI.root.getChildren().removeAll();
        ApplicationUI.root2.getChildren().removeAll();
        ApplicationUI.getStage.setScene(ApplicationUI.scene);
        Refresh.refreshAndCreateUI();
    }

}