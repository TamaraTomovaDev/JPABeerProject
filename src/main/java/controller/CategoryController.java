package controller;

import model.Category;
import service.CategoryService;
import util.InputUtil;

import java.util.List;

public class CategoryController {
    private final CategoryService categoryService = new CategoryService();
    private final InputUtil inputUtil;  // <- voeg toe

    // Constructor
    public CategoryController(InputUtil inputUtil) {
        this.inputUtil = inputUtil;
    }

    public void addCategory() {
        String name = inputUtil.readString("Naam categorie: ");
        String description = inputUtil.readString("Beschrijving: ");
        Category category = new Category();
        category.setName(name);
        category.setDescription(description);

        try {
            categoryService.saveCategory(category);
            System.out.println("Categorie toegevoegd!");
        } catch (Exception e) {
            System.out.println("Fout: " + e.getMessage());
        }
    }

    public void viewAllCategories() {
        List<Category> categories = categoryService.findAllCategories();
        if (categories.isEmpty()) {
            System.out.println("Geen categorieën gevonden.");
        } else {
            categories.forEach(System.out::println);
        }
    }

    public void findCategoryById() {
        int id = inputUtil.readInt("ID: ");
        Category category = categoryService.findCategoryById(id);
        System.out.println(category != null ? category : "Geen categorie gevonden met ID " + id);
    }

    public void updateCategory() {
        int id = inputUtil.readInt("ID van categorie: ");
        Category existing = categoryService.findCategoryById(id);
        if (existing == null) {
            System.out.println("Categorie niet gevonden!");
            return;
        }

        String name = inputUtil.readString("Nieuwe naam: ");
        String description = inputUtil.readString("Nieuwe beschrijving: ");
        existing.setName(name);
        existing.setDescription(description);

        try {
            categoryService.updateCategory(existing);
            System.out.println("Categorie geüpdatet!");
        } catch (Exception e) {
            System.out.println("Fout: " + e.getMessage());
        }
    }

    public void deleteCategory() {
        int id = inputUtil.readInt("ID: ");
        try {
            categoryService.deleteCategory(id);
            System.out.println("Categorie verwijderd!");
        } catch (Exception e) {
            System.out.println("Fout: " + e.getMessage());
        }
    }

    public void findCategoryByName() {
        String name = inputUtil.readString("Naam categorie: ");
        Category category = categoryService.findCategoryByName(name);
        System.out.println(category != null ? category : "Geen categorie gevonden met naam " + name);
    }
}
