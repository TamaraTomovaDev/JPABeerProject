package repository;

import jakarta.persistence.EntityManager;
import model.Category;

import java.util.List;

public class CategoryRepository extends BaseRepository<Category> {
    public CategoryRepository() {
        super(Category.class);
    }

    // findCategoryByName(String name)
    public Category findCategoryByName(EntityManager em, String name) {
        List<Category> result = em.createQuery(
                "SELECT c FROM Category c WHERE LOWER(c.name) = LOWER(:name)", Category.class)
                .setParameter("name", name)
                .getResultList();

        return result.isEmpty() ? null : result.get(0);
    }

    // Haalt een categorue op samen met de bieren
    public Category findByIdWithBeers(EntityManager em, int id) {
        List<Category> result = em.createQuery(
                "SELECT c FROM Category c LEFT JOIN FETCH c.beers WHERE c.id = :id", Category.class)
                .setParameter("id", id)
                .getResultList();

        return result.isEmpty() ? null : result.get(0);
    }
}