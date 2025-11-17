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
            throw new IllegalArgumentException("Category name cannot be empty");
        }
        if (category.getDescription() == null || category.getDescription().isBlank()) {
            throw new IllegalArgumentException("Category description cannot be empty");
        }
    }

    // Slaat een nieuwe categorie op
    public void saveCategory(Category category) {
        validateCategory(category);
        logger.info("Saving category: {}", category.getName());

        JpaExecutor.execute(em -> {
            categoryRepository.create(em, category); // BaseRepository doet transacties
            logger.info("Category saved successfully: {}", category.getName());
            return null;
        });
    }

    // Haalt een categorie op via ID
    public Category findCategoryById(int id) {
        return JpaExecutor.execute(em -> categoryRepository.findById(em, id));
    }

    // Haalt alle categorieën op
    public List<Category> findAllCategories() {
        return JpaExecutor.execute(em -> categoryRepository.findAll(em));
    }

    // Update een bestaande categorie
    public void updateCategory(Category category) {
        validateCategory(category);
        JpaExecutor.execute(em -> {
            Category existing = categoryRepository.findById(em, category.getId());
            if (existing == null) {
                throw new IllegalArgumentException("Category with id " + category.getId() + " not found");
            }
            categoryRepository.update(em, category); // BaseRepository doet transacties
            logger.info("Category updated: {}", category.getName());
            return null;
        });
    }

    // Verwijdert een categorie op basis van ID
    public void deleteCategory(int id) {
        JpaExecutor.execute(em -> {
            Category category = categoryRepository.findById(em, id);
            if (category == null) {
                throw new IllegalArgumentException("Category with id " + id + " not found");
            }
            // Extra check: mag niet verwijderd worden als er bieren aan gekoppeld zijn
            if (category.getBeers() != null && !category.getBeers().isEmpty()) {
                throw new IllegalArgumentException("Cannot delete category with beers assigned");
            }
            categoryRepository.delete(em, id); // BaseRepository doet transacties
            logger.info("Category deleted: {}", category.getName());
            return null;
        });
    }

    // Zoekt een categorie op naam
    public Category findCategoryByName(String name) {
        return JpaExecutor.execute(em -> categoryRepository.findCategoryByName(em, name));
    }
}