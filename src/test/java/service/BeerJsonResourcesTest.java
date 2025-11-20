package service;

import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

class BeerJsonResourceTest {

    private static final String BEERS_JSON_PATH = "data/beers.json";

    @Test
    void testBeersJsonExistsInClasspath() {
        // Controleer of resource gevonden wordt
        URL resourceUrl = getClass().getClassLoader().getResource(BEERS_JSON_PATH);
        assertNotNull(resourceUrl, "Resource URL is null! beers.json niet gevonden in classpath.");

        // Controleer of InputStream niet null is
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(BEERS_JSON_PATH)) {
            assertNotNull(inputStream, "InputStream is null! beers.json kan niet worden geopend.");
        } catch (Exception e) {
            fail("Fout bij openen van beers.json: " + e.getMessage());
        }
    }
}