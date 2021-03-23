package ru.productstar;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileReader {
    private String file;

    public FileReader(String file) {
        this.file = file;
    }

    public List<String> readFile() throws IOException, URISyntaxException {
        URL resource = getClass().getClassLoader().getResource(file);
        Path path = new File(resource.toURI()).toPath();
        return Files.readAllLines(path, StandardCharsets.UTF_8);

    }
}
