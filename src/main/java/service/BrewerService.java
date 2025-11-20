package service;

import com.fasterxml.jackson.databind.ObjectMapper;
import dto.BrewerDTO;
import model.Brewer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repository.BrewerRepository;
import util.ConfigUtil;
import util.JpaExecutor;
import java.io.File;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class BrewerService {
    private static final Logger logger = LoggerFactory.getLogger(BrewerService.class);
    private static final String BREWERS_JSON_PATH = ConfigUtil.getProperty("brewers.json.path");
    private final BrewerRepository brewerRepository = new BrewerRepository();

    // Validatie voor Brewer-object
    private void validateBrewer(Brewer brewer) {
        if (brewer.getName() == null || brewer.getName().isBlank()) {
            throw new IllegalArgumentException("Naam van de brouwer mag niet leeg zijn");
        }
        if (brewer.getLocation() == null || brewer.getLocation().isBlank()) {
            throw new IllegalArgumentException("Locatie van de brouwer mag niet leeg zijn");
        }
    }

    // Slaat een nieuwe brouwer op
    public void saveBrewer(Brewer brewer) {
        validateBrewer(brewer);

        JpaExecutor.executeWrite(em -> {
            if (brewerRepository.findBrewerByName(em, brewer.getName()) != null) {
                throw new IllegalArgumentException("Brouwer met deze naam bestaat al.");
            }

            brewerRepository.create(em, brewer);
            logger.debug("Brouwer opgeslagen: {}", brewer.getName());
            return null;
        });
    }

    // Haalt een brouwer op via ID
    public Brewer findBrewerById(int id) {
        return JpaExecutor.executeRead(em -> brewerRepository.findById(em, id));
    }

    // Haalt alle brouwers op
    public List<Brewer> findAllBrewers() {
        return JpaExecutor.executeRead(em -> brewerRepository.findAll(em));
    }

    // Haalt een brouwer op samen met zijn bieren
    public Brewer getBrewerWithBeers(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid brewer ID");
        }

        return JpaExecutor.executeRead(em ->
                brewerRepository.findByIdWithBeers(em, id)
        );
    }

    // Update een bestaande brouwer
    public void updateBrewer(Brewer brewer) {
        validateBrewer(brewer);

        JpaExecutor.executeWrite(em -> {
            Brewer existing = brewerRepository.findById(em, brewer.getId());
            if (existing == null) {
                throw new IllegalArgumentException("Brouwer met id " + brewer.getId() + " niet gevonden.");
            }

            brewerRepository.update(em, brewer);
            logger.debug("Brouwer geüpdatet: {}", brewer.getName());
            return null;
        });
    }

    // Verwijdert een brouwer op basis van ID
    public void deleteBrewer(int id) {
        JpaExecutor.executeWrite(em -> {
            Brewer brewer = brewerRepository.findByIdWithBeers(em, id);
            if (brewer == null) {
                throw new IllegalArgumentException("Brouwer met id " + id + " niet gevonden.");
            }
            // Extra check: heeft de brouwer bieren?
            if (brewer.getBeers() != null && !brewer.getBeers().isEmpty()) {
                throw new IllegalArgumentException("Kan brouwer niet verwijderen zolang er bieren aan gekoppeld zijn.");
            }

            brewerRepository.delete(em, id);
            logger.debug("Brouwer verwijderd: {}", brewer.getName());
            return null;
        });
    }

    // Zoekt een brouwer op naam
    public Brewer findBrewerByName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Naam van de brouwer mag niet leeg zijn.");
        }
        return JpaExecutor.executeRead(em -> {
            Brewer brewer = brewerRepository.findBrewerByName(em, name);

            if (brewer == null) {
                logger.warn("Geen brouwer met de naam: {}", name);
            }

            return brewer;
        });
    }

    // Haalt alle brouwers op met het aantal bieren
    public List<Object[]> findAllBrewersWithBeerCount() {
        return JpaExecutor.executeRead(em -> brewerRepository.findBrewerWithBeerCount(em));
    }


    public void importBrewersFromJson() {
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(BREWERS_JSON_PATH)) {
            if (inputStream == null) throw new RuntimeException("brewers.json niet gevonden!");
            BrewerDTO[] brewerDTOs = mapper.readValue(inputStream, BrewerDTO[].class);
            JpaExecutor.executeWrite(em -> {
                List<Brewer> brewers = BrewerDTO.toEntityList(Arrays.asList(brewerDTOs));
                brewers.forEach(b -> brewerRepository.create(em, b));
                return null;
            });
            logger.info("Imported {} brewers from JSON", brewerDTOs.length);
        } catch (IOException e) {
            logger.error("Error importing brewers from JSON", e);
            throw new RuntimeException("Error importing brewers from JSON", e);
        }
    }

    public void exportBrewersToJson() {
        exportBrewersToJson(BREWERS_JSON_PATH);
    }

    public void exportBrewersToJson(String filePath) {
        List<Brewer> brewers = findAllBrewers();
        List<BrewerDTO> brewerDTOs = BrewerDTO.fromEntityList(brewers);
        ObjectMapper mapper = new ObjectMapper();
        try {
            File file = new File(filePath);
            file.getParentFile().mkdirs();
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, brewerDTOs);
            logger.info("Exported {} brewers to JSON file: {}", brewerDTOs.size(), filePath);
        } catch (IOException e) {
            logger.error("Error exporting brewers to JSON", e);
            throw new RuntimeException("Error exporting brewers to JSON", e);
        }
    }
}