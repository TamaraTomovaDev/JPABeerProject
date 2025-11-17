package repository;

import jakarta.persistence.EntityManager;
import model.Category;

public class CategoryRepository extends BaseRepository<Category> {
    public CategoryRepository() {
        super(Category.class);
    }

    // findCategoryByName(String name)
    public Category findCategoryByName(EntityManager em, String name) {
        return em.createQuery(
                "SELECT c FROM Category c WHERE c.name = :name", Category.class)
                .setParameter("name", name)
                .getSingleResult();
    }
}
