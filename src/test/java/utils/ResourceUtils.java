package utils;

import io.micrometer.core.instrument.util.IOUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public final class ResourceUtils {

    private ResourceUtils() {
        //avoid instantiation
    }

    public static String loadResourceAsString(String pathAsString) throws IOException {
        return IOUtils.toString(loadResourceAsInputStream(pathAsString), StandardCharsets.UTF_8);
    }

    private static InputStream loadResourceAsInputStream(String pathAsString) throws IOException {
        return new ClassPathResource(pathAsString).getInputStream();
    }
}
