package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigUtil {
    private static final Properties properties = new Properties();

    private static final String DEFAULT_BEERS_PATH = "data/beers.json";
    private static final String DEFAULT_BREWERS_PATH = "data/brewers.json";
    private static final String DEFAULT_CATEGORIES_PATH = "data/categories.json";

    static {
        try (InputStream input = ConfigUtil.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input != null) {
                properties.load(input);
            } else {
                System.err.println("⚠ config.properties niet gevonden! Gebruik standaardpaden.");
            }
        } catch (IOException e) {
            throw new RuntimeException("Fout bij laden van config.properties", e);
        }
    }

    public static String getProperty(String key) {
        switch (key) {
            case "beers.json.path":
                return properties.getProperty(key, DEFAULT_BEERS_PATH);
            case "brewers.json.path":
                return properties.getProperty(key, DEFAULT_BREWERS_PATH);
            case "categories.json.path":
                return properties.getProperty(key, DEFAULT_CATEGORIES_PATH);
            default:
                return properties.getProperty(key);
        }
    }

    public static void printAllProperties() {
        properties.forEach((k, v) -> System.out.println(k + "=" + v));
    }
}