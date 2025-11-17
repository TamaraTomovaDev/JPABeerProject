package repository;

import jakarta.persistence.EntityManager;
import model.Beer;

import java.util.List;

public class BeerRepository extends BaseRepository<Beer> {
    public BeerRepository() {
        super(Beer.class);
    }

    // findBeersByCategory(int categoryId)
    public List<Beer> findBeersByCategory(EntityManager em, int categoryId) {
        return em.createQuery(
                "SELECT b FROM Beer b WHERE b.category.id = :categoryId", Beer.class)
                .setParameter("categoryId", categoryId)
                .getResultList();
    }

    // findBeersByBrewer(int brewerId)
    public List<Beer> findAllBeersByBrewer(EntityManager em, int brewerId) {
        return em.createQuery(
                "SELECT b FROM Beer b WHERE b.brewer.id = :brewerId", Beer.class)
                .setParameter("brewerId", brewerId)
                .getResultList();
    }

    // findBeersCheaperThan(double maxPrice)
    public List<Beer> findBeersCheaperThan(EntityManager em, double maxPrice) {
        return em.createQuery(
                "SELECT b FROM Beer b WHERE b.price < :maxPrice", Beer.class)
                .setParameter("maxPrice", maxPrice)
                .getResultList();
    }
}
