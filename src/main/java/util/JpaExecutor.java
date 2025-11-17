package util;

import config.JpaConfig;
import jakarta.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Function;

public class JpaExecutor {
    private static final Logger logger = LoggerFactory.getLogger(JpaExecutor.class);

    // Voor acties die een resultaat teruggeven
    public static <T> T execute(Function<EntityManager, T> action) {
        EntityManager em = JpaConfig.getEntityManager();
        try {
            logger.debug("Opening EntityManager");
            return action.apply(em);
        } catch (Exception e) {
            logger.error("Error during JPA operation: {}", e.getMessage(), e);
            throw e;
        } finally {
            if (em.isOpen()) {
                em.close();
                logger.debug("EntityManager closed");
            }
        }
    }
}