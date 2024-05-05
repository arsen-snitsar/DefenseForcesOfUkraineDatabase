package org.example.finalprojectalpha.Files;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class ImageHandler {

    public static void copyImageToResources(String originalPathString) {
        try {
            URI originalUri = new URI(originalPathString);
            Path originalPath = Paths.get(originalUri);
            String fileName = originalPath.getFileName().toString();
            Path copiedPath = Paths.get("src/main/resources/org/example/finalprojectalpha/Insignias/" + fileName);
            Files.copy(originalPath, copiedPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}