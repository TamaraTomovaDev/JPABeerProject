package repository;

import jakarta.persistence.EntityManager;
import model.Category;

import java.util.List;

public class CategoryRepository {
    // CREATE
    public void createCategory(EntityManager em, Category category) {
        em.getTransaction().begin();
        em.persist(category);
        em.getTransaction().commit();
    }

    // findById(int id)
    public Category findCategoryById(EntityManager em, int id) {
        return em.find(Category.class, id);
    }

    // findAll()
    public List<Category> findAllCategories(EntityManager em) {
        return em.createQuery("SELECT c FROM Category c", Category.class).getResultList();
    }

    // update(T entity)
    public void updateCategory(EntityManager em, Category category) {
        em.getTransaction().begin();
        em.merge(category);
        em.getTransaction().commit();
    }

    // delete(int id)
    public void deleteCategory(EntityManager em, int id) {
        em.getTransaction().begin();
        Category category = em.find(Category.class, id);
        if(category != null) {
            em.remove(category);
        }
        em.getTransaction().commit();
    }
}
