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
                "SELECT b FROM Beer b JOIN FETCH b.brewer JOIN FETCH b.category WHERE b.category.id = :categoryId", Beer.class)
                .setParameter("categoryId", categoryId)
                .getResultList();
    }

    // findBeersByBrewer(int brewerId)
    public List<Beer> findBeersByBrewer(EntityManager em, int brewerId) {
        return em.createQuery(
                "SELECT b FROM Beer b JOIN FETCH b.brewer JOIN FETCH b.category WHERE b.brewer.id = :brewerId", Beer.class)
                .setParameter("brewerId", brewerId)
                .getResultList();
    }

    // findBeersCheaperThan(double maxPrice)
    public List<Beer> findBeersCheaperThan(EntityManager em, double maxPrice) {
        return em.createQuery(
                "SELECT b FROM Beer b JOIN FETCH b.brewer JOIN FETCH b.category WHERE b.price < :maxPrice", Beer.class)
                .setParameter("maxPrice", maxPrice)
                .getResultList();
    }

    // Controleert of een bier met dezelfde naam al bestaat voor een specifieke brouwer
    public boolean existsByNameAndBrewer(EntityManager em, String beerName, int brewerId) {
        Long count = em.createQuery(
                "SELECT COUNT(b) FROM Beer b WHERE LOWER(b.name) = LOWER(:name) AND b.brewer.id = :brewerId", Long.class)
                .setParameter("name", beerName)
                .setParameter("brewerId", brewerId)
                .getSingleResult();
        return count > 0;
    }
}
