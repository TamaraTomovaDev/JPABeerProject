package service;

import config.JpaConfig;
import jakarta.persistence.EntityManager;
import model.Beer;
import repository.BeerRepository;

import java.util.List;

public class BeerService {
    BeerRepository beerRepository = new BeerRepository();

    public void saveBeer(Beer beer) {
        EntityManager em = JpaConfig.getEntityManager();
        beerRepository.createBeer(em, beer);
        em.close();
    }

    public Beer findBeerById(int id) {
        EntityManager em = JpaConfig.getEntityManager();
        Beer beer = beerRepository.findBeerById(em, id);
        em.close();
        return beer;
    }

    public List<Beer> findAllBeers() {
        EntityManager em = JpaConfig.getEntityManager();
        List<Beer> beers = beerRepository.findAllBeers(em);
        em.close();
        return beers;
    }

    public void updateBeer(Beer beer) {
        EntityManager em = JpaConfig.getEntityManager();
        beerRepository.updateBeer(em, beer);
        em.close();
    }

    public void deleteBeer(int id) {
        EntityManager em = JpaConfig.getEntityManager();
        beerRepository.deleteBeer(em, id);
        em.close();
    }
}
