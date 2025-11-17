package repository;

import jakarta.persistence.EntityManager;

import java.util.List;

public abstract class BaseRepository<T> {
    private final Class<T> entityClass;

    protected BaseRepository(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public void create(EntityManager em, T entity) {
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
    }

    public T findById(EntityManager em, int id) {
        return em.find(entityClass, id);
    }

    public List<T> findAll(EntityManager em) {
        return em.createQuery("SELECT e FROM " + entityClass.getSimpleName() + " e", entityClass)
                .getResultList();
    }

    public void update(EntityManager em, T entity) {
        em.getTransaction().begin();
        em.merge(entity);
        em.getTransaction().commit();
    }

    public void delete(EntityManager em, int id) {
        em.getTransaction().begin();
        T entity = em.find(entityClass, id);
        if(entity != null)
            em.remove(entity);
        em.getTransaction().commit();
    }
}
