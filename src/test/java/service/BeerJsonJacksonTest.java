package service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.BeerDTO;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BeerJsonJacksonTest {

    private static final String BEERS_JSON_PATH = "data/beers.json";

    @Test
    void testReadBeersJsonWithJackson() {
        ObjectMapper mapper = new ObjectMapper();

        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(BEERS_JSON_PATH)) {
            assertNotNull(inputStream, "beers.json niet gevonden in classpath!");

            // Lees JSON naar lijst van BeerDTO
            List<BeerDTO> beerDTOs = mapper.readValue(inputStream, new TypeReference<List<BeerDTO>>() {});

            // ✅ Controleer dat er 3 items zijn
            assertEquals(3, beerDTOs.size(), "Aantal bieren in beers.json klopt niet!");

            // ✅ Controleer eerste bier
            BeerDTO firstBeer = beerDTOs.get(0);
            assertEquals("Duvel", firstBeer.getName());
            assertEquals(8.5, firstBeer.getAlcoholPercentage());
            assertEquals(2.8, firstBeer.getPrice());
            assertEquals(1, firstBeer.getBrewerId());
            assertEquals(2, firstBeer.getCategoryId());

        } catch (Exception e) {
            fail("Fout bij lezen van beers.json: " + e.getMessage());
        }
    }
}