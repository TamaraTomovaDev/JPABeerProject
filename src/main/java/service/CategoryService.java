package service;

import config.JpaConfig;
import jakarta.persistence.EntityManager;
import model.Category;
import repository.CategoryRepository;

import java.util.List;

public class CategoryService {
    CategoryRepository categoryRepository = new CategoryRepository();

    public void saveCategory(Category category) {
        EntityManager em = JpaConfig.getEntityManager();
        categoryRepository.createCategory(em, category);
        em.close();
    }

    public Category findCategoryById(int id) {
        EntityManager em = JpaConfig.getEntityManager();
        Category category = categoryRepository.findCategoryById(em, id);
        em.close();
        return category;
    }

    public List<Category> findAllCategories() {
        EntityManager em = JpaConfig.getEntityManager();
        List<Category> categories = categoryRepository.findAllCategories(em);
        em.close();
        return categories;
    }

    public void updateCategory(Category category) {
        EntityManager em = JpaConfig.getEntityManager();
        categoryRepository.updateCategory(em, category);
        em.close();
    }

    public void deleteCategory(int id) {
        EntityManager em = JpaConfig.getEntityManager();
        categoryRepository.deleteCategory(em, id);
        em.close();
    }
}
