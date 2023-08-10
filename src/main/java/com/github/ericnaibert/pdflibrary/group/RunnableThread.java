package com.github.ericnaibert.pdflibrary.group;

import javafx.scene.control.Button;

import java.awt.*;
import java.io.IOException;

public class RunnableThread {

    protected static void passButtonAndIndex(Button button, int index) {

        button.setOnMouseClicked(event1 -> {

            new Thread(() -> {

                System.out.println("FILE OPENED");
                System.out.println(HelloController.directories[index]);

                try {
                    Desktop.getDesktop().open(HelloController.directories[index]);

                } catch (IOException e) {
                    System.out.println("Exception: " + e);
                }

            }).start();
        });

    }
}


