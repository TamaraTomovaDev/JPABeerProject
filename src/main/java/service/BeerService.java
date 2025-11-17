package service;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Beer;
import model.Brewer;
import model.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repository.BeerRepository;
import repository.BrewerRepository;
import repository.CategoryRepository;
import util.JpaExecutor;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class BeerService {
    private static final Logger logger = LoggerFactory.getLogger(BeerService.class);

    private final BeerRepository beerRepository = new BeerRepository();
    private final CategoryRepository categoryRepository = new CategoryRepository();
    private final BrewerRepository brewerRepository = new BrewerRepository();

    // Validatie voor Beer-object
    private void validateBeer(Beer beer) {
        if (beer.getName() == null || beer.getName().isBlank()) {
            throw new IllegalArgumentException("Beer name cannot be empty");
        }
        if (beer.getPrice() <= 0) {
            throw new IllegalArgumentException("Price must be greater than zero");
        }
        if (beer.getAlcoholPercentage() < 0) {
            throw new IllegalArgumentException("Alcohol percentage cannot be negative");
        }
        if (beer.getBrewer() == null) {
            throw new IllegalArgumentException("Brewer must be specified");
        }
        if (beer.getCategory() == null) {
            throw new IllegalArgumentException("Category must be specified");
        }
    }

    // Nieuw bier opslaan
    public void saveBeer(Beer beer) {
        validateBeer(beer);
        logger.info("Saving Beer: {}", beer.getName());

        JpaExecutor.execute(em -> {
            Brewer brewer = brewerRepository.findById(em, beer.getBrewer().getId());
            Category category = categoryRepository.findById(em, beer.getCategory().getId());
            if (brewer == null || category == null) {
                throw new IllegalArgumentException("Brewer or category does not exist");
            }

            boolean duplicate = beerRepository.findAllBeersByBrewer(em, brewer.getId())
                    .stream()
                    .anyMatch(b -> b.getName().equalsIgnoreCase(beer.getName()));
            if (duplicate) {
                throw new IllegalArgumentException("Beer with same name already exists for this brewer");
            }

            beerRepository.create(em, beer); // BaseRepository doet transacties
            logger.info("Beer saved successfully: {}", beer.getName());
            return null;
        });
    }

    // Haalt een bier op via id
    public Beer findBeerById(int id) {
        return JpaExecutor.execute(em -> beerRepository.findById(em, id));
    }

    // Haalt alle bieren op
    public List<Beer> findAllBeers() {
        return JpaExecutor.execute(em -> beerRepository.findAll(em));
    }

    // Update een bestaand bier
    public void updateBeer(Beer beer) {
        validateBeer(beer);
        JpaExecutor.execute(em -> {
            Beer existing = beerRepository.findById(em, beer.getId());
            if (existing == null) {
                throw new IllegalArgumentException("Beer with id " + beer.getId() + " not found");
            }
            beerRepository.update(em, beer); // BaseRepository doet transacties
            logger.info("Beer updated: {}", beer.getName());
            return null;
        });
    }

    // Verwijder een bier op basis van Id
    public void deleteBeer(int id) {
        JpaExecutor.execute(em -> {
            Beer beer = beerRepository.findById(em, id);
            if (beer == null) {
                throw new IllegalArgumentException("Beer with id " + id + " not found");
            }
            beerRepository.delete(em, id); // BaseRepository doet transacties
            logger.info("Beer deleted: {}", id);
            return null;
        });
    }

    // Haalt alle bieren op van een bepaalde categorie
    public List<Beer> findBeersByCategory(int categoryId) {
        return JpaExecutor.execute(em -> beerRepository.findBeersByCategory(em, categoryId));
    }

    // Haalt alle bieren op van een bepaalde brouwer
    public List<Beer> findBeersByBrewer(int brewerID) {
        return JpaExecutor.execute(em -> beerRepository.findAllBeersByBrewer(em, brewerID));
    }

    // Haalt alle bieren op die goedkoper zijn dan een bepaalde prijs
    public List<Beer> findBeersCheaperThan(double maxPrice) {
        return JpaExecutor.execute(em -> beerRepository.findBeersCheaperThan(em, maxPrice));
    }

    // Exporteert alle bieren naar een JSON-bestand.
    public void exportBeersToJson(String filePath) {
        List<Beer> beers = findAllBeers();
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), beers);
            logger.info("Exported {} beers to JSON file: {}", beers.size(), filePath);
        } catch (IOException e) {
            logger.error("Error exporting beers to JSON: {}", e.getMessage(), e);
            throw new RuntimeException("Error exporting beers to JSON", e);
        }
    }

    // Importeert bieren vanuit een JSON-bestand en slaat ze op in de database.
    public void importBeersFromJson(String filePath) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Beer[] beers = mapper.readValue(new File(filePath), Beer[].class);
            Arrays.stream(beers).forEach(this::saveBeer); // valideert en slaat op
            logger.info("Imported {} beers from JSON file: {}", beers.length, filePath);
        } catch (IOException e) {
            logger.error("Error importing beers from JSON: {}", e.getMessage(), e);
            throw new RuntimeException("Error importing beers from JSON", e);
        }
    }
}