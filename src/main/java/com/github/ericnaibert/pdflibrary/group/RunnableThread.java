package com.github.ericnaibert.pdflibrary.group;

import com.github.ericnaibert.pdflibrary.storage.CoversPathStorage;
import javafx.scene.control.Button;

import java.awt.*;
import java.io.IOException;

public class RunnableThread {

    protected static void passButtonAndIndex(Button button, int index) {

        button.setOnMouseClicked(event1 -> {

            new Thread(() -> {

                System.out.println("FILE OPENED");
                System.out.println(CoversPathStorage.getBooksPaths()[index]);

                try {
                    Desktop.getDesktop().open(CoversPathStorage.getBooksPaths()[index]);

                } catch (IOException e) {
                    System.out.println("Exception: " + e);
                }

            }).start();
        });

    }
}


