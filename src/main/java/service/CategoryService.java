package service;

import model.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repository.CategoryRepository;
import util.JpaExecutor;

import java.util.List;

public class CategoryService {
    private static final Logger logger = LoggerFactory.getLogger(CategoryService.class);
    private final CategoryRepository categoryRepository = new CategoryRepository();

    // Validatie voor Category-object
    private void validateCategory(Category category) {
        if (category.getName() == null || category.getName().isBlank()) {
            throw new IllegalArgumentException("Categorie naam maeg niet leeg zijn.");
        }
        if (category.getDescription() == null || category.getDescription().isBlank()) {
            throw new IllegalArgumentException("Categorie beschrijving mag niet leeg zijn.");
        }
    }

    // Slaat een nieuwe categorie op
    public void saveCategory(Category category) {
        validateCategory(category);

        JpaExecutor.executeWrite(em -> {
            categoryRepository.create(em, category); // BaseRepository doet transacties
            logger.info("Categorie opgeslagen: {}", category.getName());
            return null;
        });
    }

    // Haalt een categorie op via ID
    public Category findCategoryById(int id) {
        return JpaExecutor.executeRead(em -> categoryRepository.findById(em, id));
    }

    // Haalt alle categorieën op
    public List<Category> findAllCategories() {
        return JpaExecutor.executeRead(em -> categoryRepository.findAll(em));
    }

    // Haalt een categorie op via Id en laadt bieren
    public Category getCategoryWithBeers(int id) {
        if (id <= 0) throw new IllegalArgumentException("Ongeldig categorie ID");

        return JpaExecutor.executeRead(em -> categoryRepository.findByIdWithBeers(em, id));
    }

    // Update een bestaande categorie
    public void updateCategory(Category category) {
        validateCategory(category);

        JpaExecutor.executeWrite(em -> {
            Category existing = categoryRepository.findById(em, category.getId());
            if (existing == null) {
                throw new IllegalArgumentException("Categorie met id " + category.getId() + " niet gevonden.");
            }
            categoryRepository.update(em, category); // BaseRepository doet transacties
            logger.info("Categorie geüpdatet: {}", category.getName());
            return null;
        });
    }

    // Verwijdert een categorie op basis van ID
    public void deleteCategory(int id) {
        JpaExecutor.executeWrite(em -> {
            Category category = categoryRepository.findByIdWithBeers(em, id);
            if (category == null) {
                throw new IllegalArgumentException("Categorie met id " + id + " niet gevonden.");
            }
            // Extra check: mag niet verwijderd worden als er bieren aan gekoppeld zijn
            if (category.getBeers() != null && !category.getBeers().isEmpty()) {
                throw new IllegalArgumentException("Kan categorie niet verwijderen zolang er bieren aan gekoppeld zijn.");
            }
            categoryRepository.delete(em, id); // BaseRepository doet transacties
            logger.info("Categorie verwijderd: {}", category.getName());
            return null;
        });
    }

    // Zoekt een categorie op naam
    public Category findCategoryByName(String name) {
        return JpaExecutor.executeRead(em -> categoryRepository.findCategoryByName(em, name));
    }
}