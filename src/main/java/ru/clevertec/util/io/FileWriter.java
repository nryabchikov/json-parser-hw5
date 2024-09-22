package ru.clevertec.util.io;

import java.io.IOException;
import java.io.Writer;

public class FileWriter {
    public static void writeToFile(String filename, String str) {
        try (Writer writer = new java.io.FileWriter(filename)) {
            writer.write(str);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
