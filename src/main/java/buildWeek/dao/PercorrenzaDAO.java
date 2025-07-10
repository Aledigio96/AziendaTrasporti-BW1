package buildWeek.dao;

import buildWeek.entities.Abbonamento;
import buildWeek.entities.Percorrenza;
import buildWeek.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import java.util.UUID;

public class PercorrenzaDAO {
    private final EntityManager entityManager;
    public PercorrenzaDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // Metodo per salvare una nuova percorrenza
    public void save(Percorrenza newpercorrenza){
        EntityTransaction transaction=  entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(newpercorrenza);
        transaction.commit();
        System.out.println("Percorrenza salvata con successo");
    }

//Metodo per trovare una percorrenza per ID
    public Percorrenza findById(String id) {
        Percorrenza found = entityManager.find(Percorrenza.class, UUID.fromString(id));
        if (found == null) throw new NotFoundException(id);
        return found;
    }


// Metodo per cancellare una percorrenza per ID
    public void findByIdandDelete(UUID id) {
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        Query query = entityManager.createQuery("DELETE FROM Percorrenza p WHERE p.id = :id");
        query.setParameter("id", id);

        query.executeUpdate();

        transaction.commit();

        System.out.println( "Percorrenza cancellata con successo!");
    }

    // Metodo per contare le percorrenze di un mezzo su una tratta specifica
    public long contaPercorrenzeMezzoTratta(String mezzoId, String trattaId) {
        Query query = entityManager.createQuery(
                "SELECT COUNT(p) FROM Percorrenza p WHERE p.idMezzo.id = :mezzoId AND p.idTratta.id = :trattaId"
        );
        query.setParameter("mezzoId", mezzoId);
        query.setParameter("trattaId", trattaId);
        return (long) query.getSingleResult();
    }
}

