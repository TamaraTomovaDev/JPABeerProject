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
            brewerRepository.create(em, brewer); // BaseRepository doet transacties
            logger.info("Brouwer opgeslagen: {}", brewer.getName());
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

            brewerRepository.update(em, brewer); // BaseRepository doet transacties
            logger.info("Brouwer geüpdatet: {}", brewer.getName());
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

            brewerRepository.delete(em, id); // BaseRepository doet transacties
            logger.info("Brouwer verwijderd: {}", brewer.getName());
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

}