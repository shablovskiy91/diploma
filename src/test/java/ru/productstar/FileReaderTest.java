package ru.productstar;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.assertj.core.api.Assertions.assertThat;


class FileReaderTest {
    private FileReader fileReader = new FileReader("testQuiz.txt");
    @Test
    public void testReadsFile() throws IOException, URISyntaxException {
        assertThat(fileReader.readFile()).hasSize(11);
    }
}