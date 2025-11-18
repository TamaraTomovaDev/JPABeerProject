package util;

import config.JpaConfig;
import jakarta.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Function;

public class JpaExecutor {
    private static final Logger logger = LoggerFactory.getLogger(JpaExecutor.class);

    // Read-only (geen transactie)
    public static <T> T executeRead(Function<EntityManager, T> action) {
        EntityManager em = JpaConfig.getEntityManager();
        try {
            logger.debug("Opening EntityManager for read");
            return action.apply(em);
        } catch (Exception e) {
            logger.error("Error during JPA read operation: {}", e.getMessage(), e);
            throw e;
        } finally {
            if (em.isOpen()) {
                em.close();
                logger.debug("EntityManager closed");
            }
        }
    }

    // Write (met transactie en rollback)
    public static <T> T executeWrite(Function<EntityManager, T> action) {
        EntityManager em = JpaConfig.getEntityManager();
        try {
            logger.debug("Opening EntityManager for write");
            em.getTransaction().begin();
            T result = action.apply(em);
            em.getTransaction().commit();
            return result;
        } catch (Exception e) {
            logger.error("Error during JPA write operation: {}", e.getMessage(), e);
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
                logger.warn("Transaction rolled back");
            }
            throw e;
        } finally {
            if (em.isOpen()) {
                em.close();
                logger.debug("EntityManager closed");
            }
        }
    }
}