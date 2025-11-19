package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigUtil {
    private static final Properties properties = new Properties();
    private static final String DEFAULT_JSON_PATH = "src/main/resources/data.beers.json";

    static {
        try (InputStream input = ConfigUtil.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input != null) {
                properties.load(input);
            } else {
                System.err.println("config.properties niet gevonden! Gebruik fallback pad: " + DEFAULT_JSON_PATH);
            }
        } catch (IOException e) {
            throw new RuntimeException("Fout bij laden van config.properties", e);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key, DEFAULT_JSON_PATH);
    }

    public static void printAllProperties() {
        properties.forEach((k, v) -> System.out.println(k + "=" + v));
    }
}
