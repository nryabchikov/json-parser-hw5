package ru.clevertec.util.io;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class FileWriterTest {
    private Path tempFile;

    @AfterEach
    void cleanUp() throws IOException {
        if (tempFile != null) {
            Files.deleteIfExists(tempFile);
        }
    }

    @Test
    void shouldWriteToFile() throws IOException {
        //given
        tempFile = Files.createTempFile("test", ".txt");
        String content = "Hello, Nikita!";
        FileWriter.writeToFile(tempFile.toString(), content);

        //when
        String result = Files.readString(tempFile);

        //then
        assertEquals(content, result);
    }
}