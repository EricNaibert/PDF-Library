package com.github.ericnaibert.pdflibrary.storage;

import com.github.ericnaibert.pdflibrary.menu.Refresh;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class CoversPathStorage {

    private static String pathToBooks;

    private static final String path = System.getProperty("user.home") + "/Documents/PdfLibrary/";

    public static void storeCoverLocation(String nameToStore) {

        File file = new File(path + Refresh.getFolderName() + "/covers.LIBRARY");

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream);

            dataOutputStream.writeBytes(nameToStore);
            dataOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void getBooksLocation() {

        File pathToCovers = new File(path + Refresh.getFolderName() + "/covers.LIBRARY");

        if(pathToCovers.exists()) {
            try {
                FileInputStream fileInputStream = new FileInputStream(pathToCovers);
                DataInputStream dataInputStream = new DataInputStream(fileInputStream);

                pathToBooks = new String(dataInputStream.readAllBytes(), StandardCharsets.UTF_8);

            } catch (FileNotFoundException e) {
                System.out.println("Exception: " + e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static String getPathToBooks() {
        return pathToBooks;
    }
}
