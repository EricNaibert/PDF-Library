package com.github.ericnaibert.pdflibrary.storage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class PathStorage {

    private static final String userHome = System.getProperty("user.home");

    public static void storePathToFile(String nameToStore) {


        try {
            Files.createDirectories(Path.of(userHome + "/Documents/PdfLibrary/" + nameToStore));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static File[] readPathFromFile() {

        File file = new File(userHome + "/Documents/PdfLibrary");
        return file.listFiles();

    }

}
