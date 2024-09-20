package ru.clevertec.util.io;

import java.io.IOException;
import java.io.Reader;

public class FileReader {
    public static String readFromFile(String filename) {
        try (Reader reader = new java.io.FileReader(filename)) {
            StringBuilder content = new StringBuilder();
            int nextChar;
            while ((nextChar = reader.read()) != -1) {
                content.append((char) nextChar);
            }
            return String.valueOf(content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
