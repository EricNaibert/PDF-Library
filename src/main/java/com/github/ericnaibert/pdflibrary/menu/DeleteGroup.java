package com.github.ericnaibert.pdflibrary.menu;

import com.github.ericnaibert.pdflibrary.ApplicationUI;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class DeleteGroup extends ApplicationUI {

    public static EventHandler<MouseEvent> eventHandler = event -> {

        File folderToDelete = new File(System.getProperty("user.home") + "/Documents/PdfLibrary/" + Refresh.getFolderName());

        try {
            FileUtils.deleteDirectory(folderToDelete);
        } catch (IOException e) {
            System.out.println("Cannot delete directory! Exception: " + e);
        } finally {
            root2.getChildren().removeAll();
            getStage.setScene(scene);
            Refresh.refreshAndCreateUI();
        }
    };

}
