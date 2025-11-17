package service;

import model.Brewer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repository.BrewerRepository;
import util.JpaExecutor;

import java.util.List;

public class BrewerService {
    private static final Logger logger = LoggerFactory.getLogger(BrewerService.class);
    private final BrewerRepository brewerRepository = new BrewerRepository();

    // Validatie voor Brewer-object
    private void validateBrewer(Brewer brewer) {
        if (brewer.getName() == null || brewer.getName().isBlank()) {
            throw new IllegalArgumentException("Brewer name cannot be empty");
        }
        if (brewer.getLocation() == null || brewer.getLocation().isBlank()) {
            throw new IllegalArgumentException("Brewer location cannot be empty");
        }
    }

    // Slaat een nieuwe brouwer op
    public void saveBrewer(Brewer brewer) {
        validateBrewer(brewer);
        logger.info("Saving brewer: {}", brewer.getName());

        JpaExecutor.execute(em -> {
            brewerRepository.create(em, brewer); // BaseRepository doet transacties
            logger.info("Brewer saved successfully: {}", brewer.getName());
            return null;
        });
    }

    // Haalt een brouwer op via ID
    public Brewer findBrewerById(int id) {
        return JpaExecutor.execute(em -> brewerRepository.findById(em, id));
    }

    // Haalt alle brouwers op
    public List<Brewer> findAllBrewers() {
        return JpaExecutor.execute(em -> brewerRepository.findAll(em));
    }

    // Update een bestaande brouwer
    public void updateBrewer(Brewer brewer) {
        validateBrewer(brewer);
        JpaExecutor.execute(em -> {
            Brewer existing = brewerRepository.findById(em, brewer.getId());
            if (existing == null) {
                throw new IllegalArgumentException("Brewer with id " + brewer.getId() + " not found");
            }
            brewerRepository.update(em, brewer); // BaseRepository doet transacties
            logger.info("Brewer updated: {}", brewer.getName());
            return null;
        });
    }

    // Verwijdert een brouwer op basis van ID
    public void deleteBrewer(int id) {
        JpaExecutor.execute(em -> {
            Brewer brewer = brewerRepository.findById(em, id);
            if (brewer == null) {
                throw new IllegalArgumentException("Brewer with id " + id + " not found");
            }
            // Extra check: heeft de brouwer bieren?
            if (brewer.getBeers() != null && !brewer.getBeers().isEmpty()) {
                throw new IllegalArgumentException("Cannot delete brewer with beers assigned");
            }
            brewerRepository.delete(em, id); // BaseRepository doet transacties
            logger.info("Brewer deleted: {}", brewer.getName());
            return null;
        });
    }

    // Zoekt een brouwer op naam
    public Brewer findBrewerByName(String name) {
        return JpaExecutor.execute(em -> brewerRepository.findBrewerByName(em, name));
    }

    // Haalt alle brouwers op met het aantal bieren
    public List<Object[]> findAllBrewersWithBeerCount() {
        return JpaExecutor.execute(em -> brewerRepository.findBrewerWithBeerCount(em));
    }
}