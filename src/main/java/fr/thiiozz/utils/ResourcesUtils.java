package fr.thiiozz.utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

/**
 * Created by thiiozz on 02/04/17.
 */
public class ResourcesUtils {
    public String getResourceFileAsString(String fileName) throws URISyntaxException, IOException {
        Path filePath = Paths.get(getClass().getResource(fileName).toURI());
        return Files.lines(filePath).collect(Collectors.joining());
    }
}
