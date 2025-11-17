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
        return em.createQuery(
                "SELECT br FROM Brewer br WHERE br.name = :name", Brewer.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    // findAllBrewersWithBeerCount()
    public List<Object[]> findBrewerWithBeerCount(EntityManager em) {
        return em.createQuery("SELECT br.name, COUNT(b) FROM Brewer br LEFT JOIN br.beers b GROUP BY br.name")
                .getResultList();
    }
}
