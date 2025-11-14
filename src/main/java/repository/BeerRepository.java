package repository;

import jakarta.persistence.EntityManager;
import model.Beer;

import java.util.List;

public class BeerRepository {
    // CREATE
    public void createBeer(EntityManager em, Beer beer) {
        em.getTransaction().begin();
        em.persist(beer);
        em.getTransaction().commit();
    }

    // findById(int id)
    public Beer findBeerById(EntityManager em, int id) {
        return em.find(Beer.class, id);
    }

    // findAll()
    public List<Beer> findAllBeers(EntityManager em) {
        return em.createQuery("SELECT b FROM Beer b", Beer.class).getResultList();
    }

    // update(T entity)
    public void updateBeer(EntityManager em, Beer beer) {
        em.getTransaction().begin();
        em.merge(beer);
        em.getTransaction().commit();
    }

    // delete(int id)
    public void deleteBeer(EntityManager em, int id) {
        em.getTransaction().begin();
        Beer beer = em.find(Beer.class, id);
        if(beer != null) {
            em.remove(beer);
        }
        em.getTransaction().commit();
    }

    // findBeersByCategory(long categoryId)
    public List<Beer> findBeersByCategory(EntityManager em, int categoryId) {
        return em.createQuery(
                "SELECT b FROM Beer b WHERE b.category.id = :categoryId", Beer.class)
                .setParameter("categoryId", categoryId)
                .getResultList();
    }

    // findBeersByBrewer(long brewerId)
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
