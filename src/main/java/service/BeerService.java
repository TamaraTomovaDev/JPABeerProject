package service;

import com.fasterxml.jackson.databind.ObjectMapper;
import dto.BeerDTO;
import model.Beer;
import model.Brewer;
import model.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repository.BeerRepository;
import repository.BrewerRepository;
import repository.CategoryRepository;
import util.ConfigUtil;
import util.JpaExecutor;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class BeerService {
    private static final Logger logger = LoggerFactory.getLogger(BeerService.class);
    private static final String BEERS_JSON_PATH = ConfigUtil.getProperty("beers.json.path");

    private final BeerRepository beerRepository = new BeerRepository();
    private final CategoryRepository categoryRepository = new CategoryRepository();
    private final BrewerRepository brewerRepository = new BrewerRepository();

    // Validatie voor Beer-object
    private void validateBeer(Beer beer) {
        if (beer.getName() == null || beer.getName().isBlank()) {
            throw new IllegalArgumentException("Naam van het bier mag niet leeg zijn");
        }
        if (beer.getPrice() <= 0) {
            throw new IllegalArgumentException("Prijs moet groter zijn dan 0");
        }
        if (beer.getAlcoholPercentage() < 0) {
            throw new IllegalArgumentException("Alcoholpercentage mag niet negatief zijn");
        }
        if (beer.getBrewer() == null) {
            throw new IllegalArgumentException("Brouwer moet gespecificeerd zijn");
        }
        if (beer.getCategory() == null) {
            throw new IllegalArgumentException("Categorie moet gespecificeerd zijn");
        }
    }

    // Nieuw bier opslaan
    public void saveBeer(Beer beer) {
        validateBeer(beer);

        JpaExecutor.executeWrite(em -> {
            Brewer brewer = brewerRepository.findById(em, beer.getBrewer().getId());
            Category category = categoryRepository.findById(em, beer.getCategory().getId());

            if (brewer == null || category == null) {
                throw new IllegalArgumentException("Brouwer of categorie bestaat niet.");
            }

            // Duplicate-check via repository
            if (beerRepository.existsByNameAndBrewer(em, beer.getName(), brewer.getId())) {
                throw new IllegalArgumentException("Bier met dezelfde naam bestaat al voor deze brouwer");
            }

            beerRepository.create(em, beer);
            logger.debug("Bier opgeslagen: {}", beer.getName());
            return null;
        });
    }

    // Haalt een bier op via id
    public Beer findBeerById(int id) {
        return JpaExecutor.executeRead(em -> beerRepository.findById(em, id));
    }

    // Haalt alle bieren op
    public List<Beer> findAllBeers() {
        return JpaExecutor.executeRead(em -> beerRepository.findAll(em));
    }

    // Update een bestaand bier
    public void updateBeer(Beer beer) {
        validateBeer(beer);
        JpaExecutor.executeWrite(em -> {
            Beer existing = beerRepository.findById(em, beer.getId());
            if (existing == null) {
                throw new IllegalArgumentException("Bier met id " + beer.getId() + " niet gevonden");
            }

            // Optional: duplicate-check bij update
            if (!existing.getName().equalsIgnoreCase(beer.getName()) &&
                    beerRepository.existsByNameAndBrewer(em, beer.getName(), beer.getBrewer().getId())) {
                throw new IllegalArgumentException("Bier met dezelfde naam bestaat al voor deze brouwer");
            }

            beerRepository.update(em, beer);
            logger.debug("Bier geüpdatet: {}", beer.getName());
            return null;
        });
    }

    // Verwijder een bier op basis van Id
    public void deleteBeer(int id) {
        JpaExecutor.executeWrite(em -> {
            Beer beer = beerRepository.findById(em, id);
            if (beer == null) {
                throw new IllegalArgumentException("Bier met id " + id + " niet gevonden");
            }
            beerRepository.delete(em, id);
            logger.debug("Beer deleted: {}", id);
            return null;
        });
    }

    // Haalt alle bieren op van een bepaalde categorie
    public List<Beer> findBeersByCategory(int categoryId) {
        return JpaExecutor.executeRead(em -> beerRepository.findBeersByCategory(em, categoryId));
    }

    // Haalt alle bieren op van een bepaalde brouwer
    public List<Beer> findBeersByBrewer(int brewerID) {
        return JpaExecutor.executeRead(em -> beerRepository.findBeersByBrewer(em, brewerID));
    }

    // Haalt alle bieren op die goedkoper zijn dan een bepaalde prijs
    public List<Beer> findBeersCheaperThan(double maxPrice) {
        return JpaExecutor.executeRead(em -> beerRepository.findBeersCheaperThan(em, maxPrice));
    }

    // Import naar JSON
    public void importBeersFromJson() {
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(BEERS_JSON_PATH)) {
            if (inputStream == null) throw new RuntimeException("beers.json niet gevonden!");
            BeerDTO[] beerDTOs = mapper.readValue(inputStream, BeerDTO[].class);

            JpaExecutor.executeWrite(em -> {
                List<Brewer> brewers = brewerRepository.findAll(em);
                List<Category> categories = categoryRepository.findAll(em);
                List<Beer> beers = BeerDTO.toEntityList(Arrays.asList(beerDTOs), brewers, categories);
                beers.forEach(this::validateBeer);
                beerRepository.batchInsert(em, beers, 20);
                return null;
            });

            logger.info("Imported {} beers from JSON", beerDTOs.length);
        } catch (IOException e) {
            logger.error("Error importing beers from JSON", e);
            throw new RuntimeException("Error importing beers from JSON", e);
        }
    }

    // Export naar JSON
    public void exportBeersToJson() {
        exportBeersToJson(BEERS_JSON_PATH);
    }

    public void exportBeersToJson(String filePath) {
        List<Beer> beers = findAllBeers();
        List<BeerDTO> beerDTOs = BeerDTO.fromEntityList(beers);
        ObjectMapper mapper = new ObjectMapper();
        try {
            File file = new File(filePath);
            file.getParentFile().mkdirs();
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, beerDTOs);
            logger.info("Exported {} beers to JSON file: {}", beerDTOs.size(), filePath);
        } catch (IOException e) {
            logger.error("Error exporting beers to JSON", e);
            throw new RuntimeException("Error exporting beers to JSON", e);
        }
    }
}