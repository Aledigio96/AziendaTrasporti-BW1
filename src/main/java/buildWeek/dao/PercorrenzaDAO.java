package buildWeek.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.util.UUID;

public class PercorrenzaDAO {
    private final EntityManager entityManager;

    public PercorrenzaDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public long contaPercorrenzeMezzoTratta(String mezzoId, String trattaId) {
        Query query = entityManager.createQuery(
                "SELECT COUNT(p) FROM Percorrenza p WHERE p.idMezzo.id = :mezzoId AND p.idTratta.id = :trattaId"
        );
        query.setParameter("mezzoId", mezzoId);
        query.setParameter("trattaId", trattaId);
        return (long) query.getSingleResult();
    }
}

