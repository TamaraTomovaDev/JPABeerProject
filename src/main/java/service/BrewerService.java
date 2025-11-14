package service;

import config.JpaConfig;
import jakarta.persistence.EntityManager;
import model.Brewer;
import repository.BrewerRepository;

import java.util.List;

public class BrewerService {
    BrewerRepository brewerRepository = new BrewerRepository();

    public void saveBrewer(Brewer brewer) {
        EntityManager em = JpaConfig.getEntityManager();
        brewerRepository.createBrewer(em, brewer);
        em.close();
    }

    public Brewer findBrewerById(int id) {
        EntityManager em = JpaConfig.getEntityManager();
        Brewer brewer = brewerRepository.findBrewerById(em, id);
        em.close();
        return brewer;
    }

    public List<Brewer> findAllBreewers() {
        EntityManager em = JpaConfig.getEntityManager();
        List<Brewer> brewers = brewerRepository.findAllBrewers(em);
        em.close();
        return brewers;
    }

    public void updateBrewer(Brewer brewer) {
        EntityManager em = JpaConfig.getEntityManager();
        brewerRepository.updateBrewer(em, brewer);
        em.close();
    }

    public void deleteBrewer(int id) {
        EntityManager em = JpaConfig.getEntityManager();
        brewerRepository.deleteBrewer(em, id);
        em.close();
    }
}
