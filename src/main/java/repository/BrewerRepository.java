package repository;

import jakarta.persistence.EntityManager;
import model.Brewer;

import java.util.List;

public class BrewerRepository {
    // CREATE
    public void createBrewer(EntityManager em, Brewer brewer) {
        em.getTransaction().begin();
        em.persist(brewer);
        em.getTransaction().commit();
    }

    // findById(int id)
    public Brewer findBrewerById(EntityManager em, int id) {
        return em.find(Brewer.class, id);
    }

    // findAll()
    public List<Brewer> findAllBrewers(EntityManager em) {
        return em.createQuery("SELECT br FROM Brewer br", Brewer.class).getResultList();
    }

    // update(T entity)
    public void updateBrewer(EntityManager em, Brewer brewer) {
        em.getTransaction().begin();
        em.merge(brewer);
        em.getTransaction().commit();
    }

    // delete(int id)
    public void deleteBrewer(EntityManager em, int id) {
        em.getTransaction().begin();
        Brewer brewer = em.find(Brewer.class, id);
        if(brewer != null) {
            em.remove(brewer);
        }
        em.getTransaction().commit();
    }

    // findBrewerByName(String name)
    public Brewer findBrewerByName(EntityManager em, String name) {
        return em.createQuery(
                "SELECT br FROM Brewer br WHERE br.name = :name", Brewer.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    // findAllBrewersWithBeerCount()
    public List<Object[]> findBrewerWithBeerCount(EntityManager em) {
        return em.createQuery(
                "SELECT br.name, COUNT(b) FROM Brewer br LEFT JOIN br.beers b GROUP BY br.name")
                .getResultList();
    }
}
