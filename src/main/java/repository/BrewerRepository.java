package repository;

import jakarta.persistence.EntityManager;
import model.Brewer;

import java.util.List;

public class BrewerRepository extends BaseRepository<Brewer> {
    public BrewerRepository() {
        super(Brewer.class);
    }

    // findBrewerByName(String name)
    public Brewer findBrewerByName(EntityManager em, String name) {
        List<Brewer> result = em.createQuery(
                "SELECT b FROM Brewer b WHERE LOWER(b.name) = LOWER(:name)", Brewer.class)
                .setParameter("name", name)
                .getResultList();

        return result.isEmpty() ? null : result.get(0);
    }

    // findAllBrewersWithBeerCount()
    public List<Object[]> findBrewerWithBeerCount(EntityManager em) {
        return em.createQuery("SELECT br.name, COUNT(b) FROM Brewer br LEFT JOIN br.beers b GROUP BY br.name")
                .getResultList();
    }

    public Brewer findByIdWithBeers(EntityManager em, int id) {
        List<Brewer> result = em.createQuery(
                "SELECT b FROM Brewer b LEFT JOIN FETCH b.beers WHERE b.id = :id", Brewer.class)
                .setParameter("id", id)
                .getResultList();

        return result.isEmpty() ? null : result.get(0);
    }
}
