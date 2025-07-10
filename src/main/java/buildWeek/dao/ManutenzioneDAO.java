package buildWeek.dao;

import buildWeek.entities.Biglietto;
import buildWeek.entities.Manutenzione;
import buildWeek.entities.Mezzo;
import buildWeek.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

import java.util.UUID;

public class ManutenzioneDAO {
    private final EntityManager entityManager;

    public ManutenzioneDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

// Metodo per salvare una nuova manutenzione
    public void save(Manutenzione newmanutenzione){
        EntityTransaction transaction=  entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(newmanutenzione);
        transaction.commit();
        System.out.println("Manutenzione salvata con successo");
    }

// Metodo per trovare una manutenzione per ID
    public Manutenzione findById(String id) {
        Manutenzione found = entityManager.find(Manutenzione.class, UUID.fromString(id));
        if (found == null) throw new NotFoundException(id);
        return found;
    }


// Metodo per cancellare una manutenzione per ID
    public void findByIdandDelete(UUID id) {
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        Query query = entityManager.createQuery("DELETE FROM Manutenzione m WHERE m.id = :id");
        query.setParameter("id", id);

        query.executeUpdate();

        transaction.commit();

        System.out.println( "Manutenzione cancellata con successo!");
    }

// Metodo per contare il numero di manutenzioni per un mezzo specifico
    public long countManutenzioniPerMezzo(Mezzo idMezzo) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Query query = entityManager.createQuery("SELECT count(m) FROM Manutenzione m WHERE m.idMezzo = :idMezzo");
        query.setParameter("idMezzo", idMezzo);
        Object result = query.getSingleResult();
        Long count = (Long) result;
        transaction.commit();
        System.out.println("Numero di manutenzioni per il mezzo: " + count);

        return count;
    }
}
