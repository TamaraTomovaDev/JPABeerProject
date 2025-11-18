package repository;

import jakarta.persistence.EntityManager;

import java.util.List;

public abstract class BaseRepository<T> {
    private final Class<T> entityClass;

    protected BaseRepository(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public void create(EntityManager em, T entity) {
        em.persist(entity);
    }

    public T findById(EntityManager em, int id) {
        return em.find(entityClass, id);
    }

    public List<T> findAll(EntityManager em) {
        return em.createQuery("SELECT e FROM " + entityClass.getSimpleName() + " e", entityClass)
                .getResultList();
    }

    public void update(EntityManager em, T entity) {
        em.merge(entity);
    }

    public void delete(EntityManager em, int id) {
        T entity = em.find(entityClass, id);
        if(entity != null)
            em.remove(entity);
    }

    // Batch insert
    public void batchInsert(EntityManager em, List<T> entities, int batchSize) {
        for (int i = 0; i < entities.size(); i++) {
            em.persist(entities.get(i));
            if (i % batchSize == 0) {
                em.flush();
                em.clear();
            }
        }
    }
}
