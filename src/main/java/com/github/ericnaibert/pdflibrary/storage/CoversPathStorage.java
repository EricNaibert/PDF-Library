package com.github.ericnaibert.pdflibrary.storage;

import com.github.ericnaibert.pdflibrary.menu.Refresh;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class CoversPathStorage {

    private static File[] booksPaths;

    private static File pathToBooks;

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

                String temp = new String(dataInputStream.readAllBytes(), StandardCharsets.UTF_8);
                setBooksPaths(temp);
                setPathToBooks(temp);

            } catch (FileNotFoundException e) {
                System.out.println("Exception: " + e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static File[] getBooksPaths() {
        return booksPaths;
    }

    public static void setBooksPaths(String booksPaths) {
        CoversPathStorage.booksPaths = new File(booksPaths).listFiles();
    }

    public static File getPathToBooks() {
        return pathToBooks;
    }

    public static void setPathToBooks(String pathToBooks) {
        CoversPathStorage.pathToBooks = new File(pathToBooks);
    }
}
