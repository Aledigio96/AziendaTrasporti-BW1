package buildWeek.dao;

import buildWeek.entities.Mezzo;
import buildWeek.entities.Tratta;
import buildWeek.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;

import java.util.UUID;

public class TrattaDAO {
    private final EntityManager entityManager;

    public TrattaDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public void save(Tratta newTratta) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(newTratta);
        transaction.commit();
        System.out.println("Tratta salvata con successo");
    }


    public Tratta findById(String id) {
        Tratta found = entityManager.find(Tratta.class, UUID.fromString(id));
        if (found == null) throw new NotFoundException(id);
        return found;
    }

    public Tratta findByZonaPartenza(String zonaPartenza) {
        try {
            return entityManager.createQuery(
                            "SELECT t FROM Tratta t WHERE t.zonaPartenza = :zonaPartenza", Tratta.class)
                    .setParameter("zonaPartenza", zonaPartenza)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public void findByIdandDelete(UUID id) {
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        Query query = entityManager.createQuery("DELETE FROM Tratta tr WHERE tr.id = :id");
        query.setParameter("id", id);

        query.executeUpdate();

        transaction.commit();

        System.out.println( "Tratta cancellato con successo!");
    }
}
