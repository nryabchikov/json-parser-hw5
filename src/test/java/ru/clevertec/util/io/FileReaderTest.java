package ru.clevertec.util.io;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class FileReaderTest {
    private Path tempFile;

    @AfterEach
    void cleanUp() throws IOException {
        if (tempFile != null) {
            Files.deleteIfExists(tempFile);
        }
    }

    @Test
    void shouldReadFromFile() throws IOException {
        //given
        Path tempFile = Files.createTempFile("test", ".txt");
        String content = "Hello, Nikita!";
        Files.writeString(tempFile, content);

        //when
        String result = FileReader.readFromFile(tempFile.toString());

        //then
        assertEquals(content, result);
    }
}

